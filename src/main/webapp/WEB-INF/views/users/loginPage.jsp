<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h3 style="text-align:center; color: brown">${message}</h3>
<form method="post">
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="pass"><br>
    <button type="submit">Login</button>
</form>
<p>Are you a new user? <a href="${pageContext.request.contextPath}/registration">Sign up!</a></p><br>
</body>
</html>
