<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.googlecode.janrain4j.api.engage.*" %>
<%@ page import="com.googlecode.janrain4j.api.engage.response.*" %>
<%@ page import="com.googlecode.janrain4j.demo.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<jsp:include page="_flash.jsp" />

<%  // create services
    EngageService engageService = EngageServiceFactory.getEngageService();

    // get signed in primary key
    Long primaryKey = (Long) session.getAttribute("primaryKey");
    
    // get mapped identifiers
    MappingsResponse mappingsResponse = engageService.mappings(String.valueOf(primaryKey));
    List<String> mappings = mappingsResponse.getMappings();
    pageContext.setAttribute("mappings", mappings);
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="account" />
        </jsp:include>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <div class="span-10">
                        <h2>Mappings</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a class="item" href="/delete_account" onclick="return confirm('Are you sure?');">Delete Account</a>
                        <janrain:signInLink styleClass="item" tokenUrl="http://localhost:8888/map">Add an Identifier</janrain:signInLink>
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
                                            <c:url value="/unmap" var="url">
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
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
        
        <janrain:load />
        <janrain:signInOverlay flags="show_provider_list" />
    </body>
</html>
