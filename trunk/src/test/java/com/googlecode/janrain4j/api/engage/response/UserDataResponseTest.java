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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

@SuppressWarnings("serial")
public class UserDataResponseTest {

    private String jsonResponse = null;
    private UserDataResponse response = null;
    
    @Test
    public void testUserDataResponse() throws Exception {
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        Profile profile = response.getProfile();
        assertNotNull(profile);
        assertEquals("http://brian.myopenid.com/", profile.getIdentifier());
        assertEquals("Other", profile.getProviderName());
        assertEquals("brian", profile.getDisplayName());
        assertEquals("brian", profile.getPreferredUsername());
        assertEquals("http://brian.myopenid.com/", profile.getUrl());
    }
    
    @Test
    public void testUserDataResponseWithMinimalSetOfData() throws Exception {
        
        jsonResponse =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        assertEquals(new JSONObject(jsonResponse).optJSONObject("profile").toString(), response.getProfileAsJSONObject().toString());
        
        Profile profile = response.getProfile();
        assertNotNull(profile);
        assertEquals("http://brian.myopenid.com/", profile.getIdentifier());
        assertEquals("Other", profile.getProviderName());
        assertNull(profile.getPrimaryKey());
        assertNull(profile.getDisplayName());
        assertNull(profile.getPreferredUsername());
        assertNull(profile.getName());
        assertNull(profile.getGender());
        assertNull(profile.getBirthday());
        assertNull(profile.getUtcOffset());
        assertNull(profile.getEmail());
        assertNull(profile.getVerifiedEmail());
        assertNull(profile.getUrl());
        assertNull(profile.getPhoneNumber());
        assertNull(profile.getPhoto());
        assertNull(profile.getAddress());
        assertNull(response.getAccessCredentials());
        assertNull(response.getMergedPoco());
        assertNull(response.getFriends());
        assertFalse(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithFullSetOfData() throws Exception {
        
        jsonResponse =
            "{" +
            "  \"profile\": {\n" +
            "    \"identifier\": \"my-identifier\",\n" +
            "    \"providerName\": \"my-provider-name\",\n" +
            "    \"primaryKey\": \"my-primary-key\",\n" +
            "    \"displayName\": \"my-display-name\",\n" +
            "    \"preferredUsername\": \"my-preferred-username\",\n" +
            "    \"name\": {\n" +
            "      \"formatted\": \"my-formatted-name\",\n" +
            "      \"familyName\": \"my-family-name\",\n" +
            "      \"givenName\": \"my-given-name\",\n" +
            "      \"middleName\": \"my-middle-name\",\n" +
            "      \"honorificPrefix\": \"my-honorific-prefix\",\n" +
            "      \"honorificSuffix\": \"my-honorific-suffix\"\n" +
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
            "      \"formatted\": \"my-formatted-address\",\n" +
            "      \"streetAddress\": \"my-street-address\",\n" +
            "      \"locality\": \"my-locality\",\n" +
            "      \"region\": \"my-region\",\n" +
            "      \"postalCode\": \"my-postal-code\",\n" +
            "      \"country\": \"my-country\"\n" +
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
            "  \"friends\": [\n" +
            "    \"friend1\",\n" +
            "    \"friend2\",\n" +
            "    \"friend3\"\n" +
            "  ],\n" +
            "  \"limitedData\": false,\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        assertEquals(new JSONObject(jsonResponse).optJSONObject("profile").toString(), response.getProfileAsJSONObject().toString());
        assertEquals(new JSONObject(jsonResponse).optJSONObject("accessCredentials").toString(), response.getAccessCredentialsAsJSONObject().toString());
        // TODO merged pocp
        assertEquals(new JSONObject(jsonResponse).optJSONArray("friends").toString(), response.getFriendsAsJSONArray().toString());
        
        Profile profile = response.getProfile();
        assertNotNull(profile);
        assertEquals("my-identifier", profile.getIdentifier());
        assertEquals("my-provider-name", profile.getProviderName());
        assertEquals("my-primary-key", profile.getPrimaryKey());
        assertEquals("my-display-name", profile.getDisplayName());
        assertEquals("my-preferred-username", profile.getPreferredUsername());
        Name name = profile.getName();
        assertNotNull(name);
        assertEquals("my-formatted-name", name.getFormatted());
        assertEquals("my-family-name", name.getFamilyName());
        assertEquals("my-given-name", name.getGivenName());
        assertEquals("my-middle-name", name.getMiddleName());
        assertEquals("my-honorific-prefix", name.getHonorificPrefix());
        assertEquals("my-honorific-suffix", name.getHonorificSuffix());
        assertEquals("my-gender", profile.getGender());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(dateFormatter.parse("1977-05-07"), profile.getBirthday());
        assertEquals("my-utc-offset", profile.getUtcOffset());
        assertEquals("my-email", profile.getEmail());
        assertEquals("my-verified-email", profile.getVerifiedEmail());
        assertEquals("my-url", profile.getUrl());
        assertEquals("my-phone-number", profile.getPhoneNumber());
        assertEquals("my-photo", profile.getPhoto());
        Address address = profile.getAddress();
        assertNotNull(address);
        assertEquals("my-formatted-address", address.getFormatted());
        assertEquals("my-street-address", address.getStreetAddress());
        assertEquals("my-locality", address.getLocality());
        assertEquals("my-region", address.getRegion());
        assertEquals("my-postal-code", address.getPostalCode());
        assertEquals("my-country", address.getCountry());
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertEquals("my-access-credentials-type", accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
        assertEquals("my-uid", accessCredentials.getUid());
        assertEquals("my-access-token", accessCredentials.getAccessToken());
        assertEquals(new Long(1234567890), accessCredentials.getExpires());
        assertEquals("my-eact", accessCredentials.getEact());
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
        
        assertFalse(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithLimitedData() throws Exception {
        
        jsonResponse =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"limitedData\": true,\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertTrue(response.isLimitedData());
    }
    
    @Test
    public void testUserDataResponseWithOauthAccessCredentials() throws Exception {
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONObject("accessCredentials").toString(), response.getAccessCredentialsAsJSONObject().toString());
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isOauth());
        assertEquals(AccessCredentials.TYPE_OAUTH, accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
    }
    
    @Test
    public void testUserDataResponseWithFacebookAccessCredentials() throws Exception {
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONObject("accessCredentials").toString(), response.getAccessCredentialsAsJSONObject().toString());
        
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
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONObject("accessCredentials").toString(), response.getAccessCredentialsAsJSONObject().toString());
        
        AccessCredentials accessCredentials = response.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isWindowsLive());
        assertEquals(AccessCredentials.TYPE_WINDOWS_LIVE, accessCredentials.getType());
        assertEquals("my-eact", accessCredentials.getEact());
    }
    
    @Test
    public void testUserDataResponseWithNoFriends() throws Exception {
        
        jsonResponse =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "  ],\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONArray("friends").toString(), response.getFriendsAsJSONArray().toString());
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(0, friends.size());
    }
    
    @Test
    public void testUserDataResponseWithSingleFriend() throws Exception {
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONArray("friends").toString(), response.getFriendsAsJSONArray().toString());
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(1, friends.size());
        assertTrue(friends.contains("friend1"));
    }
    
    @Test
    public void testUserDataResponseWithMultipleFriends() throws Exception {
        
        jsonResponse =
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
        
        response = new UserDataResponse(jsonResponse) {};
        
        assertEquals(new JSONObject(jsonResponse).optJSONArray("friends").toString(), response.getFriendsAsJSONArray().toString());
        
        List<String> friends = response.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
    }
}
