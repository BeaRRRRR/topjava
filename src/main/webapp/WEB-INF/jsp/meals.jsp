<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="meal.title"/></h3>

    <form method="post" action="meals/filter">
        <dl>
            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="meal.filter"/></button>
    </form>
    <hr>
    <button type="button" class="btn btn-success" data-toggle="modal" data-toggle="#addMeal">Добавить еду</button>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr data-mealExceed="${meal.exceed}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>

<div class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
                <h3><spring:message code="${meal.isNew() ? 'meal.add' : 'meal.edit'}"/></h3>
                <hr>
                <form id="detailsForm">
                    <input type="hidden" name="id" value="${meal.id}">
                    <div>
                        <label class="col-form-label" for="dateTime"><spring:message code="meal.dateTime"/>:</label>
                        <input class="form-control" type="datetime-local" value="${meal.dateTime}" name="dateTime" id="dateTime" required>
                    </div>
                    <div>
                        <<label for="description"></label>><spring:message code="meal.description"/>:</label>
                        <input type="text" value="${meal.description}" size=40 name="description" id="description" placeholder="<spring:message code="meal.description"/>" required/>
                    </div>
                    <div>
                        <label for="calories"><spring:message code="meal.calories"/>:</label>
                        <input type="number" value="${meal.calories}" name="calories" id="calories" placeholder="<spring:message code="meal.calories"/>" required/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.cancel"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>