<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<jsp:include page="_flash.jsp" />

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
                    <div class="inner divider">
                        <form action="/set_status" method="post" class="form">
                            <div class="group clearfix">
                                <label>Message</label>
                                <textarea class="text" rows="10" cols="80"></textarea>
                                <span class="description">Write here a long text</span>
                            </div>
                            <div class="group navform">
                                <input type="submit" class="mini-button" value="Update your Status" />
Facebook
LinkedIn
Twitter
MySpace
Google
Yahoo! 
                            </div>
                        </form>
                    </div>
                    <div class="inner">
                        Nullam mattis, odio ut tempus facilisis, metus nisl facilisis metus, auctor 
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
