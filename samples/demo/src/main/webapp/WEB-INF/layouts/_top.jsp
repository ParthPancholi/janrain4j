<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="top-wrapper">
    <div id="top" class="container">
        <div id="top-nav-wrapper">
            <ul class="top-nav" id="left-top-nav">
                <li class="first"><a class="mini-button" href="http://janrain4j.googlecode.com" target="_blank">Janrain4j</a></li>
                <li><a class="mini-button" href="http://www.janrain.com" target="_blank">Janrain.com</a></li>
                <li class="last"><a class="mini-button yellow" href="/about">About</a></li>
            </ul>
            <c:if test="${not empty primaryKey}">
                <ul class="top-nav" id="right-top-nav">
                    <li class="append"><a href="/account/show">${(not empty userData.profile.name.formatted ? userData.profile.name.formatted : userData.profile.identifier)}</a></li>
                    <li class="last"><a class="mini-button red" href="/account/sign_out">Sign Out</a></li>
                </ul>
            </c:if>
        </div>
        <h1 class="title">Janrain4j Demo Application</h1>
        <ul id="main-nav-left" class="tabs nav">
            <li class="left ${(param.tab == 'sign_in' ? 'selected' : '')}"><a href="/">Sign In</a></li>
            <li class="${(param.tab == 'user_data' ? 'selected' : '')}"><a href="/user_data">User Data</a></li>
            <li class="${(param.tab == 'social_publishing' ? 'selected' : '')}"><a href="/social_publishing">Social Publishing</a></li>
            <li class="right ${(param.tab == 'account' ? 'selected' : '')}"><a href="/account/show">Account</a></li>
        </ul>
        <ul id="main-nav-right" class="tabs nav">
            <li class="left ${(param.tab == 'about' ? 'selected' : '')}"><a href="/about">About</a></li>
            <li class="right ${(param.tab == 'administrator' ? 'selected' : '')}"><a href="https://appengine.google.com/dashboard?&app_id=janrain4j">Administrator</a></li>
        </ul>
    </div>
</div>

<c:if test="${not empty flashScope['message']}">
    <div id="flash-message" class="container secondary-navigation">
        <span class="item ${(not empty flashScope['level'] ? flashScope['level'] : 'info')}">${flashScope["message"]}</span>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#flash-message').delay(4000).fadeOut('slow'); 
        });
    </script>
</c:if>