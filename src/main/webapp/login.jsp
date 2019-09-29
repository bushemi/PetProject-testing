<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          type="text/css">
    <style>
        .warning {
            color: red;
            font-size: 15px;
        }
        
        .redBorder {
            border: 3px solid red;
        }
    </style>
    <script>
      function onNewUserChange(e) {
        let mainTitle = document.getElementById("mainTitle");
        let submitButton = document.getElementById("submitButton");
        mainTitle.innerText = e.checked ? "Регистрация" : "Авторизация";
        submitButton.innerText = e.checked ? "Зарегистрироваться" : "Войти";
        e.value = e.checked;
      }
    </script>
</head>
<body>

<div>
    <h1 id="mainTitle">Авторизация</h1>
</div>
<br/>
<form action="/users" method="post">
    <div class="form-group">
        <label for="newUser">Новый пользователь?</label>
        <input name="isNewUser" id="newUser" type="checkbox" onchange="onNewUserChange(this)"/>
    </div>
    <c:if test="${not empty sessionScope.loginError}">
        <c:set var="loginMessage" value="${sessionScope.loginError}"/>
        <c:set var="loginClass" value="redBorder"/>
        <%
            session.setAttribute("loginError", null);
        %>
    </c:if>
    <c:if test="${not empty sessionScope.passwordError}">
        <c:set var="passwordMessage" value="${sessionScope.passwordError}"/>
        <c:set var="passwordClass" value="redBorder"/>
        <%
            session.setAttribute("passwordError", null);
        %>
    </c:if>
    <div class="form-group">
        <label for="registrationLogin">Логин</label>
        <input name="login" type="text" class="form-control ${loginClass}" id="registrationLogin"
               placeholder="Введите логин"/>
        <label class="warning">${loginMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationPassword">Пароль</label>
        <input name="password" type="password" class="form-control ${passwordClass}" id="registrationPassword"
               placeholder="Пароль"/>
        <label class="warning">${passwordMessage}</label>
    </div>
    <button id="submitButton" type="submit" class="btn btn-primary">Войти</button>
</form>

</body>
</html>
