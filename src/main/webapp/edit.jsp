<%--
  Created by IntelliJ IDEA.
  User: misha
  Date: 03.07.2018
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>edit</title>
</head>
<body>
    <form method="post">
        <label for="date" >Дата/Время</label>
        <input id="date" type="datetime-local" name="date">

        <input type="hidden" id="id" value="${id}">

        <label for="desc">Описание</label>
        <input type="text" id="desc" name="desc">

        <label for="calories"></label>
        <input type="number" id="calories" name="calories">
    </form>
</body>
</html>
