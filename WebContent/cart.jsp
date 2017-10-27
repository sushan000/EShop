
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@ page import="java.util.List"%>
<%@ page import="com.mvc.domain.ItemBean"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1>we are here at cart</h1>
</head>
<body>
<html>
<body>

	<form action="/EShop/cs" method="post">
		<%
			String addToCartMessage=(String)request.getAttribute("addToCartMessage");
			String checkItemError=(String)request.getAttribute("checkItemError");
			if(addToCartMessage!=null){
				out.println(addToCartMessage);
			}
			if(checkItemError!=null){
				out.println(checkItemError);
			}
		%>
		
		
		<table>
			<tr>
				<th></th>
				<th>Id</th>
				<th>Item Name</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>

			<tr>

				<c:forEach items="${itemlist}" var="test">
					<tr>
						<td><input type="checkbox" name="chkItem" value="${test.id}"></td>
						<td><c:out value="${test.id}" /></td>
						<td><c:out value="${test.itemName}" /></td>
						<td><c:out value="${test.price}" /></td>
						<td><input type="text" name=<c:out value="${test.id}" />></td>

					</tr>
				</c:forEach>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
			<td><input type="submit" name="action" value="Add to Cart" />
			<input type="submit" name="action" value="Checkout" />

		<input type="hidden" name="page" value="cart" />
			
			</tr>

			
</form>
			</body>
</html>

