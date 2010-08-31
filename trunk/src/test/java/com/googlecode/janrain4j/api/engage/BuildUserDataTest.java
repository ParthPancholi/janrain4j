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
        Name name = profile.getName();
        assertNotNull(name);
        assertNull(name.getFormatted());
        assertNull(name.getFamilyName());
        assertNull(name.getGivenName());
        assertNull(name.getMiddleName());
        assertNull(name.getHonorificPrefix());
        assertNull(name.getHonorificSuffix());
        assertNull(profile.getGender());
        assertNull(profile.getBirthday());
        assertNull(profile.getUtcOffset());
        assertNull(profile.getEmail());
        assertNull(profile.getVerifiedEmail());
        assertNull(userData.getProfile().getUrl());
        assertNull(profile.getPhoneNumber());
        assertNull(profile.getPhoto());
        Address address = profile.getAddress();
        assertNotNull(address);
        assertNull(address.getFormatted());
        assertNull(address.getStreetAddress());
        assertNull(address.getLocality());
        assertNull(address.getRegion());
        assertNull(address.getPostalCode());
        assertNull(address.getCountry());
        assertFalse(profile.hasLimitedData());
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
        assertFalse(profile.hasLimitedData());
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
        
        assertTrue(userData.getProfile().hasLimitedData());
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
