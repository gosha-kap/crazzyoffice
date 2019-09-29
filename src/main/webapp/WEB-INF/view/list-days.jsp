
<%@ page language="java" contentType="text/html; charset=UTF-16"
         pageEncoding="UTF-16"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-16">
    <title>javaguides.net</title>

<head/>
<body>

                <table border="1">
                    <tr>
                        <th>date</th>
                        <th>Driver</th>
                        <th>Pologaya</th>
                        <th>on Ceil</th>
                        <th>Action</th>

                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="workdays" items="${workdays}">

                        <c:url value="/workDay/edit" var="update">
                            <c:param name="workDayId" value="${workdays.id}"/>
                        </c:url>

                        <tr>
                            <td>${workdays.date}</td>
                            <td>${workdays.dailyDriver}</td>
                            <td>${workdays.allDayWatcher}</td>
                            <td>${workdays.hollidayWatcher}</td>
                            <td><a href="${update}">Edit</a> </td>
                        </tr>

                    </c:forEach>

                </table>




</body>

</html>