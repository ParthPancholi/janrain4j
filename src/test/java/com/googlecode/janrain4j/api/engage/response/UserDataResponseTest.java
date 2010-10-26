/* Copyright 2010 Marcel Overdijk

import java.net.HttpURLConnection;

import java.net.HttpURLConnection;

import java.sql.Connection;
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.janrain4j.api.engage.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.api.engage.response.accesscredentials.AccessCredentials;
import com.googlecode.janrain4j.api.engage.response.poco.Account;
import com.googlecode.janrain4j.api.engage.response.poco.Address;
import com.googlecode.janrain4j.api.engage.response.poco.Contact;
import com.googlecode.janrain4j.api.engage.response.poco.Email;
import com.googlecode.janrain4j.api.engage.response.poco.IM;
import com.googlecode.janrain4j.api.engage.response.poco.Organization;
import com.googlecode.janrain4j.api.engage.response.poco.PhoneNumber;
import com.googlecode.janrain4j.api.engage.response.poco.Photo;
import com.googlecode.janrain4j.api.engage.response.poco.Relationship;
import com.googlecode.janrain4j.api.engage.response.poco.Url;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.json.JSONObject;

public class UserDataResponseTest {

    private String json = null;
    private UserDataResponse response = null;
    
    private SimpleDateFormat dateFormatter = null;
    private SimpleDateFormat dateTimeFormatter = null;
    
    @Before
    public void setUp() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    @Test
    public void testUserDataResponse() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"displayName\": \"brian\",\n" +
            "    \"preferredUsername\": \"brian\",\n" +
            "    \"url\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        Profile profile = response.getProfile();
        assertNotNull(profile);
        assertEquals("http://brian.myopenid.com/", profile.getIdentifier());
        assertEquals("Other", profile.getProviderName());
        assertEquals("brian", profile.getDisplayName());
        assertEquals("brian", profile.getPreferredUsername());
        assertEquals("http://brian.myopenid.com/", profile.getUrl());
    }
    
    @Test
    public void testUserDataResponseWithAllFieldsPossible() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"identifier\": \"my-identifier\",\n" +
            "    \"providerName\": \"my-provider-name\",\n" +
            "    \"primaryKey\": \"my-primary-key\",\n" +
            "    \"displayName\": \"my-display-name\",\n" +
            "    \"preferredUsername\": \"my-preferred-username\",\n" +
            "    \"name\": {\n" +
            "      \"formatted\": \"my-name-formatted\",\n" +
            "      \"familyName\": \"my-name-family-name\",\n" +
            "      \"givenName\": \"my-name-given-name\",\n" +
            "      \"middleName\": \"my-name-middle-name\",\n" +
            "      \"honorificPrefix\": \"my-name-honorific-prefix\",\n" +
            "      \"honorificSuffix\": \"my-name-honorific-suffix\"\n" +
            "    },\n" +
            "    \"gender\": \"my-gender\",\n" +
            "    \"birthday\": \"1977-05-07\",\n" +
            "    \"utcOffset\": \"my-utc-offset\",\n" +
            "    \"email\": \"my-email\",\n" +
            "    \"verifiedEmail\": \"my-verified-email\",\n" +
            "    \"url\": \"my-url\",\n" +
            "    \"phoneNumber\": \"my-phone-number\",\n" +
            "    \"photo\": \"my-photo\",\n" +
            "    \"address\": {\n" +
            "      \"formatted\": \"my-address-formatted\",\n" +
            "      \"streetAddress\": \"my-address-street-address\",\n" +
            "      \"locality\": \"my-address-locality\",\n" +
            "      \"region\": \"my-address-region\",\n" +
            "      \"postalCode\": \"my-address-postal-code\",\n" +
            "      \"country\": \"my-address-country\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"accessCredentials\": {\n" +
            "    \"type\": \"my-access-credentials-type\",\n" +
            "    \"oauthToken\": \"my-oauth-token\",\n" +
            "    \"oauthTokenSecret\": \"my-oauth-token-secret\",\n" +
            "    \"uid\": \"my-uid\",\n" +
            "    \"accessToken\": \"my-access-token\",\n" +
            "    \"expires\": 1234567890,\n" +
            "    \"eact\": \"my-eact\"\n" +
            "  },\n" +
            "  \"merged_poco\": {\n" +
            "    \"id\": \"my-merged-poco-id\",\n" +
            "    \"displayName\": \"my-merged-poco-display-name\",\n" +
            "    \"name\": {\n" +
            "      \"formatted\": \"my-merged-poco-formatted-name\",\n" +
            "      \"familyName\": \"my-merged-poco-family-name\",\n" +
            "      \"givenName\": \"my-merged-poco-given-name\",\n" +
            "      \"middleName\": \"my-merged-poco-middle-name\",\n" +
            "      \"honorificPrefix\": \"my-merged-poco-honorific-prefix\",\n" +
            "      \"honorificSuffix\": \"my-merged-poco-honorific-suffix\"\n" +
            "    },\n" +
            "    \"nickname\": \"my-merged-poco-nickname\",\n" +
            "    \"published\": \"2008-01-02T03:04:05Z\",\n" +
            "    \"updated\": \"2010-11-12T13:14:15Z\",\n" +
            "    \"birthday\": \"1977-05-07\",\n" +
            "    \"anniversary\": \"2001-09-23\",\n" +
            "    \"gender\": \"my-merged-poco-gender\",\n" +
            "    \"note\": \"my-merged-poco-note\",\n" +
            "    \"preferredUsername\": \"my-merged-poco-preferred-username\",\n" +
            "    \"utcOffset\": \"my-merged-poco-utc-offset\",\n" +
            "    \"connected\": false,\n" +
            "    \"emails\": [{\n" +
            "      \"value\": \"my-merged-poco-email1-value\",\n" +
            "      \"type\": \"my-merged-poco-email1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-email2-value\",\n" +
            "      \"type\": \"my-merged-poco-email2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"urls\": [{\n" +
            "      \"value\": \"my-merged-poco-url1-value\",\n" +
            "      \"type\": \"my-merged-poco-url1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-url2-value\",\n" +
            "      \"type\": \"my-merged-poco-url2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"phoneNumbers\": [{\n" +
            "      \"value\": \"my-merged-poco-phone-number1-value\",\n" +
            "      \"type\": \"my-merged-poco-phone-number1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-phone-number2-value\",\n" +
            "      \"type\": \"my-merged-poco-phone-number2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"ims\": [{\n" +
            "      \"value\": \"my-merged-poco-im1-value\",\n" +
            "      \"type\": \"my-merged-poco-im1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-im2-value\",\n" +
            "      \"type\": \"my-merged-poco-im2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"photos\": [{\n" +
            "      \"value\": \"my-merged-poco-photo1-value\",\n" +
            "      \"type\": \"my-merged-poco-photo1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-photo2-value\",\n" +
            "      \"type\": \"my-merged-poco-photo2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"tags\": [\n" +
            "      \"my-merged-poco-tag1\",\n" +
            "      \"my-merged-poco-tag2\",\n" +
            "    ],\n" +
            "    \"relationships\": [\n" +
            "      \"my-merged-poco-relationship1\",\n" +
            "      \"my-merged-poco-relationship2\",\n" +
            "    ],\n" +
            "    \"addresses\": [{\n" +
            "      \"formatted\": \"my-merged-poco-address1-formatted\",\n" +
            "      \"streetAddress\": \"my-merged-poco-address1-street-address\",\n" +
            "      \"locality\": \"my-merged-poco-address1-locality\",\n" +
            "      \"region\": \"my-merged-poco-address1-region\",\n" +
            "      \"postalCode\": \"my-merged-poco-address1-postal-code\",\n" +
            "      \"country\": \"my-merged-poco-address1-country\",\n" +
            "      \"type\": \"my-merged-poco-address1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"formatted\": \"my-merged-poco-address2-formatted\",\n" +
            "      \"streetAddress\": \"my-merged-poco-address2-street-address\",\n" +
            "      \"locality\": \"my-merged-poco-address2-locality\",\n" +
            "      \"region\": \"my-merged-poco-address2-region\",\n" +
            "      \"postalCode\": \"my-merged-poco-address2-postal-code\",\n" +
            "      \"country\": \"my-merged-poco-address2-country\",\n" +
            "      \"type\": \"my-merged-poco-address2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"organizations\": [{\n" +
            "      \"name\": \"my-merged-poco-organization1-name\",\n" +
            "      \"department\": \"my-merged-poco-organization1-department\",\n" +
            "      \"title\": \"my-merged-poco-organization1-title\",\n" +
            "      \"type\": \"my-merged-poco-organization1-type\",\n" +
            "      \"startDate\": \"my-merged-poco-organization1-start-date\",\n" +
            "      \"endDate\": \"my-merged-poco-organization1-end-date\",\n" +
            "      \"location\": \"my-merged-poco-organization1-location\",\n" +
            "      \"description\": \"my-merged-poco-organization1-description\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"name\": \"my-merged-poco-organization2-name\",\n" +
            "      \"department\": \"my-merged-poco-organization2-department\",\n" +
            "      \"title\": \"my-merged-poco-organization2-title\",\n" +
            "      \"type\": \"my-merged-poco-organization2-type\",\n" +
            "      \"startDate\": \"my-merged-poco-organization2-start-date\",\n" +
            "      \"endDate\": \"my-merged-poco-organization2-end-date\",\n" +
            "      \"location\": \"my-merged-poco-organization2-location\",\n" +
            "      \"description\": \"my-merged-poco-organization2-description\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"accounts\": [{\n" +
            "      \"domain\": \"my-merged-poco-account1-domain\",\n" +
            "      \"username\": \"my-merged-poco-account1-username\",\n" +
            "      \"userid\": \"my-merged-poco-account1-userid\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"domain\": \"my-merged-poco-account2-domain\",\n" +
            "      \"username\": \"my-merged-poco-account2-username\",\n" +
            "      \"userid\": \"my-merged-poco-account2-userid\",\n" +
            "      \"primary\": false\n" +
            "    }]\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "    \"friend1\",\n" +
            "    \"friend2\",\n" +
            "    \"friend3\"\n" +
            "  ],\n" +
            "  \"limitedData\": false,\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        Profile profile = response.getProfile();
        assertNotNull(profile);
        assertEquals("my-identifier", profile.getIdentifier());
        assertEquals("my-provider-name", profile.getProviderName());
        assertEquals("my-primary-key", profile.getPrimaryKey());
        assertEquals("my-display-name", profile.getDisplayName());
        assertEquals("my-preferred-username", profile.getPreferredUsername());
        assertNotNull(profile.getName());
        assertEquals("my-name-formatted", profile.getName().getFormatted());
        assertEquals("my-name-family-name", profile.getName().getFamilyName());
        assertEquals("my-name-given-name", profile.getName().getGivenName());
        assertEquals("my-name-middle-name", profile.getName().getMiddleName());
        assertEquals("my-name-honorific-prefix", profile.getName().getHonorificPrefix());
        assertEquals("my-name-honorific-suffix", profile.getName().getHonorificSuffix());
        assertEquals("my-gender", profile.getGender());
        assertEquals(dateFormatter.parse("1977-05-07"), profile.getBirthday());
        assertEquals("my-utc-offset", profile.getUtcOffset());
        assertEquals("my-email", profile.getEmail());
        assertEquals("my-verified-email", profile.getVerifiedEmail());
        assertEquals("my-url", profile.getUrl());
        assertEquals("my-phone-number", profile.getPhoneNumber());
        assertEquals("my-photo", profile.getPhoto());
        assertNotNull(profile.getAddress());
        assertEquals("my-address-formatted", profile.getAddress().getFormatted());
        assertEquals("my-address-street-address", profile.getAddress().getStreetAddress());
        assertEquals("my-address-locality", profile.getAddress().getLocality());
        assertEquals("my-address-region", profile.getAddress().getRegion());
        assertEquals("my-address-postal-code", profile.getAddress().getPostalCode());
        assertEquals("my-address-country", profile.getAddress().getCountry());
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertEquals("my-access-credentials-type", accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
        assertEquals("my-uid", accessCredentials.getUid());
        assertEquals("my-access-token", accessCredentials.getAccessToken());
        assertEquals(new Long(1234567890), accessCredentials.getExpires());
        assertEquals("my-eact", accessCredentials.getEact());
        
        Contact mergedPoco = response.getMergedPoco();
        assertNotNull(mergedPoco);
        assertEquals("my-merged-poco-id", mergedPoco.getId());
        assertEquals("my-merged-poco-display-name", mergedPoco.getDisplayName());
        assertNotNull(mergedPoco.getName());
        assertEquals("my-merged-poco-formatted-name", mergedPoco.getName().getFormatted());
        assertEquals("my-merged-poco-family-name", mergedPoco.getName().getFamilyName());
        assertEquals("my-merged-poco-given-name", mergedPoco.getName().getGivenName());
        assertEquals("my-merged-poco-middle-name", mergedPoco.getName().getMiddleName());
        assertEquals("my-merged-poco-honorific-prefix", mergedPoco.getName().getHonorificPrefix());
        assertEquals("my-merged-poco-honorific-suffix", mergedPoco.getName().getHonorificSuffix());
        assertEquals("my-merged-poco-nickname", mergedPoco.getNickname());
        assertEquals(dateTimeFormatter.parse("2008-01-02 03:04:05"), mergedPoco.getPublished());
        assertEquals(dateTimeFormatter.parse("2010-11-12 13:14:15"), mergedPoco.getUpdated());
        assertEquals(dateFormatter.parse("1977-05-07"), mergedPoco.getBirthday());
        assertEquals(dateFormatter.parse("2001-09-23"), mergedPoco.getAnniversary());
        assertEquals("my-merged-poco-gender", mergedPoco.getGender());
        assertEquals("my-merged-poco-note", mergedPoco.getNote());
        assertEquals("my-merged-poco-preferred-username", mergedPoco.getPreferredUsername());
        assertEquals("my-merged-poco-utc-offset", mergedPoco.getUtcOffset());
        assertFalse(mergedPoco.isConnected());
        List<Email> emails = mergedPoco.getEmails();
        assertEquals(2, emails.size());
        Email email1 = emails.get(0);
        assertEquals("my-merged-poco-email1-value", email1.getValue());
        assertEquals("my-merged-poco-email1-type", email1.getType());
        assertTrue(email1.isPrimary());
        Email email2 = emails.get(1);
        assertEquals("my-merged-poco-email2-value", email2.getValue());
        assertEquals("my-merged-poco-email2-type", email2.getType());
        assertFalse(email2.isPrimary());
        List<Url> urls = mergedPoco.getUrls();
        assertEquals(2, urls.size());
        Url url1 = urls.get(0);
        assertEquals("my-merged-poco-url1-value", url1.getValue());
        assertEquals("my-merged-poco-url1-type", url1.getType());
        assertTrue(url1.isPrimary());
        Url url2 = urls.get(1);
        assertEquals("my-merged-poco-url2-value", url2.getValue());
        assertEquals("my-merged-poco-url2-type", url2.getType());
        assertFalse(url2.isPrimary());
        List<PhoneNumber> phoneNumbers = mergedPoco.getPhoneNumbers();
        assertEquals(2, phoneNumbers.size());
        PhoneNumber phoneNumber1 = phoneNumbers.get(0);
        assertEquals("my-merged-poco-phone-number1-value", phoneNumber1.getValue());
        assertEquals("my-merged-poco-phone-number1-type", phoneNumber1.getType());
        assertTrue(phoneNumber1.isPrimary());
        PhoneNumber phoneNumber2 = phoneNumbers.get(1);
        assertEquals("my-merged-poco-phone-number2-value", phoneNumber2.getValue());
        assertEquals("my-merged-poco-phone-number2-type", phoneNumber2.getType());
        assertFalse(phoneNumber2.isPrimary());
        List<IM> ims = mergedPoco.getIms();
        assertEquals(2, ims.size());
        IM im1 = ims.get(0);
        assertEquals("my-merged-poco-im1-value", im1.getValue());
        assertEquals("my-merged-poco-im1-type", im1.getType());
        assertTrue(im1.isPrimary());
        IM im2 = ims.get(1);
        assertEquals("my-merged-poco-im2-value", im2.getValue());
        assertEquals("my-merged-poco-im2-type", im2.getType());
        assertFalse(im2.isPrimary());
        List<Photo> photos = mergedPoco.getPhotos();
        assertEquals(2, photos.size());
        Photo photo1 = photos.get(0);
        assertEquals("my-merged-poco-photo1-value", photo1.getValue());
        assertEquals("my-merged-poco-photo1-type", photo1.getType());
        assertTrue(photo1.isPrimary());
        Photo photo2 = photos.get(1);
        assertEquals("my-merged-poco-photo2-value", photo2.getValue());
        assertEquals("my-merged-poco-photo2-type", photo2.getType());
        assertFalse(photo2.isPrimary());
        List<String> tags = mergedPoco.getTags();
        assertEquals(2, tags.size());
        assertTrue(tags.contains("my-merged-poco-tag1"));
        assertTrue(tags.contains("my-merged-poco-tag2"));
        List<Relationship> relationships = mergedPoco.getRelationships();
        assertEquals(2, relationships.size());
        Relationship relationship1 = relationships.get(0);
        assertEquals("my-merged-poco-relationship1", relationship1.getValue());
        Relationship relationship2 = relationships.get(1);
        assertEquals("my-merged-poco-relationship2", relationship2.getValue());
        List<com.googlecode.janrain4j.api.engage.response.poco.Address> addresses = mergedPoco.getAddresses();
        assertEquals(2, addresses.size());
        com.googlecode.janrain4j.api.engage.response.poco.Address address1 = addresses.get(0);
        assertEquals("my-merged-poco-address1-formatted", address1.getFormatted());
        assertEquals("my-merged-poco-address1-street-address", address1.getStreetAddress());
        assertEquals("my-merged-poco-address1-locality", address1.getLocality());
        assertEquals("my-merged-poco-address1-region", address1.getRegion());
        assertEquals("my-merged-poco-address1-postal-code", address1.getPostalCode());
        assertEquals("my-merged-poco-address1-country", address1.getCountry());
        assertEquals("my-merged-poco-address1-type", address1.getType());
        assertTrue(address1.isPrimary());
        com.googlecode.janrain4j.api.engage.response.poco.Address address2 = addresses.get(1);
        assertEquals("my-merged-poco-address2-formatted", address2.getFormatted());
        assertEquals("my-merged-poco-address2-street-address", address2.getStreetAddress());
        assertEquals("my-merged-poco-address2-locality", address2.getLocality());
        assertEquals("my-merged-poco-address2-region", address2.getRegion());
        assertEquals("my-merged-poco-address2-postal-code", address2.getPostalCode());
        assertEquals("my-merged-poco-address2-country", address2.getCountry());
        assertEquals("my-merged-poco-address2-type", address2.getType());
        assertFalse(address2.isPrimary());
        List<Organization> organizations = mergedPoco.getOrganizations();
        assertEquals(2, organizations.size());
        Organization organization1 = organizations.get(0);
        assertEquals("my-merged-poco-organization1-name", organization1.getName());
        assertEquals("my-merged-poco-organization1-department", organization1.getDepartment());
        assertEquals("my-merged-poco-organization1-title", organization1.getTitle());
        assertEquals("my-merged-poco-organization1-type", organization1.getType());
        assertEquals("my-merged-poco-organization1-start-date", organization1.getStartDate());
        assertEquals("my-merged-poco-organization1-end-date", organization1.getEndDate());
        assertEquals("my-merged-poco-organization1-location", organization1.getLocation());
        assertEquals("my-merged-poco-organization1-description", organization1.getDescription());
        assertTrue(organization1.isPrimary());
        Organization organization2 = organizations.get(1);
        assertEquals("my-merged-poco-organization2-name", organization2.getName());
        assertEquals("my-merged-poco-organization2-department", organization2.getDepartment());
        assertEquals("my-merged-poco-organization2-title", organization2.getTitle());
        assertEquals("my-merged-poco-organization2-type", organization2.getType());
        assertEquals("my-merged-poco-organization2-start-date", organization2.getStartDate());
        assertEquals("my-merged-poco-organization2-end-date", organization2.getEndDate());
        assertEquals("my-merged-poco-organization2-location", organization2.getLocation());
        assertEquals("my-merged-poco-organization2-description", organization2.getDescription());
        assertFalse(organization2.isPrimary());
        List<Account> accounts = mergedPoco.getAccounts();
        assertEquals(2, accounts.size());
        Account account1 = accounts.get(0);
        assertEquals("my-merged-poco-account1-domain", account1.getDomain());
        assertEquals("my-merged-poco-account1-username", account1.getUsername());
        assertEquals("my-merged-poco-account1-userid", account1.getUserid());
        assertTrue(account1.isPrimary());
        Account account2 = accounts.get(1);
        assertEquals("my-merged-poco-account2-domain", account2.getDomain());
        assertEquals("my-merged-poco-account2-username", account2.getUsername());
        assertEquals("my-merged-poco-account2-userid", account2.getUserid());
        assertFalse(account2.isPrimary());
        
        // TODO test open social fields
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
        
        assertFalse(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithOauthAccessCredentials() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"accessCredentials\": {\n" +
            "    \"type\": \"OAuth\",\n" +
            "    \"oauthToken\": \"my-oauth-token\",\n" +
            "    \"oauthTokenSecret\": \"my-oauth-token-secret\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isOauth());
        assertEquals(AccessCredentials.TYPE_OAUTH, accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
    }
    
    @Test
    public void testUserDataResponseWithFacebookAccessCredentials() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"accessCredentials\": {\n" +
            "    \"type\": \"Facebook\",\n" +
            "    \"uid\": \"my-uid\",\n" +
            "    \"accessToken\": \"my-access-token\",\n" +
            "    \"expires\": 1234567890\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isFacebook());
        assertEquals(AccessCredentials.TYPE_FACEBOOK, accessCredentials.getType());
        assertEquals("my-uid", accessCredentials.getUid());
        assertEquals("my-access-token", accessCredentials.getAccessToken());
        assertEquals(new Long(1234567890), accessCredentials.getExpires());
    }
    
    @Test
    public void testUserDataResponseWithWindowsLiveAccessCredentials() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"accessCredentials\": {\n" +
            "    \"type\": \"WindowsLive\",\n" +
            "    \"eact\": \"my-eact\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isWindowsLive());
        assertEquals(AccessCredentials.TYPE_WINDOWS_LIVE, accessCredentials.getType());
        assertEquals("my-eact", accessCredentials.getEact());
    }
    
    @Test
    public void testUserDataResponseWithMergedPoco() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"merged_poco\": {\n" +
            "    \"id\": \"my-merged-poco-id\",\n" +
            "    \"displayName\": \"my-merged-poco-display-name\",\n" +
            "    \"name\": {\n" +
            "      \"formatted\": \"my-merged-poco-formatted-name\",\n" +
            "      \"familyName\": \"my-merged-poco-family-name\",\n" +
            "      \"givenName\": \"my-merged-poco-given-name\",\n" +
            "      \"middleName\": \"my-merged-poco-middle-name\",\n" +
            "      \"honorificPrefix\": \"my-merged-poco-honorific-prefix\",\n" +
            "      \"honorificSuffix\": \"my-merged-poco-honorific-suffix\"\n" +
            "    },\n" +
            "    \"nickname\": \"my-merged-poco-nickname\",\n" +
            "    \"published\": \"2008-01-02T03:04:05Z\",\n" +
            "    \"updated\": \"2010-11-12T13:14:15Z\",\n" +
            "    \"birthday\": \"1977-05-07\",\n" +
            "    \"anniversary\": \"2001-09-23\",\n" +
            "    \"gender\": \"my-merged-poco-gender\",\n" +
            "    \"note\": \"my-merged-poco-note\",\n" +
            "    \"preferredUsername\": \"my-merged-poco-preferred-username\",\n" +
            "    \"utcOffset\": \"my-merged-poco-utc-offset\",\n" +
            "    \"connected\": false,\n" +
            "    \"emails\": [{\n" +
            "      \"value\": \"my-merged-poco-email1-value\",\n" +
            "      \"type\": \"my-merged-poco-email1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-email2-value\",\n" +
            "      \"type\": \"my-merged-poco-email2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"urls\": [{\n" +
            "      \"value\": \"my-merged-poco-url1-value\",\n" +
            "      \"type\": \"my-merged-poco-url1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-url2-value\",\n" +
            "      \"type\": \"my-merged-poco-url2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"phoneNumbers\": [{\n" +
            "      \"value\": \"my-merged-poco-phone-number1-value\",\n" +
            "      \"type\": \"my-merged-poco-phone-number1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-phone-number2-value\",\n" +
            "      \"type\": \"my-merged-poco-phone-number2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"ims\": [{\n" +
            "      \"value\": \"my-merged-poco-im1-value\",\n" +
            "      \"type\": \"my-merged-poco-im1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-im2-value\",\n" +
            "      \"type\": \"my-merged-poco-im2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"photos\": [{\n" +
            "      \"value\": \"my-merged-poco-photo1-value\",\n" +
            "      \"type\": \"my-merged-poco-photo1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"value\": \"my-merged-poco-photo2-value\",\n" +
            "      \"type\": \"my-merged-poco-photo2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"tags\": [\n" +
            "      \"my-merged-poco-tag1\",\n" +
            "      \"my-merged-poco-tag2\",\n" +
            "    ],\n" +
            "    \"relationships\": [\n" +
            "      \"my-merged-poco-relationship1\",\n" +
            "      \"my-merged-poco-relationship2\",\n" +
            "    ],\n" +
            "    \"addresses\": [{\n" +
            "      \"formatted\": \"my-merged-poco-address1-formatted\",\n" +
            "      \"streetAddress\": \"my-merged-poco-address1-street-address\",\n" +
            "      \"locality\": \"my-merged-poco-address1-locality\",\n" +
            "      \"region\": \"my-merged-poco-address1-region\",\n" +
            "      \"postalCode\": \"my-merged-poco-address1-postal-code\",\n" +
            "      \"country\": \"my-merged-poco-address1-country\",\n" +
            "      \"type\": \"my-merged-poco-address1-type\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"formatted\": \"my-merged-poco-address2-formatted\",\n" +
            "      \"streetAddress\": \"my-merged-poco-address2-street-address\",\n" +
            "      \"locality\": \"my-merged-poco-address2-locality\",\n" +
            "      \"region\": \"my-merged-poco-address2-region\",\n" +
            "      \"postalCode\": \"my-merged-poco-address2-postal-code\",\n" +
            "      \"country\": \"my-merged-poco-address2-country\",\n" +
            "      \"type\": \"my-merged-poco-address2-type\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"organizations\": [{\n" +
            "      \"name\": \"my-merged-poco-organization1-name\",\n" +
            "      \"department\": \"my-merged-poco-organization1-department\",\n" +
            "      \"title\": \"my-merged-poco-organization1-title\",\n" +
            "      \"type\": \"my-merged-poco-organization1-type\",\n" +
            "      \"startDate\": \"my-merged-poco-organization1-start-date\",\n" +
            "      \"endDate\": \"my-merged-poco-organization1-end-date\",\n" +
            "      \"location\": \"my-merged-poco-organization1-location\",\n" +
            "      \"description\": \"my-merged-poco-organization1-description\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"name\": \"my-merged-poco-organization2-name\",\n" +
            "      \"department\": \"my-merged-poco-organization2-department\",\n" +
            "      \"title\": \"my-merged-poco-organization2-title\",\n" +
            "      \"type\": \"my-merged-poco-organization2-type\",\n" +
            "      \"startDate\": \"my-merged-poco-organization2-start-date\",\n" +
            "      \"endDate\": \"my-merged-poco-organization2-end-date\",\n" +
            "      \"location\": \"my-merged-poco-organization2-location\",\n" +
            "      \"description\": \"my-merged-poco-organization2-description\",\n" +
            "      \"primary\": false\n" +
            "    }],\n" +
            "    \"accounts\": [{\n" +
            "      \"domain\": \"my-merged-poco-account1-domain\",\n" +
            "      \"username\": \"my-merged-poco-account1-username\",\n" +
            "      \"userid\": \"my-merged-poco-account1-userid\",\n" +
            "      \"primary\": true\n" +
            "    }, {\n" +
            "      \"domain\": \"my-merged-poco-account2-domain\",\n" +
            "      \"username\": \"my-merged-poco-account2-username\",\n" +
            "      \"userid\": \"my-merged-poco-account2-userid\",\n" +
            "      \"primary\": false\n" +
            "    }]\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        Contact mergedPoco = response.getMergedPoco();
        assertNotNull(mergedPoco);
        assertEquals("my-merged-poco-id", mergedPoco.getId());
        assertEquals("my-merged-poco-display-name", mergedPoco.getDisplayName());
        assertNotNull(mergedPoco.getName());
        assertEquals("my-merged-poco-formatted-name", mergedPoco.getName().getFormatted());
        assertEquals("my-merged-poco-family-name", mergedPoco.getName().getFamilyName());
        assertEquals("my-merged-poco-given-name", mergedPoco.getName().getGivenName());
        assertEquals("my-merged-poco-middle-name", mergedPoco.getName().getMiddleName());
        assertEquals("my-merged-poco-honorific-prefix", mergedPoco.getName().getHonorificPrefix());
        assertEquals("my-merged-poco-honorific-suffix", mergedPoco.getName().getHonorificSuffix());
        assertEquals("my-merged-poco-nickname", mergedPoco.getNickname());
        assertEquals(dateTimeFormatter.parse("2008-01-02 03:04:05"), mergedPoco.getPublished());
        assertEquals(dateTimeFormatter.parse("2010-11-12 13:14:15"), mergedPoco.getUpdated());
        assertEquals(dateFormatter.parse("1977-05-07"), mergedPoco.getBirthday());
        assertEquals(dateFormatter.parse("2001-09-23"), mergedPoco.getAnniversary());
        assertEquals("my-merged-poco-gender", mergedPoco.getGender());
        assertEquals("my-merged-poco-note", mergedPoco.getNote());
        assertEquals("my-merged-poco-preferred-username", mergedPoco.getPreferredUsername());
        assertEquals("my-merged-poco-utc-offset", mergedPoco.getUtcOffset());
        assertFalse(mergedPoco.isConnected());
        List<Email> emails = mergedPoco.getEmails();
        assertEquals(2, emails.size());
        Email email1 = emails.get(0);
        assertEquals("my-merged-poco-email1-value", email1.getValue());
        assertEquals("my-merged-poco-email1-type", email1.getType());
        assertTrue(email1.isPrimary());
        Email email2 = emails.get(1);
        assertEquals("my-merged-poco-email2-value", email2.getValue());
        assertEquals("my-merged-poco-email2-type", email2.getType());
        assertFalse(email2.isPrimary());
        List<Url> urls = mergedPoco.getUrls();
        assertEquals(2, urls.size());
        Url url1 = urls.get(0);
        assertEquals("my-merged-poco-url1-value", url1.getValue());
        assertEquals("my-merged-poco-url1-type", url1.getType());
        assertTrue(url1.isPrimary());
        Url url2 = urls.get(1);
        assertEquals("my-merged-poco-url2-value", url2.getValue());
        assertEquals("my-merged-poco-url2-type", url2.getType());
        assertFalse(url2.isPrimary());
        List<PhoneNumber> phoneNumbers = mergedPoco.getPhoneNumbers();
        assertEquals(2, phoneNumbers.size());
        PhoneNumber phoneNumber1 = phoneNumbers.get(0);
        assertEquals("my-merged-poco-phone-number1-value", phoneNumber1.getValue());
        assertEquals("my-merged-poco-phone-number1-type", phoneNumber1.getType());
        assertTrue(phoneNumber1.isPrimary());
        PhoneNumber phoneNumber2 = phoneNumbers.get(1);
        assertEquals("my-merged-poco-phone-number2-value", phoneNumber2.getValue());
        assertEquals("my-merged-poco-phone-number2-type", phoneNumber2.getType());
        assertFalse(phoneNumber2.isPrimary());
        List<IM> ims = mergedPoco.getIms();
        assertEquals(2, ims.size());
        IM im1 = ims.get(0);
        assertEquals("my-merged-poco-im1-value", im1.getValue());
        assertEquals("my-merged-poco-im1-type", im1.getType());
        assertTrue(im1.isPrimary());
        IM im2 = ims.get(1);
        assertEquals("my-merged-poco-im2-value", im2.getValue());
        assertEquals("my-merged-poco-im2-type", im2.getType());
        assertFalse(im2.isPrimary());
        List<Photo> photos = mergedPoco.getPhotos();
        assertEquals(2, photos.size());
        Photo photo1 = photos.get(0);
        assertEquals("my-merged-poco-photo1-value", photo1.getValue());
        assertEquals("my-merged-poco-photo1-type", photo1.getType());
        assertTrue(photo1.isPrimary());
        Photo photo2 = photos.get(1);
        assertEquals("my-merged-poco-photo2-value", photo2.getValue());
        assertEquals("my-merged-poco-photo2-type", photo2.getType());
        assertFalse(photo2.isPrimary());
        List<String> tags = mergedPoco.getTags();
        assertEquals(2, tags.size());
        assertTrue(tags.contains("my-merged-poco-tag1"));
        assertTrue(tags.contains("my-merged-poco-tag2"));
        List<Relationship> relationships = mergedPoco.getRelationships();
        assertEquals(2, relationships.size());
        Relationship relationship1 = relationships.get(0);
        assertEquals("my-merged-poco-relationship1", relationship1.getValue());
        Relationship relationship2 = relationships.get(1);
        assertEquals("my-merged-poco-relationship2", relationship2.getValue());
        List<Address> addresses = mergedPoco.getAddresses();
        assertEquals(2, addresses.size());
        Address address1 = addresses.get(0);
        assertEquals("my-merged-poco-address1-formatted", address1.getFormatted());
        assertEquals("my-merged-poco-address1-street-address", address1.getStreetAddress());
        assertEquals("my-merged-poco-address1-locality", address1.getLocality());
        assertEquals("my-merged-poco-address1-region", address1.getRegion());
        assertEquals("my-merged-poco-address1-postal-code", address1.getPostalCode());
        assertEquals("my-merged-poco-address1-country", address1.getCountry());
        assertEquals("my-merged-poco-address1-type", address1.getType());
        assertTrue(address1.isPrimary());
        com.googlecode.janrain4j.api.engage.response.poco.Address address2 = addresses.get(1);
        assertEquals("my-merged-poco-address2-formatted", address2.getFormatted());
        assertEquals("my-merged-poco-address2-street-address", address2.getStreetAddress());
        assertEquals("my-merged-poco-address2-locality", address2.getLocality());
        assertEquals("my-merged-poco-address2-region", address2.getRegion());
        assertEquals("my-merged-poco-address2-postal-code", address2.getPostalCode());
        assertEquals("my-merged-poco-address2-country", address2.getCountry());
        assertEquals("my-merged-poco-address2-type", address2.getType());
        assertFalse(address2.isPrimary());
        List<Organization> organizations = mergedPoco.getOrganizations();
        assertEquals(2, organizations.size());
        Organization organization1 = organizations.get(0);
        assertEquals("my-merged-poco-organization1-name", organization1.getName());
        assertEquals("my-merged-poco-organization1-department", organization1.getDepartment());
        assertEquals("my-merged-poco-organization1-title", organization1.getTitle());
        assertEquals("my-merged-poco-organization1-type", organization1.getType());
        assertEquals("my-merged-poco-organization1-start-date", organization1.getStartDate());
        assertEquals("my-merged-poco-organization1-end-date", organization1.getEndDate());
        assertEquals("my-merged-poco-organization1-location", organization1.getLocation());
        assertEquals("my-merged-poco-organization1-description", organization1.getDescription());
        assertTrue(organization1.isPrimary());
        Organization organization2 = organizations.get(1);
        assertEquals("my-merged-poco-organization2-name", organization2.getName());
        assertEquals("my-merged-poco-organization2-department", organization2.getDepartment());
        assertEquals("my-merged-poco-organization2-title", organization2.getTitle());
        assertEquals("my-merged-poco-organization2-type", organization2.getType());
        assertEquals("my-merged-poco-organization2-start-date", organization2.getStartDate());
        assertEquals("my-merged-poco-organization2-end-date", organization2.getEndDate());
        assertEquals("my-merged-poco-organization2-location", organization2.getLocation());
        assertEquals("my-merged-poco-organization2-description", organization2.getDescription());
        assertFalse(organization2.isPrimary());
        List<Account> accounts = mergedPoco.getAccounts();
        assertEquals(2, accounts.size());
        Account account1 = accounts.get(0);
        assertEquals("my-merged-poco-account1-domain", account1.getDomain());
        assertEquals("my-merged-poco-account1-username", account1.getUsername());
        assertEquals("my-merged-poco-account1-userid", account1.getUserid());
        assertTrue(account1.isPrimary());
        Account account2 = accounts.get(1);
        assertEquals("my-merged-poco-account2-domain", account2.getDomain());
        assertEquals("my-merged-poco-account2-username", account2.getUsername());
        assertEquals("my-merged-poco-account2-userid", account2.getUserid());
        assertFalse(account2.isPrimary());
        
        // TODO test open social fields
    }
    
    @Test
    public void testUserDataResponseWithNoFriends() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "  ],\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(0, friends.size());
    }
    
    @Test
    public void testUserDataResponseWithSingleFriend() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "    \"friend1\"\n" +
            "  ],\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(1, friends.size());
        assertTrue(friends.contains("friend1"));
    }
    
    @Test
    public void testUserDataResponseWithMultipleFriends() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "    \"friend1\",\n" +
            "    \"friend2\",\n" +
            "    \"friend3\"\n" +
            "  ],\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
    }
    
    @Test
    public void testUserDataResponseWithLimitedDataIsTrue() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"limitedData\": true,\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        assertTrue(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithLimitedDataIsFalse() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"limitedData\": false,\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        assertFalse(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithLimitedDataNotProvided() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        assertFalse(response.isLimitedData());
    }
    
    @Test
    public void testSerializable() throws Exception {
        
        json =
            "{" +
            "  \"profile\": {\n" +
            "    \"displayName\": \"brian\",\n" +
            "    \"preferredUsername\": \"brian\",\n" +
            "    \"url\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(json);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(response);
        oos.close();
        
        assertTrue(out.toByteArray().length > 0);
        assertNotNull(response.getResponseAsJSONObject());
    }
}
