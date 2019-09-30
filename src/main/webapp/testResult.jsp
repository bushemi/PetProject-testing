<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Test result</title>
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
      
      function toAllTests() {
        window.location.href = "tests";
      }
    </script>
</head>
<body>
<h1>Результат выполнения теста</h1>
<br/>
<div>
    <button onclick="toMainMenu()">В главное меню</button>
    <button onclick="toAllTests()">Все тесты</button>
</div>
<br/>
<c:if test="${not empty sessionScope.countRightAnswers}">
    <p>Всего правильных ответов: ${sessionScope.countRightAnswers}</p>
    <p>Затрачено времени: ${sessionScope.spentTime} секунд</p>
</c:if>

</body>
</html>
