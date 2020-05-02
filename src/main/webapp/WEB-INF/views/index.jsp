<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Main page</title>
</head>
<style>
    body {
        background: url('https://images.unsplash.com/photo-1485470733090-0aae1788d5af?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2182&q=80') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        background-size: cover;
        -o-background-size: cover;
    }
</style>
<body class="text-center bg-light text-dark">
<%--<div class="py-md-1" style="text-align: center;">--%>
<%--    <h1>Welcome to the homepage!</h1>--%>
<%--    <h5><em>Please choose from one of the next categories:</em></h5>--%>
<%--</div>--%>
<%--<div class="text-center "></div>--%>
<div class="text-center">
    
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Vilka</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/allProductsToBuy">Catalog<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/userPage">Your homepage</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/initialization">Initialize<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Lists
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/allProductsFromDb">Products</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/getAllUsers">Users</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/getAllOrders">Orders</a>
                </div>
            </li>
        </ul>
    </div>
    <span class="navbar-text float-right">
      You order - we deliver
    </span>
</nav>
<div class="container py-5">
    <header class="text-center text-white py-5">
        <h1 class="display-2 font-weight mb-4">Welcome to Vilka!</h1>
        <p class="lead mb-5"><span style="font-size: 150%;">Internet-shop, where you can buy only best-quality products.</span></p>
        <%--        <p class="font-italic">Snippet By <a href="https://bootstrapious.com" class="text-white">--%>
        <%--            <u>Bootstrapious</u></a>--%>
        <%--        </p>--%>
    </header>
<%--    <div class="text-white">--%>
<%--        <p class="lead text-center">Are you a new user? <a href="${pageContext.request.contextPath}/registration" class="text-white"><u>Sign up!</u></a><br>--%>
<%--        Already an existing user? <a href="${pageContext.request.contextPath}/login" class="text-white"><u>Sign in!</u></a></p>--%>
<%--    </div>--%>
    <footer class="mx-auto text-white text-center">
            <p class="lead">Are you a new user? <a href="${pageContext.request.contextPath}/registration" class="text-white"><u>Sign up!</u></a><br>
                Already an existing user? <a href="${pageContext.request.contextPath}/login" class="text-white"><u>Sign in!</u></a></p>
    </footer>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
