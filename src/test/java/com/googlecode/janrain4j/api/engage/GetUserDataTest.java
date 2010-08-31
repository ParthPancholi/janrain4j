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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.EXTENDED_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.GET_USER_DATA_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.IDENTIFIER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class GetUserDataTest extends EngageServiceImplTestCase {

    private String url = apiUrl + "/" + GET_USER_DATA_METHOD;
    
    @Test
    public void testGetUserData() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(userDataResponse);
        
        UserData userData = service.getUserData(identifier);
        assertNotNull(userData);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testGetUserDataWithExtendedIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(true));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(userDataResponse);
        
        UserData userData = service.getUserData(identifier, true);
        assertNotNull(userData);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testGetUserDataWithExtendedIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(userDataResponse);
        
        UserData userData = service.getUserData(identifier, false);
        assertNotNull(userData);
        
        verify(httpClient).post(url, params);
    }
}
