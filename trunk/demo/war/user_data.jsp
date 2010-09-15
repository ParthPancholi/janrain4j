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
                                <td>${userData.profile.photo}</td>
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
                            <tr>
                                <td class="user-data"><label>Limited Data</label></td>
                                <td>${userData.profile.limitedData}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <h2>Access Credentials</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <c:choose>
                            <c:when test="${not empty userData.accessCredentials}">
                                <table>
                                    <tr>
                                        <td class="user-data"><label>Type</label></td>
                                        <td>${userData.accessCredentials.type}</td>
                                    </tr>
                                    <c:if test="${userData.accessCredentials.oauth}">
                                        <tr>
                                            <td class="user-data"><label>Token</label></td>
                                            <td>${userData.accessCredentials.oauthToken}</td>
                                        </tr>
                                        <tr>
                                            <td class="user-data"><label>Token Secret</label></td>
                                            <td>${userData.accessCredentials.oauthTokenSecret}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${userData.accessCredentials.facebook}">
                                        <tr>
                                            <td class="user-data"><label>UID</label></td>
                                            <td>${userData.accessCredentials.uid}</td>
                                        </tr>
                                        <tr>
                                            <td class="user-data"><label>Access Token</label></td>
                                            <td>${userData.accessCredentials.accessToken}</td>
                                        </tr>
                                        <tr>
                                            <td class="user-data"><label>Expires</label></td>
                                            <td>${userData.accessCredentials.expires}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${userData.accessCredentials.windowsLive}">
                                        <tr>
                                            <td class="user-data"><label>Eact</label></td>
                                            <td>${userData.accessCredentials.eact}</td>
                                        </tr>
                                    </c:if>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Your provider does not provide any access credentials.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <h2>Merged Portable Contacts</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        Under construction.
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <h2>Friends</h2>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div class="inner">
                        <c:if test="${not empty userData.friends}">
                            <table class="table">
                                <tr>             
                                    <th>Identifier</th>
                                </tr>
                                <c:forEach items="${userData.friends}" var="identifier" varStatus="status">
                                    <tr class="${status.index % 2 == 0 ? 'odd' : 'even'}">
                                        <td>${identifier}</td>
                                    </tr>
                                </c:forEach>
                            </table>                        
                        </c:if>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
    </body>
</html>
