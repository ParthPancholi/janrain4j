<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty flashScope['message']}">
    <div class="container secondary-navigation">
        <span class="item ${(not empty flashScope['level'] ? flashScope['level'] : 'info')}">${flashScope["message"]}</span>
    </div>
</c:if>