<%--
  Created by IntelliJ IDEA.
  User: kaptep
  Date: 03.06.2020
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <link rel="canonical" href="https://getbootstrap.com/docs/3.3/examples/signin/">

    <title>CrazzyOffice</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/resources/css/signin.css" rel="stylesheet">


</head>


    <body class="text-center">
    <form class="form-signin" action="perform_login"
          method="post">
        <img class="mb-4" src="/resources/img/avatar.png" alt="">
        <h1 class="h3 mb-3 font-weight-normal">CrazyOffice</h1>
        <label for="name" class="sr-only">Email address</label>
        <input type="text" id="name" name = "username" class="form-control" placeholder="User name" required autofocus>
        <label for="pass" class="sr-only">Password</label>
        <input type="password" id="pass" name="password" class="form-control" placeholder="Password" required>
        <div class="mb-3">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </div>
            <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
    </form>
    </body>


</html>
