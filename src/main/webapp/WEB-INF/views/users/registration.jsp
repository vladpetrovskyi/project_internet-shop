<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Registration</title>
</head>
<body class="bg-light text-dark"><br>
<div class="py-md-1" style="text-align: center;">
    <h1>Registering as a new user</h1>
</div>
<div class="mx-auto text-danger py-md-1" style="width: 400px;"><h4 style="text-align: center;">
    <strong>${message}</strong>
    <strong><div class="registrationFormAlert" id="divCheckPasswordMatch"></div></strong>
</h4>
</div>
<form class="needs-validation" method="post" novalidate>
    <div class="mx-auto" style="width: 400px;">
        <div class="form-group px-md-5">
            <label for="name">Name, Surname</label>
            <input type="text" class="form-control" id="name" name="name_Surname" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                This field is mandatory.
            </div>
        </div>
        <div class="form-group px-md-5">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login" required>
            <div class="invalid-feedback">
                Please choose a username.
            </div>
            <div class="valid-feedback">
                Username is available.
            </div>
        </div>
        <div class="form-group px-md-5">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" aria-describedby="passwordHelpBlock" name="pass"
                   required>
            <small id="passwordHelpBlock" class="form-text text-muted">
                Must be 8-20 characters long.
            </small>
            <div class="invalid-feedback">
                Please create password.
            </div>
        </div>
        <div class="form-group px-md-5">
            <label for="password_repeat">Repeat password</label>
            <input type="password" class="form-control" id="password_repeat" onChange="checkPasswordMatch();"
                   name="pass_Repeat" required>
        </div>
        <div class="invalid-feedback">
            Please create password.
        </div>
    </div>
    <div class="container" style="text-align: center">
        <button type="submit" class="btn btn-dark">Register</button>
    </div>
</form>
<br>
<div class="container" style="text-align: center"><a href="${pageContext.request.contextPath}/">Return to
    main</a><br></div>
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
    function checkPasswordMatch() {
        var password = $("#password").val();
        var confirmPassword = $("#password_repeat").val();

        if (password != confirmPassword)
            $("#divCheckPasswordMatch").html("Passwords do not match!");
        else
            $("#divCheckPasswordMatch").html("");
    }

    $(document).ready(function () {
        $("#password, #password_repeat").keyup(checkPasswordMatch);
    });
</script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"></script>
</body>
</html>
