package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.domain.ItemBean;
import com.mvc.domain.LoginBean;
import com.mvc.domain.SummaryOfAllItem;
import com.mvc.service.ItemService;
import com.mvc.service.LoginDAO;
import com.mvc.service.SummaryService;

/**
 * Servlet implementation class LoginUserServlet
 */
public class LoginUserServlet extends HttpServlet {
	private Map<Integer, Integer> cart;
	private HttpSession session;
	private List<ItemBean> itemlist;
	private ItemService itemDetail;
	public Map<Integer, Integer> updatedCart = new HashMap<>();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUserServlet() {
		// super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if (page.equalsIgnoreCase("login")) {
			LoginBean loginBean = new LoginBean();
			loginBean.setUsername(request.getParameter("username"));
			loginBean.setPassword(request.getParameter("password"));
			LoginDAO loginDAO = new LoginDAO();
			String result = loginDAO.authenticate(loginBean);
			// loginDAO.getUserCredential();

			if (result == "SUCCESS") {
				// System.out.println("success");
				session = request.getSession();
				session.setAttribute("user", loginBean.getUsername()); // Login user.
				itemDetail = new ItemService();
				itemlist = new ArrayList<>();
				itemlist = itemDetail.getItemDetails();

				session = request.getSession(true);
				cart = new HashMap<Integer, Integer>();
				session.setAttribute("itemlist", itemlist);
				response.sendRedirect("cart.jsp"); // Redirects to http://example.com/context/home after succesful
													// login.

			} else {
				// System.out.println("failure");
				request.setAttribute("errMessage", "Unknown login, please try again."); // Set error.
				request.getRequestDispatcher("Login.jsp").forward(request, response); // Forward to same page so that
																						// you
																						// can display error.

			}

			// response.getWriter().append("Served at: ").append(request.getContextPath());
		} else if (page.equalsIgnoreCase("cart")) {
			// for (Map.Entry<Integer, Integer> entry : updatedCart.entrySet()) {
			//
			// System.out.print(entry.getKey() + "\t");
			// System.out.print(entry.getValue() + "\n");
			//
			// }
			//

			// System.out.println("cart page");
			if (request.getParameter("action").equalsIgnoreCase("Add to Cart")) {
				session.removeAttribute("cart");
				String[] selectedItemIds = request.getParameterValues("chkItem");
				if (selectedItemIds != null && selectedItemIds.length > 0) {
					// System.out.println("cart is not empty");
					// System.out.println(selectedItemIds);
					for (String selectedId : selectedItemIds) {
						Integer quantity = Integer.parseInt(request.getParameter(selectedId));
						// System.out.println(quantity.toString());
						Integer id = Integer.parseInt(selectedId);
						
						if (!(updatedCart.isEmpty())) {
							SummaryService summary = new SummaryService();
							
							cart = summary.getUpdatedSummary(updatedCart, id, quantity);
							updatedCart.clear();
							updatedCart = cart;
						} else {
							cart.put(id, quantity);
						}
					}
					// session.removeAttribute("cart");
					request.setAttribute("addToCartMessage",
							selectedItemIds.length + " Items added to cart sucessfully");
					session.setAttribute("itemlist", itemlist);
					session.setAttribute("cart", cart);
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				} else {
					request.setAttribute("checkItemError", " please check Items correctly");
					session.setAttribute("itemlist", itemlist);
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}
			} else if (request.getParameter("action").equalsIgnoreCase("Checkout")) {
				Map<Integer, Integer> cartItems = (Map<Integer, Integer>) session.getAttribute("cart");

				 for(Map.Entry<Integer, Integer> entry: cartItems.entrySet()) {
				System.out.println("\n");
				 System.out.print(entry.getKey() +"\t");
				 System.out.print(entry.getValue());
				System.out.println("\n");
				 }

				SummaryService summaryService = new SummaryService();
				SummaryOfAllItem summaryItemListTotal = summaryService.getSummary(cartItems);
				// System.out.println(summaryItemListTotal.getGrandTotal());

				// for(ItemBean item: summaryItemListTotal.getItem()) {
				// System.out.println(item.getItemName());
				// System.out.println(item.getPrice());
				// System.out.println(item.getQuantity());
				// }

				request.setAttribute("ItemTotal", summaryItemListTotal);
				request.setAttribute("grandTotal", summaryItemListTotal.getGrandTotal());
				request.getRequestDispatcher("summary.jsp").forward(request, response);

			}

		} else if (page.equalsIgnoreCase("summary")) {
			if (request.getParameter("action").equalsIgnoreCase("Back")) {
				session = request.getSession(true);
				Map<Integer, Integer> cart2 = (Map<Integer, Integer>)session.getAttribute("cart");
				session.removeAttribute("cart");
				// session.removeAttribute("cart");
				String[] selectedItemSummaryIds = request.getParameterValues("chkItem");
				// System.out.println(Arrays.toString(selectedItemIds));
				// System.out.println(request.getParameter("1"));
				if (selectedItemSummaryIds != null && selectedItemSummaryIds.length > 0) {
					// System.out.println("cart is not empty");
					// System.out.println(selectedItemIds);
					// List<String> listOfItems = new ArrayList<>();
					// listOfItems = Arrays.asList(selectedItemSummaryIds);
					// for (String item : listOfItems) {
					// SummaryService summary = new SummaryService();
					// String quantity = request.getParameter(item);
					// Integer q = Integer.parseInt(quantity);
					// Integer id = Integer.valueOf(item);
					// System.out.println(q);
					// updatedCart = summary.getUpdatedSummary(cart, id, q);
					// cart.clear();
					// cart = updatedCart;
					// }

					for (String selectedItemSummaryId : selectedItemSummaryIds) {
						// System.out.println(Arrays.toString(selectedItemIds));

						SummaryService summary = new SummaryService();
						String s = request.getParameter(selectedItemSummaryId);
						
						Integer quantity = Integer.valueOf(s);

						System.out.print(quantity + "\t");// printing quantity
						Integer id = Integer.valueOf(selectedItemSummaryId);
						
						cart = summary.getUpdatedSummary(cart2, id, quantity);
//						cart.clear();
//						updatedCart.clear();
					}
//					updatedCart=cart;
					//session.setAttribute("updatedCart", updatedCart);

					// for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
					//
					// System.out.print(entry.getKey() + "\t");
					// System.out.print(entry.getValue() + "\n");
					//
					// }

					// session.removeAttribute("cart");

					session.setAttribute("itemlist", itemlist);
					session.setAttribute("cart", cart);
					// response.sendRedirect("cart.jsp");
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}

			}
		}
	}
}
