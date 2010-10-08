<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- move flash scope to request, and remove it from session (our simple flash scope implementation) --%>
<c:set var="flashScope" scope="request" value="${sessionScope.flash}" />
<c:remove var="flash" scope="session" />