<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Test In Process</title>
    <script>
      <c:if test="${not empty sessionScope.finalTime}">
      let countDownDate = new Date(${sessionScope.finalTime}).getTime();
      
      let x = setInterval(function () {
        
        let now = new Date().getTime();
        
        let distance = countDownDate - now;
        
        let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((distance % (1000 * 60)) / 1000);
        
        document.getElementById("timer").innerHTML = "Осталось времени: " + minutes + "m " + seconds + "s ";
        if (distance < 0) {
          clearInterval(x);
          document.getElementById("timer").innerHTML = "Время кончилось!";
        }
      }, 1000);
      </c:if>
    </script>
    <style>
        .timerClass {
            height: 10%;
            font-size: 20px;
            color: red;
            border: 3px solid black;
        }
        
        #question {
            height: 30%;
            width: 30%;
        }
    </style>
</head>
<body>
<h1>Прохождение теста</h1>
<br/>
<div class="timerClass">
    <p id="timer"></p>
</div>
<div>
    <button onclick="toMainMenu()">Закончить тест!</button>
</div>
<c:if test="${not empty sessionScope.question}">
    <form action="/questions" method="post">
        <div>
            <label for="question">Вопрос:</label>
            <textarea disabled id="question" maxlength="255">${sessionScope.question.mainText}</textarea>
        </div>
        <c:forEach items="${sessionScope.question.options}" var="option">
            <div>
                <label for="option-${option.id}">${option.mainText}</label>
                <input name="${option.id}" id="option-${option.id}" type="checkbox"/>
            </div>
        </c:forEach>
        <button id="submitButton" type="submit" class="btn btn-primary">Подтвердить</button>
    </form>
</c:if>
</body>
</html>
