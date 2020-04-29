<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order</title>
</head>
<body>
<h2>Your order #${order_id}</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
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
        </tr>
    </c:forEach>
</table>
<p>Sum: ${sum}</p>
<a href="${pageContext.request.contextPath}/userPage">Return to your profile</a><br>
<a href="${pageContext.request.contextPath}/">Return to main</a><br><br>
</body>
</html>
