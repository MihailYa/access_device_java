<%--
  Created by IntelliJ IDEA.
  User: mikle
  Date: 20.05.2019
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin panel</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<form action="AdminPanelServlet" method="post">
    <input type="hidden" name="command" value="logoutCommand">
    <input type="submit" value="Logout" class="defaultInput">
</form>

<h1 style="margin-top: 20px;">AccessCards table</h1>
<table class="simpleTable" cellspacing="0">
    <tr>
        <th>AccessCardId</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Schedule begin time</th>
        <th>Schedule end time</th>
        <th>Is card locked</th>
    </tr>
    <c:forEach var="accessCard" items="${accessCards}">

        <form action="AdminPanelServlet" method="post">
            <input type="hidden" name="command" value="updateAccessCard">
            <tr>
                <td>
                    <input type="hidden" name="accessCardId" value="${accessCard.getId()}">
                    <input type="hidden" name="accessCardPersonId" value="${accessCard.getPerson().getId()}">
                    <input type="hidden" name="accessCardScheduleId" value="${accessCard.getSchedule().getId()}">
                        ${accessCard.getId()}</td>
                <td>${accessCard.getPerson().getName()}</td>
                <td>${accessCard.getPerson().getSurname()}</td>
                <td>${accessCard.getSchedule().getBeginTime()}</td>
                <td>${accessCard.getSchedule().getEndTime()}</td>
                <td>
                    <input type="checkbox" name="isAccessCardLocked" onChange="this.form.submit()"
                        <c:if test="${accessCard.isLocked()}">
                            checked
                        </c:if>
                /></td>
            </tr>
        </form>
    </c:forEach>
</table>
<h1 style="margin-top: 20px;">Visitors journal</h1>
<table class="simpleTable">
    <tr>
        <th>RecordId</th>
        <th>AccessCardId</th>
        <th>Visit date</th>
    </tr>

    <c:forEach var="visitRecord" items="${visitRecords}">
        <tr>
            <td>${visitRecord.getId()}</td>
            <td>${visitRecord.getAccessCardId()}</td>
            <td>${visitRecord.getVisitDateTime()}</td>
        </tr>
    </c:forEach>
</table>

<h1 style="margin-top: 20px;">Cards locking journal</h1>
<table class="simpleTable">
    <tr>
        <th>RecordId</th>
        <th>AccessCardId</th>
        <th>Locking date</th>
    </tr>
    <c:forEach var="lockCardRecord" items="${lockCardRecords}">
        <tr>
            <td>${lockCardRecord.getId()}</td>
            <td>${lockCardRecord.getAccessCardId()}</td>
            <td>${lockCardRecord.getLockingDateTime()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
