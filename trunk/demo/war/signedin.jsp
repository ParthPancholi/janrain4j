<%@ page isELIgnored="false" %>
<%@ page import="com.googlecode.janrain4j.api.engage.*" %>
<%@ page import="com.googlecode.janrain4j.demo.*" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="janrain" uri="http://janrain4j.googlecode.com/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.8.1/build/reset-fonts-grids/reset-fonts-grids.css" />
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.8.1/build/base/base-min.css" />
        <link rel="stylesheet" type="text/css" href="/css/main.css" />
        <title>janrain4j demo</title>
    </head>
    <body>
        <div id="doc">
            <div id="hd">
            </div>
            <div id="bd">
                <h1>signedin.jsp</h1>
                
                <c:if test="${not empty sessionScope.user}">
                    <a href="/sign_out">Sign Out</a>
                </c:if>
                
                <c:if test="${not empty param.error}">
                    <div class="error">${param.error}</div>
                </c:if>
                
                <c:if test="${not empty param.message}">
                    <div class="message">${param.message}</div>
                </c:if>
                
                <%  EngageService engageService = EngageServiceFactory.getInstance();
                    User user = (User) session.getAttribute("user");
                    
                    List<String> mappings = engageService.mappings(String.valueOf(user.getId()));
                    System.out.println("Hello: " + request.getAttribute("message"));
                    pageContext.setAttribute("mappings", mappings);
                 %>
                
                <table>
                    <c:forEach items="${mappings}" var="mapping">
                    <tr>
                        <td>${mapping}</td>
                        <td>
                            <form action="/unmap" method="post">
                                <input name="identifier" type="hidden" value="${mapping}" />
                                <input type="submit" value="Unmap" />
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                    <c:if test="${not empty mappings}">
                    <tr>
                        <td>
                            <janrain:signInLink tokenUrl="http://localhost:8888/map">Map an additional Identifier</janrain:signInLink>
                        </td>
                        <td>
                            <form action="/delete_account" method="post">
                                <input type="submit" value="Delete Account" />
                            </form>
                        </td>
                    </tr>
                    </c:if>
                </table>
                
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
            <div id="ft">
            </div>
        </div>
        <janrain:signInOverlay flags="show_provider_list" />
    </body>
</html>
