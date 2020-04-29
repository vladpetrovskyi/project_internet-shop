<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<h2>Welcome!</h2>
Are you a new user? <a href="${pageContext.request.contextPath}/registration">Sign up!</a><br>
<a href="${pageContext.request.contextPath}/allProductsFromDb">Get</a> list of all products<br>
<a href="${pageContext.request.contextPath}/getAllUsers">Get</a> list of all users<br>
<a href="${pageContext.request.contextPath}/getAllOrders">Get</a> list of all orders<br>
<a href="${pageContext.request.contextPath}/initialization">Initialize</a><br>
</body>
</html>
