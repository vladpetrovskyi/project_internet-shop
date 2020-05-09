<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>New product addition</title>
</head>
<body class="bg-light text-dark"><br>
<div class="py-md-1" style="text-align: center;">
    <h1>New product addition</h1>
</div>
<div class="mx-auto text-danger py-md-1" style="width: 400px;"><h4 style="text-align: center;"><strong>${message}</strong></h4></div>
<form class="needs-validation" method="post" novalidate>
    <div class="mx-auto" style="width: 400px;">
        <div class="form-group px-md-5">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name of new product" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                This field is mandatory.
            </div>
        </div>
        <div class="form-group px-md-5">
            <label for="price">Price</label>
            <input type="text" class="form-control" id="price" name="price" placeholder="25.95$" required>
            <div class="invalid-feedback">
                Please add price.
            </div>
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
    </div>
    <div class="container" style="text-align: center">
        <button type="submit" class="btn btn-dark">Add</button>
    </div></form>
<br>
<div class="container" style="text-align: center"><a href="${pageContext.request.contextPath}/products/allFromDb">Return</a><br></div>
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
