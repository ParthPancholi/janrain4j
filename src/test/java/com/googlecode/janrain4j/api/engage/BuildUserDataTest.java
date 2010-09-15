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
package com.googlecode.janrain4j.api.engage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class BuildUserDataTest extends EngageServiceImplTestCase {

    @Test
    public void testBuildUserData() throws Exception {
        UserData userData = service.buildUserData(new JSONObject(userDataResponse), false);
        
        assertNotNull(userData);
        Profile profile = userData.getProfile();
        assertNotNull(profile);
        assertEquals("http://brian.myopenid.com/", profile.getIdentifier());
        assertEquals("Other", profile.getProviderName());
        assertEquals("brian", profile.getDisplayName());
        assertEquals("brian", profile.getPreferredUsername());
        assertEquals("http://brian.myopenid.com/", profile.getUrl());
    }
    
    @Test
    public void testBuildUserDataWithMinimalSetOfData() throws Exception {
        String response =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        assertNotNull(userData);
        Profile profile = userData.getProfile();
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
        assertNull(userData.getProfile().getUrl());
        assertNull(profile.getPhoneNumber());
        assertNull(profile.getPhoto());
        assertNull(profile.getAddress());
        assertFalse(profile.isLimitedData());
        assertNull(userData.getAccessCredentials());
        assertNull(userData.getMergedPoco());
        assertNull(userData.getFriends());
    }
    
    @Test
    public void testBuildUserDataWithFullSetOfData() throws Exception {
        String response =
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
            "    },\n" +
            "    \"limitedData\": false\n" +
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
            "  \"stat\": \"ok\"\n" +
            "}";
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        assertNotNull(userData);
        Profile profile = userData.getProfile();
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
        assertEquals("my-url", userData.getProfile().getUrl());
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
        assertFalse(profile.isLimitedData());
        AccessCredentials accessCredentials = userData.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertEquals("my-access-credentials-type", accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
        assertEquals("my-uid", accessCredentials.getUid());
        assertEquals("my-access-token", accessCredentials.getAccessToken());
        assertEquals(new Long(1234567890), accessCredentials.getExpires());
        assertEquals("my-eact", accessCredentials.getEact());
        List<String> friends = userData.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
    }
    
    @Test
    public void testBuildUserDataWithLimitedData() throws Exception {
        String response =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"limitedData\": true\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        assertTrue(userData.getProfile().isLimitedData());
    }
    
    @Test
    public void testBuildUserDataWithOauthAccessCredentials() throws Exception {
        String response =
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
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        AccessCredentials accessCredentials = userData.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isOauth());
        assertEquals(AccessCredentials.TYPE_OAUTH, accessCredentials.getType());
        assertEquals("my-oauth-token", accessCredentials.getOauthToken());
        assertEquals("my-oauth-token-secret", accessCredentials.getOauthTokenSecret());
    }
    
    @Test
    public void testBuildUserDataWithFacebookAccessCredentials() throws Exception {
        String response =
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
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        AccessCredentials accessCredentials = userData.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isFacebook());
        assertEquals(AccessCredentials.TYPE_FACEBOOK, accessCredentials.getType());
        assertEquals("my-uid", accessCredentials.getUid());
        assertEquals("my-access-token", accessCredentials.getAccessToken());
        assertEquals(new Long(1234567890), accessCredentials.getExpires());
    }
    
    @Test
    public void testBuildUserDataWithWindowsLiveAccessCredentials() throws Exception {
        String response =
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
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        AccessCredentials accessCredentials = userData.getAccessCredentials();
        assertNotNull(accessCredentials);
        assertTrue(accessCredentials.isWindowsLive());
        assertEquals(AccessCredentials.TYPE_WINDOWS_LIVE, accessCredentials.getType());
        assertEquals("my-eact", accessCredentials.getEact());
    }
    
    @Test
    public void testBuildUserDataWithNoFriends() throws Exception {
        String response =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "  ],\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        List<String> friends = userData.getFriends();
        assertNotNull(friends);
        assertEquals(0, friends.size());
    }
    
    @Test
    public void testBuildUserDataWithSingleFriend() throws Exception {
        String response =
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
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        List<String> friends = userData.getFriends();
        assertNotNull(friends);
        assertEquals(1, friends.size());
        assertTrue(friends.contains("friend1"));
    }
    
    @Test
    public void testBuildUserDataWithMultipleFriends() throws Exception {
        String response =
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
        
        UserData userData = service.buildUserData(new JSONObject(response), false);
        
        List<String> friends = userData.getFriends();
        assertNotNull(friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("friend1"));
        assertTrue(friends.contains("friend2"));
        assertTrue(friends.contains("friend3"));
    }
    
    @Test
    public void testBuildUserDataExtended() throws Exception {
        String response =
            "{" +
            "  \"profile\": {\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"limitedData\": true\n" +
            // TODO
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        UserData userData = service.buildUserData(new JSONObject(response), true);
        
        assertNotNull(userData); // TODO replace with actual assertiations
    }
}
