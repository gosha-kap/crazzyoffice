<%--
  Created by IntelliJ IDEA.
  User: kaptep
  Date: 15.06.2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>

    <link href="/resources/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/bootstrap-4.5.0/dist/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/fullcalendar/core/main.min.css" rel="stylesheet">
    <link href="/resources/fullcalendar/bootstrap/main.min.css" rel="stylesheet">
    <link href="/resources/fullcalendar/daygrid/main.min.css" rel="stylesheet">
    <link  href="/resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet">

</head>
<body>
<div class="container my-3">

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form method="post" action="/schendule">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Добавить</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Дата</th>
                            <th scope="col">Работа</th>
                            <th scope="col">Сотрудник</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input  id="datepicker" name="datepicker" class="form-control"></td>
                            <td>
                            <select name="job" class="form-control">
                                <c:forEach var="job" items="${jobs}">
                                    <option value="${job.id}">${job.description}</option>
                                </c:forEach>
                            </select>
                            </td>
                            <td>
                                <select name="person" class="form-control">
                                    <c:forEach var="person" items="${persons}">
                                        <option value="${person.id}">${person.alias}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>

            </div>

            </form>
        </div>
    </div>
    <div id='calendar'></div>

</div>


<script src="/resources/js/jquery-1.11.1.min.js"></script>
<script src="/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="/resources/proper/popper.min.js"></script>
<script src="/resources/bootstrap-4.5.0/dist/js/bootstrap.js"></script>
<script src="/resources/fullcalendar/core/main.min.js"></script>
<script src="/resources/fullcalendar/bootstrap/main.min.js"></script>
<script src="/resources/fullcalendar/daygrid/main.min.js"></script>
<script src="/resources/fullcalendar/core/locales/ru.js"></script>
<script src="/resources/fullcalendar/interaction/main.min.js"></script>
<script src="/resources/sweetalert2-9.17.0/package/dist/sweetalert2.all.min.js"></script>


<script>
    var el = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(el,{

        plugins:['dayGrid','bootstrap','interaction'],
        firstDay:1,
        themeSystem:'bootstrap',
        weekNumbers:false,
        eventLimit:true,
        defaultDate: '${defaultDate}',
        events:'${request.getContextPath()}/jobevent',
        customButtons:{
            myCustomButton: {
                text: 'Add Event',
                click: function() {
                    $("#exampleModal").modal();
                }
            }
        },
        header:{
            left: 'myCustomButton',
            center: 'title',
            right: 'today prev,next'

        },

        eventRender(info){
            let icon = info.event.extendedProps.icon;
            let title = $(info.el).find('.fc-title');
            let title_list = $(info.el).find('.fc-list-item-title a');

            if (icon) {
                var micon = "<i class='" + icon + "'></i>&nbsp";
                title.prepend(micon);
                title_list.prepend(micon);
            }
        },
        editable:true,
        selectable:true,
        eventClick(arg) {
            arg.jsEvent.preventDefault();
            if(arg.event.url){
                window.open(arg.event.url);
                }
            else {
                Swal.fire({
                    title: 'Внимание, поле будет удалено!!!',
                    text: arg.event.title,
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Очистить',
                    cancelButtonText:'Отмена'
                }).then((result) => {
                    if(result.value){
                    $.ajax({
                        url: "/schendule/delete",
                        type: "POST",
                        data:"id="+arg.event.id,
                        success: function () {
                            Swal.fire(
                                'Удалено!',
                                'Событие успешно удалено.',
                                'success'
                            );
                            calendar.refetchEvents();

                        }
                    });}





                });

            }


        }

    })
    calendar.render();
   </script>
<script type="text/javascript">
    $(document).ready(
        function() {
            $('#datepicker').datepicker(
                { dateFormat: 'yy-mm-dd' }
               );
        }
    );
</script>

</body>
</html>

