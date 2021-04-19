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
                <div class="modal-body">
                    <form  id="edit_form">
                        <label for="first">Имя</label>
                        <input type="text" name="first" id="first" class="form-control" required />
                        <br />
                        <label for="last">Фамилия</label>
                        <input type="text" name="last" id="last" class="form-control" required/>
                        <br />
                        <label>Авторизован</label>
                         <input type="checkbox" id="autorised" name="autorised">
                        <br />
                        <input type="hidden"  id="employee_id"  name="employee_id" />
                    </form>
                </div>

                <div class="modal-footer d-flex justify-content-center editInsideWrapper">
                    <button type="button" class="btn btn-primary" id="edit_butt">Редактировать</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>

                </div>
            </div>
        </div>
    </div>
    <!--  -->






    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">id</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Имя</th>
            <th scope="col">Авторизован</th>
            <th scope="col">Изменить</th>
            <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="tmuser" items="${tmusers}">
            <tr>
                <th scope="row">${tmuser.userId}</th>
                <td>${tmuser.last}</td>
                <td>${tmuser.first}</td>
                <td><input type="checkbox" <c:if test="${tmuser.autorised}" >checked="checked"</c:if> disabled> </td>
                <td>
                    <button type="button"   class="btn btn-primary" data-toggle="modal" data-target="#modalEdit" data-id="${tmuser.id}">
                    <i class="fas fa-align-justify"></i>
                </button>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#deleteModal" data-id="${tmuser.id}"
                            data-content="${tmuser.last} ${tmuser.first}">
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
