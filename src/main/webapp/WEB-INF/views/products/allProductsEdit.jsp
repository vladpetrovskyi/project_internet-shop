<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>Products</h1>
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
                <a href="${pageContext.request.contextPath}/deleteProductFromDb?product_id=${product.id}">Delete</a><br>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/addNewProduct"> Add</a> a new product</p>
<a href="${pageContext.request.contextPath}/">Return to main</a><br>
</body>
</html>
