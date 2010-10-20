<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.googlecode.janrain4j.api.engage.*" %>
<%@ page import="com.googlecode.janrain4j.api.engage.response.*" %>
<%@ page import="com.googlecode.janrain4j.api.engage.response.profile.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<jsp:include page="_flash.jsp" />

<%  // get sign in provider from user data
    Profile profile = (Profile) session.getAttribute("profile");
    String providerName = profile.getProviderName();
    
    // providers supporting set status call
    List<String> setStatus = new ArrayList<String>();
    setStatus.add("Facebook");
    setStatus.add("LinkedIn");
    setStatus.add("Twitter");
    setStatus.add("MySpace");
    setStatus.add("Google");
    setStatus.add("Yahoo!");
    pageContext.setAttribute("setStatusSupported", (setStatus.contains(providerName)));
    
    // providers supporting activity call
    List<String> activity = new ArrayList<String>();
    activity.add("Facebook");
    activity.add("LinkedIn");
    activity.add("Twitter");
    activity.add("MySpace");
    activity.add("Yahoo!");
    pageContext.setAttribute("activitySupported", (activity.contains(providerName)));
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
                                        <input type="submit" class="mini-button" value="Update your Status" style="margin-left:155px; width:150px;" />
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
                        <c:choose>
                            <c:when test="${activitySupported}">
                                <form action="/activity" method="post" class="form">
                                    <div class="group clearfix">
                                        <label>User Generated Content</label>
                                        <textarea name="userGeneratedContent" class="text" rows="5" cols="80">Janrain4j is awesome!</textarea>
                                    </div>
                                    <div class="group navform">
                                        <input type="submit" class="mini-button" value="Update your Activity" style="margin-left:155px; width:150px;" />
                                    </div>
                                    <div class="group clearfix" style="margin-left:155px;">
                                        Posts an activity update to your activity stream promoting the Janrain4j library.<br />
                                        The update will contain text promoting the Janrain4j library including action links and media items.
                                        Example activity posted to Facebook:
                                        <img src="/images/activity-example.png" style="border:1px solid #eee; margin:5px 0; padding:5px;" width="450" />
                                        <span class="description" style="margin-left:0px; padding-top:5px;">Note that the actual update posted might vary between providers.</span>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                Your provider does not support publishing an activity.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
