<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.8.1/build/reset-fonts-grids/reset-fonts-grids.css" />
        <link rel="stylesheet" type="text/css" href="/css/main.css" />
        <title>janrain4j demo</title>
    </head>
    <body>
        <h1>signedin.jsp</h1>
        <table>
            <tr>
                <td>Identifier</td>
                <td>${sessionScope.userData.profile.identifier}</td>
            </tr>
            <tr>
                <td>Provider Name</td>
                <td>${sessionScope.userData.profile.providerName}</td>
            </tr>
            <tr>
                <td>Primary Key</td>
                <td>${sessionScope.userData.profile.primaryKey}</td>
            </tr>
            <tr>
                <td>Display Name</td>
                <td>${sessionScope.userData.profile.displayName}</td>
            </tr>
            <tr>
                <td>Preferred Username</td>
                <td>${sessionScope.userData.profile.preferredUsername}</td>
            </tr>
            <tr>
                <td>Formatted Name</td>
                <td>${sessionScope.userData.profile.name.formatted}</td>
            </tr>
            <tr>
                <td>Family Name</td>
                <td>${sessionScope.userData.profile.name.familyName}</td>
            </tr>
            <tr>
                <td>Given Name</td>
                <td>${sessionScope.userData.profile.name.givenName}</td>
            </tr>
            <tr>
                <td>Middle Name</td>
                <td>${sessionScope.userData.profile.name.middleName}</td>
            </tr>
            <tr>
                <td>Honorific Prefix</td>
                <td>${sessionScope.userData.profile.name.honorificPrefix}</td>
            </tr>
            <tr>
                <td>Honorific Suffix</td>
                <td>${sessionScope.userData.profile.name.honorificSuffix}</td>
            </tr>
            <tr>
                <td>Gender</td>
                <td>${sessionScope.userData.profile.gender}</td>
            </tr>
            <tr>
                <td>Birthday</td>
                <td>${sessionScope.userData.profile.birthday}</td>
            </tr>
            <tr>
                <td>UTC Offset</td>
                <td>${sessionScope.userData.profile.utcOffset}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${sessionScope.userData.profile.email}</td>
            </tr>
            <tr>
                <td>Verified Email</td>
                <td>${sessionScope.userData.profile.verifiedEmail}</td>
            </tr>
            <tr>
                <td>Url</td>
                <td>${sessionScope.userData.profile.url}</td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td>${sessionScope.userData.profile.phoneNumber}</td>
            </tr>
            <tr>
                <td>Photo</td>
                <td>${sessionScope.userData.profile.photo}</td>
            </tr>
            <tr>
                <td>Formatted Address</td>
                <td>${sessionScope.userData.profile.address.formatted}</td>
            </tr>
            <tr>
                <td>Street Address</td>
                <td>${sessionScope.userData.profile.address.streetAddress}</td>
            </tr>
            <tr>
                <td>Locality</td>
                <td>${sessionScope.userData.profile.address.locality}</td>
            </tr>
            <tr>
                <td>Region</td>
                <td>${sessionScope.userData.profile.address.region}</td>
            </tr>
            <tr>
                <td>Postal Code</td>
                <td>${sessionScope.userData.profile.address.postalCode}</td>
            </tr>
            <tr>
                <td>Country</td>
                <td>${sessionScope.userData.profile.address.country}</td>
            </tr>
            <tr>
                <td>Limited Data</td>
                <td>${sessionScope.userData.profile.limitedData}</td>
            </tr>
        </table>
    </body>
</html>
