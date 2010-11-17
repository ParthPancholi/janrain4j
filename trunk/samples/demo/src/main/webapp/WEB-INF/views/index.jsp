<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html tab="sign_in">
    <body>
        <div class="half-content-top title">
            <h2>Sign In</h2>
        </div>
        <div class="half-content">
            <div class="additional-tools divider clearfix">
            </div>
            <div class="inner">
                <sec:authorize access="isAuthenticated()">
                    You are already signed in. <a href="<c:url value="/sign_out" />">Sign out</a> to sign in with a different account.
                </sec:authorize>
                <sec:authorize access="not isAuthenticated()">
                    <janrain:signInEmbedded flags="hide_sign_in_with" />
                </sec:authorize>
            </div>
        </div>
        <div class="half-content-bottom"></div>
    </body>
</html>
