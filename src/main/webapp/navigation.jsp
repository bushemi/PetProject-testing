<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Navigation</title>
</head>

<body>
<h1>Навигация</h1>
<br/>
<a href="userProfile"><h3>Profile</h3></a>
<a href="tests"><h3>Tests</h3></a>
<c:if test="${not empty sessionScope.role}">
    <c:if test="${sessionScope.role == 'admin'}">
        <a href="users"><h3>Users</h3></a>
    </c:if>
</c:if>
<a href="login"><h3>logout</h3></a>

</body>
</html>
