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
            <jsp:param name="selected" value="user_data" />
        </jsp:include>
        
        <jsp:include page="_flash_message.jsp" />
        
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
                                <td style="width: 200px;"><label>Identifier</label></td>
                                <td>${userData.profile.identifier}</td>
                            </tr>
                            <tr>
                                <td><label>Provider Name</label></td>
                                <td>${userData.profile.providerName}</td>
                            </tr>
                            <tr>
                                <td><label>Primary Key</label></td>
                                <td>${userData.profile.primaryKey}</td>
                            </tr>
                            <tr>
                                <td><label>Display Name</label></td>
                                <td>${userData.profile.displayName}</td>
                            </tr>
                            <tr>
                                <td><label>Preferred Username</label></td>
                                <td>${userData.profile.preferredUsername}</td>
                            </tr>
                            <tr>
                                <td><label>Formatted Name</label></td>
                                <td>${userData.profile.name.formatted}</td>
                            </tr>
                            <tr>
                                <td><label>Family Name</label></td>
                                <td>${userData.profile.name.familyName}</td>
                            </tr>
                            <tr>
                                <td><label>Given Name</label></td>
                                <td>${userData.profile.name.givenName}</td>
                            </tr>
                            <tr>
                                <td><label>Middle Name</label></td>
                                <td>${userData.profile.name.middleName}</td>
                            </tr>
                            <tr>
                                <td><label>Honorific Prefix</label></td>
                                <td>${userData.profile.name.honorificPrefix}</td>
                            </tr>
                            <tr>
                                <td><label>Honorific Suffix</label></td>
                                <td>${userData.profile.name.honorificSuffix}</td>
                            </tr>
                            <tr>
                                <td><label>Gender</label></td>
                                <td>${userData.profile.gender}</td>
                            </tr>
                            <tr>
                                <td><label>Birthday</label></td>
                                <td>${userData.profile.birthday}</td>
                            </tr>
                            <tr>
                                <td><label>UTC Offset</label></td>
                                <td>${userData.profile.utcOffset}</td>
                            </tr>
                            <tr>
                                <td><label>Email</label></td>
                                <td>${userData.profile.email}</td>
                            </tr>
                            <tr>
                                <td><label>Verified Email</label></td>
                                <td>${userData.profile.verifiedEmail}</td>
                            </tr>
                            <tr>
                                <td><label>Url</label></td>
                                <td>${userData.profile.url}</td>
                            </tr>
                            <tr>
                                <td><label>Phone Number</label></td>
                                <td>${userData.profile.phoneNumber}</td>
                            </tr>
                            <tr>
                                <td><label>Photo</label></td>
                                <td>${userData.profile.photo}</td>
                            </tr>
                            <tr>
                                <td><label>Formatted Address</label></td>
                                <td>${userData.profile.address.formatted}</td>
                            </tr>
                            <tr>
                                <td><label>Street Address</label></td>
                                <td>${userData.profile.address.streetAddress}</td>
                            </tr>
                            <tr>
                                <td><label>Locality</label></td>
                                <td>${userData.profile.address.locality}</td>
                            </tr>
                            <tr>
                                <td><label>Region</label></td>
                                <td>${userData.profile.address.region}</td>
                            </tr>
                            <tr>
                                <td><label>Postal Code</label></td>
                                <td>${userData.profile.address.postalCode}</td>
                            </tr>
                            <tr>
                                <td><label>Country</label></td>
                                <td>${userData.profile.address.country}</td>
                            </tr>
                            <tr>
                                <td><label>Limited Data</label></td>
                                <td>${userData.profile.limitedData}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
