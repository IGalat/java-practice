<%--suppress XmlDuplicatedId --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>Student Information</h2>
<%-- 'commandName' corresponds with ModelMap attribute. Default name is 'command', so we can omit setting commandName --%>
<form:form method="POST" commandName="command123" action="addStudent">
    <table>
        <tr>
                <%-- 'duplicate id reference' is ok --%>
            <td><form:label path="name">Name</form:label></td>
                <%-- 'input' goes into object(new Student()) that was passed? --%>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="age">Age</form:label></td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td><form:label path="id">id</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>