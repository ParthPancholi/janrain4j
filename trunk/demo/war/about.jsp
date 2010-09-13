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
                    <h2>About</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lobortis aliquam tincidunt. Nulla ipsum ipsum, vulputate sed congue at, scelerisque nec leo. Nunc porta bibendum lorem, ac tincidunt enim placerat eu. Suspendisse egestas consequat leo, eget ornare augue auctor id. Nullam vel lectus libero, eu consectetur tortor. Suspendisse vestibulum tempus nulla, at laoreet lacus fermentum nec. Mauris egestas vulputate lacus, ut condimentum elit sodales eu. Fusce dapibus, diam commodo condimentum molestie, magna odio rhoncus orci, eu sodales nisl metus eu metus. Donec quis libero id odio luctus condimentum quis malesuada purus. Maecenas urna tortor, volutpat quis egestas eu, pretium nec ligula. Quisque ornare consequat mattis. Vivamus malesuada odio quis enim pulvinar interdum. Etiam ac nulla eros, sit amet euismod magna. Vivamus varius placerat ante a pulvinar. Aliquam vel metus justo, non tincidunt est.
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
