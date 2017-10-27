<%@page
	import="java.util.List, com.mvc.domain.ItemBean,com.mvc.domain.SummaryOfAllItem"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1>Cart Summary</h1>
</head>
<body>

	<form name="loginForm" action="/EShop/cs" method="post">
		<table>
			<tr>
				<th></th>
				<th>Id</th>
				<th>Item Name</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
			<%
				SummaryOfAllItem summaryItemListTotal = (SummaryOfAllItem) request.getAttribute("ItemTotal");
				for (ItemBean item : summaryItemListTotal.getItem()) {
			%>




			<tr>
				<td>&nbsp</td>
				<td><input type="hidden" name="chkItem" value="<%=item.getId()%>">
					<%
						out.println(item.getItemName());
					%>
				</td>
				<td><input type="hidden" name="<%=item.getId()%>" value ="<%=item.getQuantity()%>">
					<%
						out.println(item.getQuantity());
					%>
				</td>
				<td>
					<%
						out.println(item.getQuantity() * item.getPrice());
					%>
				</td>
			</tr>
			<br>
			<tr>
			</tr>
			
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<%
						}

						Integer total = (Integer) request.getAttribute("grandTotal");
						out.println(total);
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="action" value="Back" /> <input
			type="submit" name="action" value="Checkout" /> <input type="hidden"
			name="page" value="summary" />

	</form>
</body>
</html>