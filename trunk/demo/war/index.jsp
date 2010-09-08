<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>
<c:if test="${not empty sessionScope.user}">
    <c:redirect url="signedin.jsp" />
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.8.1/build/reset-fonts-grids/reset-fonts-grids.css" />
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.8.1/build/base/base-min.css" />
        <link rel="stylesheet" type="text/css" href="/css/main.css" />
        <title>janrain4j demo</title>
    </head>
    <body>
        <div id="doc">
            <div id="hd">
            </div>
            <div id="bd">
                <h1>index.jsp</h1>
                
                <c:if test="${not empty param.error}">
                    <div class="error">${param.error}</div>
                </c:if>
                
                <c:if test="${not empty param.message}">
                    <div class="message">${param.message}</div>
                </c:if>
                
                <janrain:signInEmbedded />
            </div>
            <div id="ft">
            </div>
        </div>
    </body>
</html>
