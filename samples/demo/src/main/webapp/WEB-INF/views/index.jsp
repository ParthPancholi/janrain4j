<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>
<html tab="sign_in">
    <body>
        <div class="half-content-top title">
            <h2>Sign In</h2>
        </div>
        <div class="half-content">
            <div class="additional-tools divider clearfix">
            </div>
            <div class="inner">
                <c:choose>
                    <c:when test="${empty primaryKey}">
                        <janrain:signInEmbedded flags="hide_sign_in_with" />
                    </c:when>
                    <c:otherwise>
                        You are already signed in. TODO <a href="/sign_out">Sign out</a> to sign in with a different account.
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="half-content-bottom"></div>
    </body>
</html>
