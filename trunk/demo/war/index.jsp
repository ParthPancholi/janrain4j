<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:set var="flashScope" scope="request" value="${sessionScope.flash}" />
<c:remove var="flash" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="signin" />
        </jsp:include>
        
        <div class="container secondary-navigation">
            <div class="span-18 breadcrumb">
                <a href="#" id="start" class="item">home</a>
                <a href="#" class="item">outro ítem</a>
                <a href="#" class="item">outro ítem um pouco maior</a>
            </div>
            <div class="span-6 last generic-tools">
                <a href="#" class="item">outro ítem</a>
                <a href="#" class="item">outro ítem </a>
            </div>
        </div>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <h2>Sign In</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <c:if test="${empty sessionScope.primaryKey}">
                            <janrain:signInEmbedded flags="hide_sign_in_with" />
                        </c:if>
                        <c:if test="${not empty sessionScope.primaryKey}">
                            You are already signed in. <a href="/sign_out">Sign out</a> to sign in with a different account.
                        </c:if>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>