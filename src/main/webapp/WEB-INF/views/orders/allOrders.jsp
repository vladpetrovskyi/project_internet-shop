<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h2>Orders</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:out value="${order.user.id}" />
            </td>
            <td>
                <c:out value="${order.user.name}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/viewOrder?order_id=${order.id}">View</a>
                <a href="${pageContext.request.contextPath}/deleteOrder?order_id=${order.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/">Return to main</a><br><br>
</body>
</html>
