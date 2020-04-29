<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Available products</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}" />
            </td>
            <td>
                <c:out value="${product.name}" />
            </td>
            <td>
                <c:out value="${product.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/addToCart?product_id=${product.id}">Buy</a><br>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/viewCart">View</a> your shopping cart<br>
<a href="${pageContext.request.contextPath}/userPage">Return to your profile</a><br>
</body>
</html>
