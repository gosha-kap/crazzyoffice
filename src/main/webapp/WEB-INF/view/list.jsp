<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-16"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>График дежурств</title>

</head>
<body>
<div>

<table align="center" id="navigate">
    <tr align="center" >

        <form  action="${pageContext.request.contextPath}/workDay/list-search" method="post">
            <input type="hidden" name="month" value="${month}">
            <input type="hidden" name="year" value="${year}">
            <td>
            <input type="submit" name="previous" value="<<">
        </td>
        <td>
            <h3 align ="center" >${date}</h3>
        </td>
        <td>
             <input type="submit" name="next" value=">>">
        </td>
        </form>
    </tr>
</table>

<table border="1" align="center">
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


    <!-- loop over and print our customers -->


</table>
    <table align="center">
        <tr>

            <td>
                <br>
                <form  action="${pageContext.request.contextPath}/workDay/list-search" method="post">
                    <input type="hidden" name="month" value="${month}">
                    <input type="hidden" name="year" value="${year}">
                    <input type="submit" name = "edit" value="Редактировать">
                </form>
            </td>

        </tr>
    </table>

</div>
</body>
</html>
