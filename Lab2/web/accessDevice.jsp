<%--
  Created by IntelliJ IDEA.
  User: mikle
  Date: 19.05.2019
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AccessDevice</title>
    <link rel="shortcut icon" href="">
    <link rel="stylesheet" href="css/accessDevice.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 devicePanelDiv">
            <section class="devicePanel">

                <section>
                    <form action="AccessDevice" method="post" class="accessDeviceForm">
                        <input type="hidden" name="command" value="buttonsPanelCommand">
                        <table cellspacing="0">
                            <tr>
                                <td colspan="3" class="imagesTd">
                                    <div class="imageDiv">
                                        <c:choose>
                                            <c:when test="${isDoorLocked}">
                                                <img src="img/lock-solid.svg" alt="lockedDoor">
                                            </c:when>
                                            <c:otherwise>
                                            <img src="img/lock-open-solid.svg" alt="unLockedDoor">
                                        </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="imageDiv">
                                        <c:choose>
                                            <c:when test="${isBellRinging}">
                                            <img src="img/volume-up-solid.svg" alt="ringingBell">
                                        </c:when>
                                            <c:otherwise>
                                                <img src="img/volume-off-solid.svg" alt="bell">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" class="guiDisplay"><p>${message}</p></td>
                            </tr>
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
                                <td colspan="3">
                                    <section class="holdCall">
                                        <label>
                                            Hold CALL
                                            <input type="checkbox" name="callButtonPress" value="CALL" class="numberButton"
                                                   onChange="this.form.submit()" ${callButtonChecked}/>
                                        </label>
                                    </section></td>
                            </tr>
                        </table>
                    </form>
                </section>
                <section>
                    <form action="AccessDevice" method="post" class="accessDeviceCardRecipient">
                        <input type="hidden" name="command" value="accessCardRecipientCommand">
                        <label>
                            Access card id
                            <br>
                            <input type="text" name="accessCardId" value="${insertedCardId}" required>
                        </label>
                        <br>
                        <input type="submit" class="defaultInput" value="Submit">
                    </form>
                </section>
            </section>
        </div>
        <div class="col-md-6 loginDiv">
            <section class="loginPanel">
                <form action="AccessDevice" method="post" class="loginForm">
                    <h1>Login</h1>
                    <%
                        if(request.getParameter("loginErrorMessage") != null) {
                    %>
                    <p class="errorMessageP">${loginErrorMessage}</p>
                    <%
                        }
                    %>
                    <input type="hidden" name="command" value="adminLoginCommand">
                    <input type="text" name="adminLogin" placeholder="Login">
                    <br>
                    <input type="password" name="adminPassword" placeholder="Password" style="background:none;">
                    <br>
                    <input type="submit" class="defaultInput" value="Submit">
                </form>
            </section>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
