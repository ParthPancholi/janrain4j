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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ACTIVITY_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ACTIVITY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ALL_IDENTIFIERS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ALL_MAPPINGS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ANALYTICS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_URL;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.AUTH_INFO_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.END_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.EXTENDED_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.GET_CONTACTS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.GET_USER_DATA_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.IDENTIFIER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.LOCATION_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.MAPPINGS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.MAP_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.OVERWRITE_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PROVIDERS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.SET_AUTH_PROVIDERS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.SET_STATUS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.START_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.STATUS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.TOKEN_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNLINK_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNMAP_METHOD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.api.engage.request.Activity;
import com.googlecode.janrain4j.api.engage.response.AllMappingsResponse;
import com.googlecode.janrain4j.api.engage.response.AnalyticsResponse;
import com.googlecode.janrain4j.api.engage.response.ContactsResponse;
import com.googlecode.janrain4j.api.engage.response.MappingsResponse;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;
import com.googlecode.janrain4j.http.HttpClient;
import com.googlecode.janrain4j.http.HttpClientFactory;
import com.googlecode.janrain4j.http.HttpFailureException;
import com.googlecode.janrain4j.http.HttpResponse;
import com.googlecode.janrain4j.json.JSONObject;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EngageServiceImpl.class, HttpClientFactory.class})
public class EngageServiceImplTest {

protected EngageServiceImpl service = null;
    
    private Config config = null;
    
    private HttpClient httpClient = null;
    
    private HttpResponse httpResponse = null;
    
    private Map<String, String> params = null;
    
    private String apiKey = "my-api-key";
    private String token = "my-token";
    private String identifier = "my-identifier";
    private String primaryKey = "my-primary-key";
    private String status = "my-status";
    private String location = "my-location";
    private String activityString = "{ \"url\": \"http:\\/\\/my-url.com\\/\", \"action\": \"my-action\" }";
    private Activity activityObject = null;
    private List<String> providers = new ArrayList<String>();
    
    private String method = "some_method";
    private String url = null;
    
    private String successResponse = "{ \"stat\": \"ok\" }";
    
    private int errorCode = 99;
    private String errorMessage = "Some error message";
    private String errorResponse = "{ \"err\": { \"msg\": \"" + errorMessage + "\", \"code\": " + errorCode + " }, \"stat\": \"fail\" }";
    
    @Before
    public void setUp() throws Exception {
        // mock config
        config = mock(Config.class);
        when(config.getApiKey()).thenReturn(apiKey);
        
        // mock http client and response
        httpClient = mock(HttpClient.class);
        mockStatic(HttpClientFactory.class);
        when(HttpClientFactory.getHttpClient(config)).thenReturn(httpClient);
        httpResponse = mock(HttpResponse.class);
        
        // create service to test
        service = new EngageServiceImpl(config);
        
        // expected params in api call
        params = new HashMap<String, String>();
        
        activityObject = new Activity("http://my-url.com", "my-action");
    }
    
    @Test
    public void testAuthInfo() throws Exception {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + AUTH_INFO_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.authInfo(token));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testAuthInfoWithExtendedIsTrue() throws Exception {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + AUTH_INFO_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.authInfo(token, true));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testAuthInfoWithExtendedIsFalse() throws Exception {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + AUTH_INFO_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.authInfo(token, false));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testGetContacts() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + GET_CONTACTS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        ContactsResponse expected = mock(ContactsResponse.class);
        whenNew(ContactsResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.getContacts(identifier));
        
