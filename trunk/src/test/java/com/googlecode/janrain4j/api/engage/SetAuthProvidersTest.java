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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_URL;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PROVIDERS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.SET_AUTH_PROVIDERS_METHOD;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SetAuthProvidersTest extends EngageServiceImplTestCase {

    private String url = API_URL + SET_AUTH_PROVIDERS_METHOD;
    
    private List<String> providers = new ArrayList<String>();
    
    @Test
    public void testSetMultipleAuthProviders() throws Exception {
        // expected params in api call
        providers.add("google");
        providers.add("twitter");
        params.put(PROVIDERS_PARAM, "google,twitter");
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        service.setAuthProviders(providers);
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testSetNoAuthProvider() throws Exception {
        // expected params in api call
        params.put(PROVIDERS_PARAM, "");
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        service.setAuthProviders(providers);
        
        verify(httpClient).post(url, params);
    }
}
