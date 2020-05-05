<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Login</title>
</head>
<body class="bg-light text-dark"><br>
<div class="py-md-1" style="text-align:center;"><br>
    <h1>Sign in</h1>
</div>
<h4 style="text-align:center; color: red">${message}</h4><br>
<form class="needs-validation" method="post" novalidate>
    <div class="mx-auto" style="width: 400px;">
        <div class="form-group px-md-5">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" aria-describedby="loginHelp" name="login" required>
            <%--            <small id="loginHelp" class="form-text text-muted">We'll never share your data with anyone else.</small>--%>
            <div class="invalid-feedback">
                This field is mandatory!
            </div>
        </div>
        <div class="form-group px-md-5">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" aria-describedby="passHelp" name="pass" required>
            <%--            <small id="passHelp" class="form-text text-muted">Your password must contain 8-20 characters.</small>--%>
            <div class="invalid-feedback">
                This field is mandatory!
            </div>
        </div>
    </div>
    <div style="text-align: center">
        <button type="submit" class="btn btn-dark">Submit</button>
    </div><br>
</form>
<br>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<div class="mx-auto" style="text-align: center"><p>Are you a new user?
    <a href="${pageContext.request.contextPath}/registration">Sign up!</a></p></div>
<div class="mx-auto" style="text-align: center"><p>
    <a href="${pageContext.request.contextPath}/">Return</a></p></div>
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
