<%--
  Created by IntelliJ IDEA.
  User: kaptep
  Date: 29.08.19
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=utf-16be"
         pageEncoding="utf-16be"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <title>Edit</title>
</head>
<body>
    <form:form action="saveWorkDay"  method="POST">
    <form:hidden path="id"/>
    <table>
        <tbody>
        <tr>
            <td><label>date: </label></td>
            <td><form:input path="date" /></td>
        </tr>

        <tr>
            <td><label>dailyDriver: </label></td>
            <td><form:input path="dailyDriver" /></td>
        </tr>

        <tr>
            <td><label>allDayWatcher:</label></td>
            <td><form:input path="allDayWatcher"/></td>
        </tr>


        <tr>
            <td><label>hollidayWatcher:</label></td>
            <td><form:input path="hollidayWatcher" /></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" /></td>
        </tr>


        </tbody>
    </table>


</form:form>
</body>
</html>
