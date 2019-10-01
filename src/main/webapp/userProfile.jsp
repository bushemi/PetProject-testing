<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User info</title>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
    </style>
    <script>
      function toMainMenu() {
        window.location.href = "navigation";
      }
    </script>
</head>
<body>
<h1>Профиль пользователя</h1>
<br/>
<div>
    <button onclick="toMainMenu()">To main menu</button>
    <button>Сохранить изменения</button>
    <button>Удалить пользователя</button>
    <button>Блокировать пользователя</button>
</div>
<div>
    <label for="login">Логин</label>
    <input type="text" class="form-control" id="login"/>
    <br/>
    <button>Сменить пароль</button>
    <p>Всего пройдено тестов: 0</p>
    <p>Средний результат: 10%</p>
</div>
</body>
</html>
