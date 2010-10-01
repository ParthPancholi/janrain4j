<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="top-wrapper">
    <div id="top" class="container">
        <div id="top-nav-wrapper">
            <ul class="top-nav" id="left-top-nav">
                <li class="first"><a class="mini-button" href="http://janrain4j.googlecode.com" target="_blank">Janrain4j</a></li>
                <li><a class="mini-button" href="http://www.janrain.com" target="_blank">Janrain.com</a></li>
                <li class="last"><a class="mini-button yellow" href="/about.jsp">About</a></li>
            </ul>
            <c:if test="${not empty primaryKey}">
                <ul class="top-nav" id="right-top-nav">
                    <li class="append"><a href="/account.jsp">${(not empty userData.profile.name.formatted ? userData.profile.name.formatted : userData.profile.identifier)}</a></li>
                    <li class="last"><a class="mini-button red" href="/sign_out">Sign Out</a></li>
                </ul>
            </c:if>
        </div>
        <h1 class="title">Janrain4j Demo Application</h1>
        <ul id="main-nav-left" class="tabs nav">
            <li class="left ${(param.selected == 'signin' ? 'selected' : '')}"><a href="/">Sign In</a></li>
            <li class="${(param.selected == 'user_data' ? 'selected' : '')}"><a href="/user_data.jsp">User Data</a></li>
            <li class="${(param.selected == 'social_publishing' ? 'selected' : '')}"><a href="/social_publishing.jsp">Social Publishing</a></li>
            <li class="right ${(param.selected == 'account' ? 'selected' : '')}"><a href="/account.jsp">Account</a></li>
        </ul>
        <ul id="main-nav-right" class="tabs nav">
            <li class="left ${(param.selected == 'about' ? 'selected' : '')}"><a href="/about.jsp">About</a></li>
            <li class="right ${(param.selected == 'administrator' ? 'selected' : '')}"><a href="https://appengine.google.com/dashboard?&app_id=janrain4j">Administrator</a></li>
        </ul>
    </div>
</div>

<c:if test="${not empty flashScope['message']}">
    <div class="container secondary-navigation flash-message">
        <span class="item ${(not empty flashScope['level'] ? flashScope['level'] : 'info')}">${flashScope["message"]}</span>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#flash-message').delay(3500).fadeOut(600); 
        });
    </script>
</c:if>