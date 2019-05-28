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
    <link rel="stylesheet" href="css/adminPanel.css">
    <script src="jquery-3.4.1.min.js">
    </script>
    <script>
        function onEditClick(button) {
            var parentTr = $(button).closest('tr');

            parentTr.find('.editCardInputHide').css('display', 'none');
            parentTr.find('.editCardInput').prop('type', 'text');
            parentTr.find('input[type=checkbox]').prop('disabled', false);

            parentTr.find('.editCardButton').css('display', 'inline-block');
        }

        function onCancelClick(button) {
            var parentTr = $(button).closest('tr');

            parentTr.find('.editCardInputHide').css('display', 'inline-block');
            parentTr.find('.editCardInput').prop('type', 'hidden');
            parentTr.find('input[type=checkbox]').prop('disabled', true);

            parentTr.find('.editCardButton').css('display', 'none');
        }

        function onEditSubmitClick(button) {
            var parentTr = $(button).closest('tr');

            var posting = $.post('AdminPanelServlet', parentTr.find('input').serialize());

            posting.done(function( data ) {
                var newDoc = document.open("text/html", "replace");
                newDoc.write(data);
                newDoc.close();
            });

        }

        function onDeleteClick(button) {
            var parentTr = $(button).closest('tr');

            var accessCardId = parentTr.find('input[name=accessCardId]').val();

            var posting = $.post('AdminPanelServlet', {accessCardId: accessCardId, command: "deleteAccessCard"});

            posting.done(function( data ) {
                var newDoc = document.open("text/html", "replace");
                newDoc.write(data);
                newDoc.close();
            });
        }
    </script>
</head>
<body>
<form action="AdminPanelServlet" method="post">
    <input type="hidden" name="command" value="logoutCommand">
    <input type="submit" value="Logout" class="defaultInput">
</form>

<h1 style="margin-top: 20px;">AccessCards table</h1>

<c:if test="${errorMessage != null}">
<p class="errorMessageP">${errorMessage}</p>
</c:if>

<table class="simpleTable" cellspacing="0">
    <tr>
        <th>AccessCardId</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Schedule begin time</th>
        <th>Schedule end time</th>
        <th>Is card locked</th>
        <th>Action</th>
    </tr>
    <c:forEach var="accessCard" items="${accessCards}">

            <tr>
                <input type="hidden" name="command" value="updateAccessCard">
                <input type="hidden" name="accessCardId" value="${accessCard.getId()}">
                <input type="hidden" name="accessCardPersonId" value="${accessCard.getPerson().getId()}">
                <input type="hidden" name="accessCardScheduleId" value="${accessCard.getSchedule().getId()}">
                <td>
                    <p>${accessCard.getId()}</p>
                </td>
                <td>
                    <p class="editCardInputHide">${accessCard.getPerson().getName()}</p>
                    <input class="editCardInput" type="hidden" name="accessCardPersonName" placeholder="Name" value="${accessCard.getPerson().getName()}">
                </td>
                <td>
                    <p class="editCardInputHide">${accessCard.getPerson().getSurname()}</p>
                    <input class="editCardInput" type="hidden" name="accessCardPersonSurname" placeholder="Surname" value="${accessCard.getPerson().getSurname()}">
                </td>
                <td>
                    <p class="editCardInputHide">${accessCard.getSchedule().getBeginTime()}</p>
                    <input class="editCardInput" type="hidden" name="accessCardScheduleBeginTime" placeholder="Begin time" value="${accessCard.getSchedule().getBeginTime()}">
                </td>
                <td>
                    <p class="editCardInputHide">${accessCard.getSchedule().getEndTime()}</p>
                    <input class="editCardInput" type="hidden" name="accessCardScheduleEndTime" placeholder="End time" value="${accessCard.getSchedule().getEndTime()}">
                </td>
                <td>
                    <input type="checkbox" name="isAccessCardLocked" onChange="this.form.submit()"
                            <c:if test="${accessCard.isLocked()}">
                                checked
                            </c:if>
                           disabled/>
                </td>
                <td>
                    <button class="defaultInput editCardInputHide" type="button"
                            onclick="onEditClick(this)">Edit
                    </button>
                    <button class="defaultInput editCardButton" type="button" onclick="onEditSubmitClick(this)">Update
                    </button>
                    <button class="defaultInput editCardButton" type="button"
                            onclick="onCancelClick(this)">Cancel
                    </button>

                    <button class="defaultInput editCardInputHide" type="button"
                            onclick="onDeleteClick(this)">Delete
                    </button>
                </td>
            </tr>
    </c:forEach>

    <form action="AdminPanelServlet" method="post">
        <input type="hidden" name="command" value="insertAccessCard">
        <tr>
            <td>Create new</td>
            <td><input type="text" name="accessCardPersonName" placeholder="Name"></td>
            <td><input type="text" name="accessCardPersonSurname" placeholder="Surname"></td>
            <td><input type="text" name="accessCardScheduleBeginTime" placeholder="Begin time"></td>
            <td><input type="text" name="accessCardScheduleEndTime" placeholder="End time"></td>
            <td><input type="checkbox" name="isAccessCardLocked"></td>
            <td><input type="submit" value="Submit" class="defaultInput"></td>
        </tr>
    </form>
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
