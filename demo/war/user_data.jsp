<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>

<c:if test="${empty primaryKey}">
    <c:redirect url="index.jsp" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us">
    <jsp:include page="_head.jsp" />
    <body>
        <jsp:include page="_top.jsp">
            <jsp:param name="selected" value="user_data" />
        </jsp:include>
        
        <div class="container secondary-navigation">
            <div class="span-18 breadcrumb">
                <a href="#" id="start" class="item">home</a>
                <a href="#" class="item">outro ítem</a>
                <a href="#" class="item">outro ítem um pouco maior</a>
            </div>
            <div class="span-6 last generic-tools">
                <a href="#" class="item">outro ítem</a>
                <a href="#" class="item">outro ítem </a>
            </div>
        </div>
        
        <div class="container">
            <div class="half-content-wrapper span-17">
                <div class="half-content-top title">
                    <h2>User Data</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                    
                    
                    <table>
                        <tr>
                            <td>Identifier</td>
                            <td>${userData.profile.identifier}</td>
                        </tr>
                        <tr>
                            <td>Provider Name</td>
                            <td>${userData.profile.providerName}</td>
                        </tr>
                        <tr>
                            <td>Primary Key</td>
                            <td>${userData.profile.primaryKey}</td>
                        </tr>
                        <tr>
                            <td>Display Name</td>
                            <td>${userData.profile.displayName}</td>
                        </tr>
                        <tr>
                            <td>Preferred Username</td>
                            <td>${userData.profile.preferredUsername}</td>
                        </tr>
                        <tr>
                            <td>Formatted Name</td>
                            <td>${userData.profile.name.formatted}</td>
                        </tr>
                        <tr>
                            <td>Family Name</td>
                            <td>${userData.profile.name.familyName}</td>
                        </tr>
                        <tr>
                            <td>Given Name</td>
                            <td>${userData.profile.name.givenName}</td>
                        </tr>
                        <tr>
                            <td>Middle Name</td>
                            <td>${userData.profile.name.middleName}</td>
                        </tr>
                        <tr>
                            <td>Honorific Prefix</td>
                            <td>${userData.profile.name.honorificPrefix}</td>
                        </tr>
                        <tr>
                            <td>Honorific Suffix</td>
                            <td>${userData.profile.name.honorificSuffix}</td>
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td>${userData.profile.gender}</td>
                        </tr>
                        <tr>
                            <td>Birthday</td>
                            <td>${userData.profile.birthday}</td>
                        </tr>
                        <tr>
                            <td>UTC Offset</td>
                            <td>${userData.profile.utcOffset}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${userData.profile.email}</td>
                        </tr>
                        <tr>
                            <td>Verified Email</td>
                            <td>${userData.profile.verifiedEmail}</td>
                        </tr>
                        <tr>
                            <td>Url</td>
                            <td>${userData.profile.url}</td>
                        </tr>
                        <tr>
                            <td>Phone Number</td>
                            <td>${userData.profile.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Photo</td>
                            <td>${userData.profile.photo}</td>
                        </tr>
                        <tr>
                            <td>Formatted Address</td>
                            <td>${userData.profile.address.formatted}</td>
                        </tr>
                        <tr>
                            <td>Street Address</td>
                            <td>${userData.profile.address.streetAddress}</td>
                        </tr>
                        <tr>
                            <td>Locality</td>
                            <td>${userData.profile.address.locality}</td>
                        </tr>
                        <tr>
                            <td>Region</td>
                            <td>${userData.profile.address.region}</td>
                        </tr>
                        <tr>
                            <td>Postal Code</td>
                            <td>${userData.profile.address.postalCode}</td>
                        </tr>
                        <tr>
                            <td>Country</td>
                            <td>${userData.profile.address.country}</td>
                        </tr>
                        <tr>
                            <td>Limited Data</td>
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
