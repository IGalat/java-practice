<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring Page Redirection</title>
</head>

<body>
<%-- this will show only if called from method that has {headerH2} mapped(index). Otherwise not even empty space --%>
<h2>${headerH2}</h2>
<%-- this will only show if called from .unusualURL method --%>
<h2>${TextForUnusualURL}</h2>

<p>Click below button to redirect the result to new page</p>

<%-- same as in Controller class:  action = "/redirect"  would be absolute path:  localhost:8080/redirect  --%>
<form:form method="GET" action="redirect">
    <table>
        <tr>
            <td>
                <input type="submit" value="Redirect Page"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>