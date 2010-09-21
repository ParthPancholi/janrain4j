<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.googlecode.janrain4j.api.engage.*" %>
<%@ page import="com.googlecode.janrain4j.api.engage.response.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<jsp:include page="_flash.jsp" />

<%  // get sign in provider from user data
    UserDataResponse userData = (UserDataResponse) session.getAttribute("userData");
    String providerName = userData.getProfile().getProviderName();
    
    // providers supporting set status
    List<String> list = new ArrayList<String>();
    list.add("Facebook");
    list.add("LinkedIn");
    list.add("Twitter");
    list.add("MySpace");
    list.add("Google");
    list.add("Yahoo!");
    
    pageContext.setAttribute("setStatusSupported", (list.contains(providerName)));
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="social_publishing" />
        </jsp:include>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <h2>Set Status</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <c:choose>
                            <c:when test="${setStatusSupported}">
                                <form action="/set_status" method="post" class="form">
                                    <div class="group clearfix">
                                        <label>Status Message</label>
                                        <textarea name="message" class="text" rows="5" cols="80"></textarea>
                                        <span class="description" style="margin-left:155px;">Note that some providers might truncate your update message.</span>
                                    </div>
                                    <div class="group navform">
                                        <input type="submit" class="mini-button" value="Update your Status" style="margin-left:155px;" />
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                Your provider does not support setting your status.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <h2>Publish Activity</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        Under construction.
                        <script type="text/javascript">
                            alert('1');
                            RPXNOW.loadAndRun(['Social'], function () {
                                alert('1.5');
                                var activity = new RPXNOW.Social.Activity(
                                    "Share your comment",
                                    "commented on 'Like My New Perfume?' on cuteoverload.com",
                                    "http://cuteoverload.com/2009/10/26/like-my-new-perfume/"
                                );
                                RPXNOW.Social.publishActivity(activity);
                            });
                            alert('2');
                        </script>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
        
        <janrain:load />
        <janrain:init />
    </body>
</html>
