<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            background-color: beige;
        }
        
        div {
            text-align: center
        }
        
        a {
            border: 2px solid black;
            font-size: 25px;
            padding: 5px;
            border-radius: 10px;
            background-color: white;
        }
    </style>
</head>
<body>
<h1>Приветствуем вас на главной странице.</h1>
<h1>Войдите в систему, чтобы начать.</h1>
<div>
    <a href="login">Войти</a>
</div>
</body>
</html>