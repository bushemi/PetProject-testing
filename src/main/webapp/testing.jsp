<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Processing the test</title>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
        
        .startButton {
            width: 20%;
            height: 10%;
            font-size: 25px;
            background-color: chartreuse;
            border-radius: 15px;
        }
    </style>
    <script>
      function toMainMenu() {
        window.location.href = "navigation";
      }
      
      function startTest() {
        window.location.href = "/questions";
      }
      
    </script>
</head>
<body>
<h1>Прохождение теста</h1>
<br/>
<div>
    <button onclick="toMainMenu()">В главное меню</button>
</div>
<br/>
    <button class="startButton" onclick="startTest()">Начать тест</button>
</body>
</html>
