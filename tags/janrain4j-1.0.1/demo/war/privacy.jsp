<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="_flash.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="about" />
        </jsp:include>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <h2>Privacy Policy</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <p>The Janrain4j demo application does not store any information about visitors and signed in users.</p>
                        <p>Note that the Janrain4j demo application uses the <a href="http://www.janrain.com">Janrain</a> user management platform which has it's own privacy policy which can be read <a href="http://www.janrain.com/janrain-privacy-policy">here</a>.</p>     
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
