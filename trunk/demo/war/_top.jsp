<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="top-wrapper">
    <div id="top" class="container">
        <div id="top-nav-wrapper">
            <ul class="top-nav" id="left-top-nav">
                <li class="first"><a class="mini-button" href="http://www.janrain.com">Janrain.com</a></li>
                <li><a href="http://janrain4j.googlecode.com" class="mini-button">Janrain4j</a></li>
                <li class="last"><a class="mini-button yellow" href="#">Help</a></li>
            </ul>
            <c:if test="${not empty primaryKey}">
                <ul class="top-nav" id="right-top-nav">
                    <li class="append"><a href="/user_data.jsp">${(not empty userData.profile.name.formatted ? userData.profile.name.formatted : userData.profile.identifier)}</a></li>
                    <li class="last"><a class="mini-button red" href="/sign_out">Sign Out</a></li>
                </ul>
            </c:if>
        </div>
        <h1 class="title">Janrain4j Demo Application</h1>
        <ul id="main-nav-left" class="tabs nav">
            <li class="left ${(param.selected == 'signin' ? 'selected' : '')}"><a href="/">Sign In</a></li>
            <li class="${(param.selected == 'user_data' ? 'selected' : '')}"><a href="/user_data.jsp">User Data</a></li>
            <li class="${(param.selected == 'mappings' ? 'selected' : '')}"><a href="/mappings.jsp">Mappings</a></li>
            <li class="right ${(param.selected == 'social_publishing' ? 'selected' : '')}"><a href="/social_publishing.jsp">Social Publishing</a></li>
        </ul>
        <ul id="main-nav-right" class="tabs nav">
            <li class="left ${(param.selected == 'all_mappings' ? 'selected' : '')}"><a href="#">All Mappings</a></li>
            <li class="${(param.selected == 'analytics' ? 'selected' : '')}"><a href="#">Analytics</a></li>
            <li class="right ${(param.selected == 'auth_providers' ? 'selected' : '')}"><a href="#">Auth Providers</a></li>
        </ul>
    </div>
</div>