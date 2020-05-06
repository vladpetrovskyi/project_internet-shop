<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Shopping cart</title>
</head>
<style>
    body {
        background: url('https://images.unsplash.com/photo-1485470733090-0aae1788d5af?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2182&q=80') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        background-size: cover;
        -o-background-size: cover;
    }

    table.center {
        margin-left:auto;
        margin-right:auto;
    }
</style>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Vilka</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/products/allAvailable">Catalog<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/user">My profile</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/orders">My orders</a>
            </li>
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    My shopping cart
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/cart">View</a>
                </div>
            </li>
        </ul>
    </div>
    <span class="nav-item active float-right">
        <a class="nav-link text-dark" href="${pageContext.request.contextPath}/logout">Sign out</a>
    </span>
</nav>
<div class="container" style="width: 50%">
    <div class="card border-0 shadow my-5">
        <div class="card-body p-5">
            <h1 style="text-align: center">Your shopping cart</h1><br>
            <h3 style="text-align:center; color: red">${message}</h3><br>
            <table class="table center text-center">
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td><c:out value="${product.name}" /></td>
                        <td><c:out value="${product.price}$"/></td>
                        <td><a href="${pageContext.request.contextPath}/cart/delete?product_id=${product.id}" class="btn btn-outline-dark" role="button" aria-pressed="true">Delete</a></td>
                    </tr>
                </c:forEach>
            </table><br>
            <h4 class="text-dark text-right">Total: ${sum}$</h4><br>
            <form method="post" action="${pageContext.request.contextPath}/cart/checkout">
                <button class="btn btn-dark float-right" type="submit">Checkout</button>
            </form>
            <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/products/allAvailable" role="button" aria-pressed="true">Add product</a>
            <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/user" role="button" aria-pressed="true">Return to your profile</a>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
