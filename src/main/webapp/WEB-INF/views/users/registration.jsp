<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<style>
    h1 {text-align: center}
    p {text-align: center}
    form {text-align: center}
</style>
<body>
<h1>Registering as a new user</h1>
<h3 style="text-align:center; color: brown">${message}</h3>
<form method="post">
<p>Please fill out all the next fields</p>
    Name: <input type="text" name="name_Surname"/><br>
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="pass"><br>
    Repeat password: <input type="password" name="pass_Repeat"><br>
    <button type="submit">Register</button>
</form>
<a href="${pageContext.request.contextPath}/">Return to main</a><br>
</body>
</html>
