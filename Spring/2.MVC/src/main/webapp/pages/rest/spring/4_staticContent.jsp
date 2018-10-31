<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cat Face!</title>
    <%-- using JSTL(see maven) tag --%>
    <spring:url value="/pics/leCat.jpg" var="kitty"/>
    <c:url value="/pics/leCat.jpg" var="cat"/>
</head>
<body>
<img src="${kitty}">
<img src="${cat}">
<img src="/pics/leCat.jpg">
</body>
</html>
