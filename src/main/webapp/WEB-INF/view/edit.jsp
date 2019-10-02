<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-16"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
<table align="center" id="navigate">
    <tr align="center" >


         <td>
            <form  action="${pageContext.request.contextPath}/workDay/list-edit" method="post">
                <input hidden name="month" value="${month}">
                <input hidden name="year" value="${year}">
                <input hidden name="edit" value="exit">
                <input type="submit" value="Назад">
            </form>
        </td>


    </tr>



</table>
<table border="1" align="center" id="grid">
    <tr>
        <th></th>
        <c:forEach var="days" items="${days}">
            <td  style="color: ${days.value ? 'red' : 'black'};width: 20px;">${days.key}</td>
        </c:forEach>
    </tr>

    <c:forEach var="persons" items="${persons}">
        <tr>
            <td>
                    ${persons.alias}
            </td>

            <!-- Test Grid Table-->
            <c:forEach var="days" items="${days}">

                <td style="color: ${days.value ? 'red' : 'black'}">

                    <c:forEach var="workdays" items="${workdays}">
                        ${workdays.date eq days.key and persons.id eq workdays.dailyDriver ? 'D' :' '}
                        ${workdays.date eq days.key and persons.id eq workdays.hollidayWatcher ? 'X' :' '}
                        ${workdays.date eq days.key and persons.id eq workdays.allDayWatcher ? 'C' :' '}
                    </c:forEach>
                </td>


            </c:forEach>

            <!---->

        </tr>
    </c:forEach>

</table>

<table align="center" id="edit">
    <tr>

     <br>

    <form action="${pageContext.request.contextPath}/workDay/list-edit" method="post">

                <input hidden name="month" value="${month}">
                <input hidden name="year" value="${year}">
                <td>
                         ${date}-<select name="day">
                            <c:forEach var="days" items="${days}">
                            <option value="${days.key}">${days.key}</option>
                            </c:forEach>
                            </select>
                </td>
                <td>
                      <select name="event">
                        <option value="driver">Водитель</option>
                        <option value="pologaya">На сутках</option>
                        <option value="holidays">На телефоне</option>
                </select>
                </td>
                <td>
                     <select name="person">
                    <c:forEach var="persons" items="${persons}">
                      <option value="${persons.id}">${persons.alias}</option>
                    </c:forEach>
                </select>
                </td>
                <td>
                    <input type="submit" name = "save" value="Сохранить">
                </td>
            </form>
    </tr>
    <tr>
               <form  action="${pageContext.request.contextPath}/workDay/list-edit" method="post">

                    <input hidden name="month" value="${month}">
                    <input hidden name="year" value="${year}">
                     <td>
                        ${date}-<select name="day">
                        <c:forEach var="workdays" items="${workdays}">
                            <option value="${workdays.date}">${workdays.date}</option>
                        </c:forEach>
                    </select>
                    </td>
                   <td>
                      -------
                   </td>
                    <td>
                        <select name="person">
                            <c:forEach var="persons" items="${persons}">
                                <option value="${persons.id}">${persons.alias}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>

                        <input type="submit" name = "del" value="Удалить">
                    </td>
                </form>





    </tr>
</table>


</body>
</html>
