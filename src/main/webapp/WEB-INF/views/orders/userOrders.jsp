<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>
<h2>Your orders</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Action</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="#${order.id}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/viewOrder?order_id=${order.id}">View</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/userPage">Return to your profile</a><br>
</body>
</html>
