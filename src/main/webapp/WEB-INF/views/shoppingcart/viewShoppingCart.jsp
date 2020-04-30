<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<h1>Your shopping cart</h1>
<h3 style="text-align:center; color: brown">${message}</h3>
<table border="1">
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Action</th>

    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.price}"/></td>
            <td><a href="${pageContext.request.contextPath}/deleteFromCart?product_id=${product.id}">Delete</a></td>

        </tr>
    </c:forEach>
</table>
<p>Sum: ${sum}</p>
<form method="post" action="${pageContext.request.contextPath}/completeOrder">
    <button type="submit">Checkout</button><br>
</form>
<a href="${pageContext.request.contextPath}/allProductsToBuy">Add</a> product<br>
<a href="${pageContext.request.contextPath}/userPage">Return to your profile</a><br>
</body>
</html>
