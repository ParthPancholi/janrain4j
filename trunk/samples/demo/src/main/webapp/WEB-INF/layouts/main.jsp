<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <head>
        <title>Janrain4j Demo Application</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/maclike-webapp-theme/stylesheets/blueprint/screen.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/maclike-webapp-theme/stylesheets/blueprint/plugins/tabs/screen.css" />" />
        <!--[if IE]><link rel="stylesheet" type="text/css" href="<c:url value="/static/maclike-webapp-theme/stylesheets/blueprint/ie.css" />" /><![endif]-->
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/maclike-webapp-theme/stylesheets/base.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css" />" />
        <script language="javascript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <decorator:head />
    </head>
    <body>
        <c:set var="tab">
            <decorator:getProperty property="tab" default="sign_in" />
        </c:set> 
        <jsp:include page="_top.jsp">
            <jsp:param name="tab" value="${tab}" />
        </jsp:include>
        <div class="container">
            <div class="half-content-wrapper span-17">
                <decorator:body />
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
