<%@ page import="com.googlecode.janrain4j.springframework.security.JanrainAuthenticationToken" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                <td><sec:authentication property="principal.userDataResponse.profile.identifier" /></td>
            </tr>
            <tr>
                <td class="label"><label>Provider Name</label></td>
                <td><sec:authentication property="principal.userDataResponse.profile.providerName" /></td>
            </tr>
            <tr>
                <%  JanrainAuthenticationToken authentication = (JanrainAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                    String plainResponse = authentication.getUserDataResponse().getResponseAsJSONObject().toString(2);
                    pageContext.setAttribute("plainResponse", plainResponse); %>
                <td class="label"><label>Plain JSON Response</label></td>
                <td><pre style="white-space:pre-wrap;">${plainResponse}</pre></td>
            </tr>
        </table>
        <a href="<c:url value="/sign_out" />">Sign Out</a>
    </body>
</html>
