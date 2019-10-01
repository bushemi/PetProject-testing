<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All users</title>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
        
        table {
            border: 2px solid black;
            width: 80%;
        }
        
        th {
            background-color: aquamarine;
        }
        
        tr {
            background-color: darksalmon;
        }
        
        .selected {
            background-color: darkolivegreen;
        }
    </style>
    <script>
      function toMainMenu() {
        window.location.href = "navigation";
      }
      
      function onUserClick(e) {
        console.log("pressed on", e.id);
        const isUserSelected = e.className.includes("selected");
        deselectAllUsers();
        if (!isUserSelected) {
          e.className = e.className + " selected";
        }
      }
      
      function deselectAllUsers() {
        const elementsByClassName = document.getElementsByClassName("selected");
        console.log(elementsByClassName);
        for (let element of elementsByClassName) {
          element.className = element.className.replace(" selected", "");
        }
      }
    </script>
</head>
<body>
<h1>Список пользователей</h1>
<br/>
<div>
    <button onclick="toMainMenu()">To main menu</button>
    <button>блокировать/разблокировать пользователя</button>
</div>
<br/>
<table>
    <thead>
    <tr>
        <th>Логин</th>
        <th>Заблокирован?</th>
        <th>Уровень доступа(Роль)</th>
        <th>Локаль</th>
    </tr>
    </thead>
    <tbody>
    <tr id="1" onclick="onUserClick(this)">
        <td>admin</td>
        <td>false</td>
        <td>admin</td>
        <td>ru</td>
    </tr>
    <tr id="2" onclick="onUserClick(this)">
        <td>root</td>
        <td>true</td>
        <td>student</td>
        <td>en</td>
    </tr>
    </tbody>
</table>
</body>
</html>
