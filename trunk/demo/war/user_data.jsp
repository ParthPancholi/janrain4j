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
                    <div class="span-10">
                        <h2>Profile</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a id="profile-collapse" class="item" href="#">Collapse</a>
                        <a id="profile-expand" class="item" href="#">Expand</a>
                    </div>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div id="profile" class="inner">
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
                    <div class="span-10">
                        <h2>Access Credentials</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a id="access-credentials-collapse" class="item" href="#">Collapse</a>
                        <a id="access-credentials-expand" class="item" href="#">Expand</a>
                    </div>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div id="access-credentials" class="inner">
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
                                No access credentials available via your provider.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <div class="span-10">
                        <h2>Merged Portable Contact</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a id="merged-poco-collapse" class="item" href="#">Collapse</a>
                        <a id="merged-poco-expand" class="item" href="#">Expand</a>
                    </div>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div id="merged-poco" class="inner">
                        <c:choose>
                            <c:when test="${not empty userData.mergedPoco}">
                                <table>
                                    <tr>
                                        <td class="user-data"><label>Id</label></td>
                                        <td>${userData.mergedPoco.id}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Display Name</label></td>
                                        <td>${userData.mergedPoco.displayName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Formatted Name</label></td>
                                        <td>${userData.mergedPoco.name.formatted}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Family Name</label></td>
                                        <td>${userData.mergedPoco.name.familyName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Given Name</label></td>
                                        <td>${userData.mergedPoco.name.givenName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Middle Name</label></td>
                                        <td>${userData.mergedPoco.name.middleName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Honorific Prefix</label></td>
                                        <td>${userData.mergedPoco.name.honorificPrefix}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Honorific Suffix</label></td>
                                        <td>${userData.mergedPoco.name.honorificSuffix}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Nickname</label></td>
                                        <td>${userData.mergedPoco.nickname}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Published</label></td>
                                        <td>${userData.mergedPoco.published}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Updated</label></td>
                                        <td>${userData.mergedPoco.updated}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Birthday</label></td>
                                        <td>${userData.mergedPoco.birthday}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Anniversary</label></td>
                                        <td>${userData.mergedPoco.anniversary}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Gender</label></td>
                                        <td>${userData.mergedPoco.gender}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Note</label></td>
                                        <td>${userData.mergedPoco.note}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Preferred Username</label></td>
                                        <td>${userData.mergedPoco.preferredUsername}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>UTC Offset</label></td>
                                        <td>${userData.mergedPoco.utcOffset}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Connected</label></td>
                                        <td>${userData.mergedPoco.connected}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Emails</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.emails}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.emails}" var="email">
                                                        <li>
                                                            ${email.value} (${email.type})
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>URL's</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.urls}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.urls}" var="url">
                                                        <li>
                                                            ${url.value} (${url.type})
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Phone Numbers</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.phoneNumbers}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.phoneNumbers}" var="phoneNumber">
                                                        <li>
                                                            ${phoneNumber.value} (${phoneNumber.type})
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>IM's</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.ims}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.ims}" var="im">
                                                        <li>
                                                            ${im.value} (${im.type})
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Photos</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.photos}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.photos}" var="photo">
                                                        <li>
                                                            ${photo.value} (${photo.type})
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Tags</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.tags}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.tags}" var="tag">
                                                        <li>
                                                            ${tag}
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Relationships</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.relationships}">
                                                <ul>
                                                    <c:forEach items="${userData.mergedPoco.relationships}" var="relationship">
                                                        <li>
                                                            ${relationship.value}
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Addresses</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.addresses}">
                                                <c:forEach items="${userData.mergedPoco.addresses}" var="address">
                                                    <table>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Formatted</label></td>
                                                            <td>${address.formatted}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Street Address</label></td>
                                                            <td>${address.streetAddress}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Locality</label></td>
                                                            <td>${address.locality}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Region</label></td>
                                                            <td>${address.region}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Postal Code</label></td>
                                                            <td>${address.postalCode}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Country</label></td>
                                                            <td>${address.country}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Type</label></td>
                                                            <td>${address.type}</td>
                                                        </tr>
                                                    </table>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Organizations</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.organizations}">
                                                <c:forEach items="${userData.mergedPoco.organizations}" var="organization">
                                                    <table>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Name</label></td>
                                                            <td>${organization.name}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Department</label></td>
                                                            <td>${organization.department}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Title</label></td>
                                                            <td>${organization.title}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Type</label></td>
                                                            <td>${organization.type}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Start Date</label></td>
                                                            <td>${organization.startDate}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>End Date</label></td>
                                                            <td>${organization.endDate}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Location</label></td>
                                                            <td>${organization.location}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Description</label></td>
                                                            <td>${organization.description}</td>
                                                        </tr>
                                                    </table>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-data"><label>Accounts</label></td>
                                        <td class="user-data-small">
                                            <c:if test="${not empty userData.mergedPoco.accounts}">
                                                <c:forEach items="${userData.mergedPoco.accounts}" var="account">
                                                    <table>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Domain</label></td>
                                                            <td>${account.domain}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Username</label></td>
                                                            <td>${account.username}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="user-data-secondary"><label>Userid</label></td>
                                                            <td>${account.userid}</td>
                                                        </tr>
                                                    </table>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:otherwise>
                                No merged protable contact available via your provider.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <div class="span-10">
                        <h2>Friends</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a id="friends-collapse" class="item" href="#">Collapse</a>
                        <a id="friends-expand" class="item" href="#">Expand</a>
                    </div>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div id="friends" class="inner">
                        <c:choose>
                            <c:when test="${not empty userData.friends}">
                                <table class="table">
                                    <c:forEach items="${userData.friends}" var="identifier" varStatus="status">
                                        <tr class="${status.index % 2 == 0 ? 'odd' : 'even'}">
                                            <td>${identifier}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                No friends available via your provider.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
                <div class="half-content-top title">
                    <div class="span-10">
                        <h2>Plain JSON Response</h2>
                    </div>
                    <div class="title-right-tools span-7 last">
                        <a id="plain-response-collapse" class="item" href="#">Collapse</a>
                        <a id="plain-response-expand" class="item" href="#">Expand</a>
                    </div>
                </div>
                <div class="half-content">
                    <div class="additional-tools divider clearfix">
                    </div>
                    <div id="plain-response" class="inner">
                        <pre style="white-space:pre-wrap;">${jsonResponse}</pre>
                    </div>
                </div>
                <div class="half-content-bottom"></div>
                
            </div>
            <jsp:include page="_sidebar.jsp" />
        </div>
        
        <script type="text/javascript">
            $(document).ready(function() {
                
                // profile
                $('#profile-expand').click(function() {
                    $('#profile').slideDown('fast');
                    return false;
                });
                $('#profile-collapse').click(function() {
                    $('#profile').hide();
                    return false;
                });

                // access credentials
                $('#access-credentials').hide();
                $('#access-credentials-expand').click(function() {
                    $('#access-credentials').slideDown('fast');
                    return false;
                });
                $('#access-credentials-collapse').click(function() {
                    $('#access-credentials').hide();
                    return false;
                });

                // merged poco
                $('#merged-poco').hide();
                $('#merged-poco-expand').click(function() {
                    $('#merged-poco').slideDown('fast');
                    return false;
                });
                $('#merged-poco-collapse').click(function() {
                    $('#merged-poco').hide();
                    return false;
                });

                // friends
                $('#friends').hide();
                $('#friends-expand').click(function() {
                    $('#friends').slideDown('fast');
                    return false;
                });
                $('#friends-collapse').click(function() {
                    $('#friends').hide();
                    return false;
                });
                
                // plain response
                $('#plain-response').hide();
                $('#plain-response-expand').click(function() {
                    $('#plain-response').slideDown('fast');
                    return false;
                });
                $('#plain-response-collapse').click(function() {
                    $('#plain-response').hide();
                    return false;
                });
            });
        </script>
    </body>
</html>