        verify(httpClient).post(url, params);
        verifyNew(ContactsResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testGetUserData() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + GET_USER_DATA_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.getUserData(identifier));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testGetUserDataWithExtendedIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + GET_USER_DATA_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.getUserData(identifier, true));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testGetUserDataWithExtendedIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + GET_USER_DATA_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        UserDataResponse expected = mock(UserDataResponse.class);
        whenNew(UserDataResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.getUserData(identifier, false));
        
        verify(httpClient).post(url, params);
        verifyNew(UserDataResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testSetStatus() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + SET_STATUS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.setStatus(identifier, status);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testSetStatusWithLocation() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        params.put(LOCATION_PARAM, location);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + SET_STATUS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.setStatus(identifier, status, location);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testMap() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + MAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.map(identifier, primaryKey);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testMapWithOverwriteIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + MAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.map(identifier, primaryKey, true);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testMapWithOverwriteIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + MAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.map(identifier, primaryKey, false);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapIdentifier() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(identifier, primaryKey);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapIdentifierWithUnlinkIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(identifier, primaryKey, true);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapIdentifierWithUnlinkIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(identifier, primaryKey, false);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapAllIdentifiers() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(primaryKey);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapAllIdentifiersWithUnlinkIsTrue() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(primaryKey, true);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testUnmapAllIdentifiersWithUnlinkIsFalse() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + UNMAP_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.unmap(primaryKey, false);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testMappings() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + MAPPINGS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        MappingsResponse expected = mock(MappingsResponse.class);
        whenNew(MappingsResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.mappings(primaryKey));
        
        verify(httpClient).post(url, params);
        verifyNew(MappingsResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testAllMappings() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ALL_MAPPINGS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        AllMappingsResponse expected = mock(AllMappingsResponse.class);
        whenNew(AllMappingsResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.allMappings());
        
        verify(httpClient).post(url, params);
        verifyNew(AllMappingsResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testActivityWithObject() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(ACTIVITY_PARAM, activityObject.toJSON());
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ACTIVITY_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.activity(identifier, activityObject);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testActivityWithObjectAndLocation() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(ACTIVITY_PARAM, activityObject.toJSON());
        params.put(LOCATION_PARAM, location);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ACTIVITY_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.activity(identifier, activityObject, location);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testActivityWithString() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(ACTIVITY_PARAM, activityString);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ACTIVITY_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.activity(identifier, activityString);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testActivityWithStringAndLocation() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(ACTIVITY_PARAM, activityString);
        params.put(LOCATION_PARAM, location);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ACTIVITY_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.activity(identifier, activityString, location);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testAnalytics() throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date start = dateFormatter.parse("08/01/2010");
        Date end = dateFormatter.parse("08/31/2010");
        
        // expected params in api call
        params.put(START_PARAM, dateFormatter.format(start));
        params.put(END_PARAM, dateFormatter.format(end));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + ANALYTICS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        AnalyticsResponse expected = mock(AnalyticsResponse.class);
        whenNew(AnalyticsResponse.class).withArguments(successResponse).thenReturn(expected);
        
        // call service
        assertSame(expected, service.analytics(start, end));
        
        verify(httpClient).post(url, params);
        verifyNew(AnalyticsResponse.class).withArguments(successResponse);
    }
    
    @Test
    public void testSetMultipleAuthProviders() throws Exception {
        // expected params in api call
        providers.add("google");
        providers.add("twitter");
        params.put(PROVIDERS_PARAM, "google,twitter");
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + SET_AUTH_PROVIDERS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.setAuthProviders(providers);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testSetSingleAuthProvider() throws Exception {
        // expected params in api call
        providers.add("google");
        params.put(PROVIDERS_PARAM, "google");
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + SET_AUTH_PROVIDERS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.setAuthProviders(providers);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testSetNoAuthProvider() throws Exception {
        // expected params in api call
        params.put(PROVIDERS_PARAM, "");
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + SET_AUTH_PROVIDERS_METHOD;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        service.setAuthProviders(providers);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testApiCallWithSuccessResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + method;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        // call service
        String jsonResponse = service.apiCall(method, params);
        
        JSONObject rsp = new JSONObject(jsonResponse);
        assertNotNull(rsp);
        assertEquals("ok", rsp.getString("stat"));
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testApiCallWithErrorResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + method;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(errorResponse);
        
        // call service
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithUnexpectedResponseStatus() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + method;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn("{ \"stat\": \"unexpected\" }");
        
        // call service
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithInvalidJson() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + method;
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn("<xml></xml>");
        
        // call service
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithHttpFailure() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        // api url
        url = API_URL + method;
        
        when(httpClient.post(url, params)).thenThrow(new HttpFailureException("Some error"));
        
        // call service
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testConfigSet() {
        service = new EngageServiceImpl(config);
        Config otherConfig = mock(Config.class);
        ConfigHolder.setConfig(otherConfig);
        
        assertNotSame(otherConfig, service.getConfig());
    }
    
    @Test
    public void testNoConfigSet() {
        service = new EngageServiceImpl();
        ConfigHolder.setConfig(config);
        
        assertSame(config, service.getConfig());
        
        Config newConfig = mock(Config.class);
        ConfigHolder.setConfig(newConfig);
        
        assertSame(newConfig, service.getConfig());
    }
}
