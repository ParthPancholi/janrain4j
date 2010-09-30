<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.googlecode.janrain4j.api.engage.response.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<jsp:include page="_flash.jsp" />

<%  // get complete json response from user data
    UserDataResponse userData = (UserDataResponse) session.getAttribute("userData");
    String jsonResponse = userData.getResponseAsJSONObject().toString(2);
    pageContext.setAttribute("jsonResponse", jsonResponse);
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="user_data" />
        </jsp:include>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <h2>Profile</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <table>
                            <tr>
                                <td class="user-data"><label>Identifier</label></td>
                                <td>${userData.profile.identifier}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Provider Name</label></td>
                                <td>${userData.profile.providerName}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Primary Key</label></td>
                                <td>${userData.profile.primaryKey}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Display Name</label></td>
                                <td>${userData.profile.displayName}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Preferred Username</label></td>
                                <td>${userData.profile.preferredUsername}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Formatted Name</label></td>
                                <td>${userData.profile.name.formatted}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Family Name</label></td>
                                <td>${userData.profile.name.familyName}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Given Name</label></td>
                                <td>${userData.profile.name.givenName}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Middle Name</label></td>
                                <td>${userData.profile.name.middleName}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Honorific Prefix</label></td>
                                <td>${userData.profile.name.honorificPrefix}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Honorific Suffix</label></td>
                                <td>${userData.profile.name.honorificSuffix}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Gender</label></td>
                                <td>${userData.profile.gender}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Birthday</label></td>
                                <td>${userData.profile.birthday}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>UTC Offset</label></td>
                                <td>${userData.profile.utcOffset}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Email</label></td>
                                <td>${userData.profile.email}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Verified Email</label></td>
                                <td>${userData.profile.verifiedEmail}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Url</label></td>
                                <td>${userData.profile.url}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Phone Number</label></td>
                                <td>${userData.profile.phoneNumber}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Photo</label></td>
                                <td>
                                    <c:if test="${not empty userData.profile.photo}">
                                        <p>${userData.profile.photo}</p>
                                        <img src="${userData.profile.photo}" />
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Formatted Address</label></td>
                                <td>${userData.profile.address.formatted}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Street Address</label></td>
                                <td>${userData.profile.address.streetAddress}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Locality</label></td>
                                <td>${userData.profile.address.locality}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Region</label></td>
                                <td>${userData.profile.address.region}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Postal Code</label></td>
                                <td>${userData.profile.address.postalCode}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Country</label></td>
                                <td>${userData.profile.address.country}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <h2>Plain JSON Response</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <pre style="white-space:pre-wrap;">${jsonResponse}</pre>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
