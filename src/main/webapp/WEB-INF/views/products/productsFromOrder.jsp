<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order</title>
</head>
<body>
<h3 style="color: brown">${message}</h3>
<h2>Your order #${order.id}</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${order.getProducts()}">
        <tr>
            <td>
                <c:out value="${product.getId()}" />
            </td>
            <td>
                <c:out value="${product.getName()}" />
            </td>
            <td>
                <c:out value="${product.getPrice()}" />
            </td>
        </tr>
    </c:forEach>
</table>
<p>Sum: ${sum}</p>
<a href="${pageContext.request.contextPath}/userPage">Return to your profile</a><br>
<a href="${pageContext.request.contextPath}/">Return to main</a><br><br>
</body>
</html>
