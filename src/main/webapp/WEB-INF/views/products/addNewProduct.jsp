<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New product addition</title>
</head>
<body>
<h1>Please fill our all fields</h1>
<h3 style="text-align:center; color: brown">${message}</h3>
<form method="post">
    <p>Please fill out all the next fields</p>
    Name: <input type="text" name="name"/><br>
    Price: <input type="text" name="price"><br>
    <button type="submit">Add</button>
</form>
<a href="${pageContext.request.contextPath}/allProductsFromDb">Return</a><br>
</body>
</html>
