<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All tests</title>
    <style>
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
        
        button {
            width: 10%;
            background-color: aquamarine;
        }
        
        button:disabled {
            background-color: darksalmon;
        }
        
        .selected {
            background-color: darkolivegreen;
        }
    
    </style>
    <script>
      let selectedTest = 0;
      
      function handleDisablingButtons() {
        if (selectedTest !== 0) {
          let startTestButton = document.getElementById("startTestButton");
          startTestButton.disabled = false;
          let deleteTestButton = document.getElementById("deleteTestButton");
          deleteTestButton.disabled = false;
          let editTestButton = document.getElementById("editTestButton");
          editTestButton.disabled = false;
        } else {
          let startTestButton = document.getElementById("startTestButton");
          startTestButton.disabled = true;
          let deleteTestButton = document.getElementById("deleteTestButton");
          deleteTestButton.disabled = true;
          let editTestButton = document.getElementById("editTestButton");
          editTestButton.disabled = true;
        }
        console.log("selected test", selectedTest);
      }
      
      function onTestClick(e) {
        console.log("pressed on", e.id);
        const isTestSelected = e.className.includes("selected");
        deselectAllTests();
        if (!isTestSelected) {
          e.className = e.className + " selected";
          selectedTest = e.id;
        } else {
          selectedTest = 0;
        }
        handleDisablingButtons();
      }
      
      function deselectAllTests() {
        const elementsByClassName = document.getElementsByClassName("selected");
        console.log(elementsByClassName);
        for (let element of elementsByClassName) {
          element.className = element.className.replace(" selected", "");
        }
      }
      
      function toMainMenu() {
        window.location.href = "navigation";
      }
      
      function startTest() {
        window.location.href = "/testToGo/" + selectedTest;
      }
      
      function addTest() {
        window.location.href = "/addTest";
      }
    
    </script>
</head>

<body>
<div>
    <button onclick="toMainMenu()">В главное меню</button>
    <button id="startTestButton" disabled onclick="startTest()">Начать тест</button>
    <c:if test="${not empty sessionScope.role}">
        <c:if test="${sessionScope.role == 'admin'}">
            <button id="deleteTestButton" disabled>Удалить тест</button>
            <button id="editTestButton" disabled>Редактировать тест</button>
            <button id="newTestButton" onclick="addTest()">Добавить новый тест</button>
        </c:if>
    </c:if>
</div>
<h3>Доступные для прохождения тесты</h3>
<table>
    <thead>
    <tr>
        <th rowspan="2">Предмет</th>
        <th rowspan="2">Название теста</th>
        <th rowspan="2">Сложность</th>
        <th rowspan="2">Минут на выполнение</th>
        <th colspan="2">Информация о прошлых прохождениях</th>
    </tr>
    <tr>
        <th>Правильных ответов</th>
        <th>Секунд затрачено</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty sessionScope.tests}">
        <c:forEach items="${sessionScope.tests}" var="item">
            <tr id="${item.id}" onclick="onTestClick(this)">
                <td>${item.subject}</td>
                <td>${item.testName}</td>
                <td>${item.difficulty}</td>
                <td>${item.minutesToComplete}</td>
                <td>${item.correctAnswers}</td>
                <td>${item.spentTime}</td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</body>
</html>
