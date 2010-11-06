<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.google.appengine.api.utils.SystemProperty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>
<html tab="account">
    <body>
        <div class="half-content-top title">
            <div class="span-10">
                <h2>Mappings</h2>
            </div>
            <div class="title-right-tools span-7 last">
                <a class="item" href="<c:url value="/account/delete" />" onclick="return confirm('Are you sure?');">Delete Account</a>
                <%  String tokenUrl = null;
                    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                        tokenUrl = "http://janrain4j.appspot.com/account/map";
                    }
                    else {
                        tokenUrl = "http://localhost:8080/account/map";
                    }
                    pageContext.setAttribute("tokenUrl", tokenUrl);
                 %>
                <janrain:signInLink styleClass="item" tokenUrl="${tokenUrl}">Add an Identifier</janrain:signInLink>
            </div>
        </div>
        <div class="half-content">
            <div class="additional-tools divider clearfix">
            </div>
            <div class="inner">
                <table class="table">
                    <tr>             
                        <th>Identifier</th>
                        <th class="last"></th>
                    </tr>
                    <c:forEach items="${mappings}" var="mapping" varStatus="status">
                        <tr class="${status.index % 2 == 0 ? 'odd' : 'even'}">
                            <td>${mapping}</td>
                            <td class="last">
                                <ul class="actions-nav">
                                    <c:url value="/account/unmap" var="url">
                                        <c:param name="identifier" value="${mapping}"/>
                                    </c:url>
                                    <li><a class="mini-button red" href="${url}" onclick="return confirm('Are you sure?');">Unmap</a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="half-content-bottom"></div>
        <janrain:signInOverlay flags="show_provider_list" />
    </body>
</html>
