<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${not empty userData.responseAsJSONObject}">
    <spring:eval var="plainResponse" expression="userData.responseAsJSONObject.toString(2)" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
    </head>
    <body>
        <h1>User Data</h1>
        <table>
            <tr>
                <td class="label"><label>Identifier</label></td>
                <td>${userData.profile.identifier}</td>
            </tr>
            <tr>
                <td class="label"><label>Provider Name</label></td>
                <td>${userData.profile.providerName}</td>
            </tr>
            <tr>
                <td class="label"><label>Plain JSON Response</label></td>
                <td><pre style="white-space:pre-wrap;">${plainResponse}</pre></td>
            </tr>
        </table>
        <a href="<c:url value="/sign_out" />">Sign Out</a>
    </body>
</html>
