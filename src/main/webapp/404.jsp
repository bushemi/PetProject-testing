<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Page not found. 404</title>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
    </style>
    <script>
      function back() {
        window.history.back();
      }
    </script>
</head>
<body>
<h1>Ошибка 404. Страница не найдена.</h1>
<br/>
<div>
    <button onclick="back()">Вернуться</button>
</div>
<br/>
</body>
</html>
