<%--
  Created by IntelliJ IDEA.
  User: kaptep
  Date: 25.07.2020
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>

    <link href="/resources/bootstrap-4.5.0/dist/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
    <link href="/resources/css/error.css" rel="stylesheet" >
    <script src="/resources/bootstrap-4.5.0/dist/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>

                    <c:choose>
                        <c:when test="${not_found}">
                          <h2>404 Not Found</h2>
                        </c:when>
                        <c:otherwise>
                            <h2>500 Internal Error</h2>
                        </c:otherwise>
                    </c:choose>


                <div class="error-details">

                    <c:choose>
                        <c:when test="${not_found}">
                            Sorry, an error has occured, Requested page not found!
                        </c:when>
                        <c:otherwise>
                            Sorry, somethong gets wrong :(
                        </c:otherwise>
                    </c:choose>



                </div>
                <div class="error-actions">
                    <a href="${request.getContextPath()}/schendule" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        Main Page </a>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
