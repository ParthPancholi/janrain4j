<%@ page import="com.googlecode.janrain4j.api.engage.response.UserDataResponse" %>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="userData" property="principal.userDataResponse" />
<c:set var="plainResponse" value='<%= ((UserDataResponse) pageContext.getAttribute("userData")).getResponseAsJSONObject().toString(2) %>' />
<html tab="user_data">
    <body>
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
                                                <li>${email.value} (${email.type})</li>
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
                                                <li>${url.value} (${url.type})</li>
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
                                                <li>${phoneNumber.value} (${phoneNumber.type})</li>
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
                                                <li>${im.value} (${im.type})</li>
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
                                                <li>${photo.value} (${photo.type})</li>
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
                                                <li>${tag}</li>
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
                                                <li>${relationship.value}</li>
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
                            <tr>
                                <td class="user-data"><label>About Me</label></td>
                                <td>${userData.mergedPoco.aboutMe}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Body Type</label></td>
                                <td>
                                    <c:if test="${not empty userData.mergedPoco.bodyType}">
                                        <table>
                                            <tr>
                                                <td class="user-data-secondary"><label>Build</label></td>
                                                <td>${userData.mergedPoco.bodyType.build}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Eye Color</label></td>
                                                <td>${userData.mergedPoco.bodyType.eyeColor}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Hair Color</label></td>
                                                <td>${userData.mergedPoco.bodyType.hairColor}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Height</label></td>
                                                <td>${userData.mergedPoco.bodyType.height}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Weight</label></td>
                                                <td>${userData.mergedPoco.bodyType.weight}</td>
                                            </tr>
                                        </table>
                                    </c:if>                                
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Current Location</label></td>
                                <td>
                                    <c:if test="${not empty userData.mergedPoco.currentLocation}">
                                        <table>
                                            <tr>
                                                <td class="user-data-secondary"><label>Formatted</label></td>
                                                <td>${userData.mergedPoco.currentLocation.formatted}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Street Address</label></td>
                                                <td>${userData.mergedPoco.currentLocation.streetAddress}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Locality</label></td>
                                                <td>${userData.mergedPoco.currentLocation.locality}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Region</label></td>
                                                <td>${userData.mergedPoco.currentLocation.region}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Postal Code</label></td>
                                                <td>${userData.mergedPoco.currentLocation.postalCode}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Country</label></td>
                                                <td>${userData.mergedPoco.currentLocation.country}</td>
                                            </tr>
                                            <tr>
                                                <td class="user-data-secondary"><label>Type</label></td>
                                                <td>${userData.mergedPoco.currentLocation.type}</td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Drinker</label></td>
                                <td>${userData.mergedPoco.drinker}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Ethnicity</label></td>
                                <td>${userData.mergedPoco.ethnicity}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Fashion</label></td>
                                <td>${userData.mergedPoco.fashion}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Happiest When</label></td>
                                <td>${userData.mergedPoco.happiestWhen}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Living Arrangement</label></td>
                                <td>${userData.mergedPoco.livingArrangement}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Profile Song</label></td>
                                <td>${userData.mergedPoco.profileSong}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Profile Url</label></td>
                                <td>${userData.mergedPoco.profileUrl}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Profile Video</label></td>
                                <td>${userData.mergedPoco.profileVideo}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Relationship Status</label></td>
                                <td>${userData.mergedPoco.relationshipStatus}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Religion</label></td>
                                <td>${userData.mergedPoco.religion}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Romance</label></td>
                                <td>${userData.mergedPoco.romance}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Scared Of</label></td>
                                <td>${userData.mergedPoco.scaredOf}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Sexual Orientation</label></td>
                                <td>${userData.mergedPoco.sexualOrientation}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Smoker</label></td>
                                <td>${userData.mergedPoco.smoker}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Status</label></td>
                                <td>${userData.mergedPoco.status}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Activities TODO</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.activities}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.activities}" var="activity">
                                                <li>${activity}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Books</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.books}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.books}" var="book">
                                                <li>${book}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Cars</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.cars}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.cars}" var="car">
                                                <li>${car}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Children</label></td>
                                <td>${userData.mergedPoco.children}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Food</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.food}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.food}" var="food">
                                                <li>${food}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Heroes</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.heroes}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.heroes}" var="hero">
                                                <li>${hero}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Interests</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.interests}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.interests}" var="interest">
                                                <li>${interest}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Job Interests</label></td>
                                <td>${userData.mergedPoco.jobInterests}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Languages Spoken</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.languagesSpoken}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.languagesSpoken}" var="languageSpoken">
                                                <li>${languageSpoken}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Movies</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.movies}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.movies}" var="movie">
                                                <li>${movie}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Music</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.music}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.music}" var="music">
                                                <li>${music}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Pets</label></td>
                                <td>${userData.mergedPoco.pets}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Political Views</label></td>
                                <td>${userData.mergedPoco.politicalViews}</td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Quotes</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.quotes}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.quotes}" var="quote">
                                                <li>${quote}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Sports</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.sports}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.sports}" var="sport">
                                                <li>${sport}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Turn Offs</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.turnOffs}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.turnOffs}" var="turnOff">
                                                <li>${turnOff}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>Turn Ons</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.turnOns}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.turnOns}" var="turnOn">
                                                <li>${turnOn}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="user-data"><label>TV Shows</label></td>
                                <td class="user-data-small">
                                    <c:if test="${not empty userData.mergedPoco.tvShows}">
                                        <ul>
                                            <c:forEach items="${userData.mergedPoco.tvShows}" var="tvShow">
                                                <li>${tvShow}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </c:when>
                    <c:otherwise>
                        No merged portable contact available via your provider.
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
                <pre style="white-space:pre-wrap;">${plainResponse}</pre>
            </div>
        </div>
        <div class="half-content-bottom"></div>
        
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