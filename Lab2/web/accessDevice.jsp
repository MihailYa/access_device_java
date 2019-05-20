<%--
  Created by IntelliJ IDEA.
  User: mikle
  Date: 19.05.2019
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AccessDevice</title>
</head>
<body>
<p>${message}</p>
<p>Door lock status: ${isDoorLocked}</p>
<p>Bell status: ${isBellRinging}</p>
<form action="AccessDevice" method="post">
    <input type="hidden" name="command" value="buttonsPanelCommand">
    <table>
        <tr>
            <td><input type="submit" name="numberButton" value="1" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="2" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="3" class="numberButton"></td>
        </tr>
        <tr>
            <td><input type="submit" name="numberButton" value="4" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="5" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="6" class="numberButton"></td>
        </tr>
        <tr>
            <td><input type="submit" name="numberButton" value="7" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="8" class="numberButton"></td>
            <td><input type="submit" name="numberButton" value="9" class="numberButton"></td>
        </tr>
        <tr>
            <td><input type="submit" name="numberButton" value="0" class="numberButton"></td>
            <td><input type="submit" name="callButton" value="CALL" class="numberButton"></td>
            <td><label>
                <input type="checkbox" name="callButtonPress" value="CALL" class="numberButton" onChange="this.form.submit()" ${callButtonChecked}/>
                Press CALL button
            </label></td>
            <td><input type="submit" name="controlButton" value="CONTROL" class="numberButton"></td>
        </tr>
    </table>
</form>
<form action="AccessDevice" method="post">
    <input type="hidden" name="command" value="accessCardRecipientCommand">
    <label>
        Access card id:
        <input type="text" name="accessCardId" value="${insertedCardId}" required>
    </label>
    <input type="submit">
</form>

<form action="AccessDevice" method="post">
    <input type="hidden" name="command" value="adminLoginCommand">
    <label>
        Login
        <input type="text" name="adminLogin">
    </label>
    <label>
        Password
        <input type="password" name="adminPassword">
    </label>
    <input type="submit">
</form>
</body>
</html>
