<%--
  Created by IntelliJ IDEA.
  User: misha
  Date: 02.07.2018
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>meals</title>
</head>
<body>
    <div>
        <table class="table table-bordered table-hover horizontal-align">
            <thead>
                <tr>
                    <th>Дата\Время</th>
                    <th>Описание</th>
                    <th>Калории</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="meal"  items="${meals}">
                    <tr style="${meal.isExceed() ? 'background-color: #f44242' : ''}">
                        <td>${meal.getDateTime().format(formatter)}</td>
                        <td>${meal.getDescription()}</td>
                        <td>${meal.getCalories()}</td>
                        <td>
                            <a href="<c:url value="/meals/edit">
                                    <c:param name="id" value="${meals.indexOf(meal)}"/>
                            </c:url>">Edit</a>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
