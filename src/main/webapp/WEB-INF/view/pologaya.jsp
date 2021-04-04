<%--
  Created by IntelliJ IDEA.
  User: gosha
  Date: 19.10.2020
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pologaya</title>

    <link href="/resources/fontawesome/css/all.css" rel="stylesheet">
    <link href="/resources/bootstrap-4.5.0/dist/css/bootstrap.css" rel="stylesheet">
    <link  href="/resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet">


</head>
<body>
<div class="container my-3">


    <!-- Modal  Delete -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Внимание</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить <span id = "del_name" data-id =''></span> ?
                     </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Нет</button>
                    <button type="button" class="btn btn-primary" id="del_butt">Да</button>
                </div>
            </div>
        </div>
    </div>
    <!--  -->

    <!-- Modal  Edit -->

    <div class="modal fade modalEditClass" id="modalEdit" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold text-secondary ml-5">Редактировать профиль</h4>
                    <button type="button" class="close text-secondary" data-dismiss="modal" aria-label="Close">
                        <span id = "edit_name" data-id =''  aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="formNameEdit1">Фамилия</label>
                        <input type="text" id="formNameEdit1" class="form-control validate">
                    </div>

                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="formNameEdit1">Имя</label>
                        <input type="text" id="formNameEdit2" class="form-control validate">

                    </div>

                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="formPositionEdit1">Отдел</label>
                        <select id="formPositionEdit1" class="form-control validate">
                            <option>ВСК</option>
                        </select>

                    </div>

                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="formOfficeEdit1">Номер телефона</label>
                        <input type="text" id="formOfficeEdit1" class="form-control validate">
                    </div>

                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="formAgeEdit1">Авторизован</label>
                        <input type="checkbox" id="formAgeEdit1" class="form-control validate">
                    </div>
                </div>

                <div class="modal-footer d-flex justify-content-center editInsideWrapper">
                    <button class="btn btn-outline-secondary btn-block editInside" data-dismiss="modal">Редактировать
                        <i class="fas fa-paper-plane-o ml-1"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!--  -->






    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">id</th>
            <th scope="col">Фамилия Имя</th>
            <th scope="col">Отдел</th>
            <th scope="col">Телефон</th>
            <th scope="col">Авторизован</th>
            <th scope="col">Изменить</th>
            <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="tmuser" items="${tmusers}">
            <tr>
                <th scope="row">${tmuser.userId}</th>
                <td>${tmuser.person.lastName} ${tmuser.person.firstName}</td>
                <td>${tmuser.person.department}</td>
                <td>${tmuser.person.phone}</td>
                <td><input type="checkbox" <c:if test="${tmuser.autorised}" >checked="checked"</c:if> disabled> </td>
                <td>
                    <button type="button"   class="btn btn-primary" data-toggle="modal" data-target="#modalEdit" data-id="${tmuser.id}">
                    <i class="fas fa-align-justify"></i>
                </button>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#deleteModal" data-id="${tmuser.id}"
                            data-content="${tmuser.person.lastName} ${tmuser.person.firstName}">
                        <i class="far fa-trash-alt"></i>
                    </button>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>



</div>



<script src="/resources/js/jquery-1.11.1.min.js"></script>
<script src="/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="/resources/proper/popper.min.js"></script>
<script src="/resources/bootstrap-4.5.0/dist/js/bootstrap.js"></script>
<script src="/resources/sweetalert2-9.17.0/package/dist/sweetalert2.all.min.js"></script>
<script src="/resources/clickEvents.js"></script>

</body>
</html>
