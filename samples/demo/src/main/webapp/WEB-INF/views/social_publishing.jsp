<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html tab="social_publishing">
    <body>
        <div class="half-content-top title">
            <h2>Set Status</h2>
        </div>
        <div class="half-content">
            <div class="additional-tools divider clearfix">
            </div>
            <div class="inner">
                <c:choose>
                    <c:when test="${setStatusSupported}">
                        <form action="/social_publishing/set_status" method="post" class="form">
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
                        <form action="/social_publishing/activity" method="post" class="form">
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
                                <img src="/static/images/activity-example.png" style="border:1px solid #eee; margin:5px 0; padding:5px;" width="450" />
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
    </body>
</html>
