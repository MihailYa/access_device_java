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
    <style>
        <%@ include file="css/accessDevice.css"%>
    </style>
</head>
<body>
<div class="accessDeviceDiv">
    <form action="AccessDevice" method="post">
        <p>Door lock status: ${isDoorLocked}</p>
        <p>Bell status: ${isBellRinging}</p>
        <input type="hidden" name="command" value="buttonsPanelCommand">
        <table cellspacing="0">
            <tr><td colspan="3" class="guiDisplay"><p>${message}</p></td></tr>
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
                <td class="callTd"><input type="submit" name="callButton" value="CALL" class="numberButton"></td>
                <td><input type="submit" name="numberButton" value="0" class="numberButton"></td>
                <td><input type="submit" name="controlButton" value="CONTROL" class="numberButton"></td>
            </tr>
            <tr>
                <td class="holdCall" colspan="3"><label>
                    Hold CALL
                    <input type="checkbox" name="callButtonPress" value="CALL" class="numberButton" onChange="this.form.submit()" ${callButtonChecked}/>
                </label></td>
            </tr>
        </table>
    </form>
    <form action="AccessDevice" method="post">
        <input type="hidden" name="command" value="accessCardRecipientCommand">
        <label>
            Access card id:<br>
            <input type="text" name="accessCardId" value="${insertedCardId}" required>
        </label>
        <br>
        <br>
        <input type="submit"  class="defaultInput">
    </form>
</div>

<br>
<form action="AccessDevice" method="post" class="loginForm" style="margin-top: 20px;">
    <input type="hidden" name="command" value="adminLoginCommand">
    <label>
        Login <br>
        <input type="text" name="adminLogin">
    </label>
    <br>
    <br>
    <label>
        Password <br>
        <input type="password" name="adminPassword">
    </label>
    <br>
    <br>
    <input type="submit" class="defaultInput">
</form>
</body>
</html>
