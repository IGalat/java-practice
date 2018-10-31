<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java configured!</title>
</head>
<body>
<c:if test="${not empty name}">Hello ${name}!</c:if>
<c:if test="${empty name}">Desu des!</c:if>
</body>
</html>
