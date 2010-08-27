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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PROVIDERS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.SET_AUTH_PROVIDERS_METHOD;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class SetAuthProvidersTest extends EngageServiceImplTestCase {

    private List<String> providers = new ArrayList<String>();
    
    @Test
    public void testSetMultipleAuthProviders() throws Exception {
        // expected params in api call
        providers.add("google");
        providers.add("twitter");
        params.put(PROVIDERS_PARAM, "google,twitter");
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
        
        service.setAuthProviders(providers);
        
        verify(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
    }
    
    @Test
    public void testSetSingleAuthProvider() throws Exception {
        // expected params in api call
        providers.add("google");
        params.put(PROVIDERS_PARAM, "google");
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
        
        service.setAuthProviders(providers);
        
        verify(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
    }
    
    @Test
    public void testSetNoAuthProvider() throws Exception {
        // expected params in api call
        params.put(PROVIDERS_PARAM, "");
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
        
        service.setAuthProviders(providers);
        
        verify(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testSetAuthProvidersThrowsEngageFailureException() {
        // expected params in api call
        providers.add("google");
        params.put(PROVIDERS_PARAM, "google");
        
        doThrow(engageFailureException()).when(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
        
        service.setAuthProviders(providers);
        
        verify(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testSetAuthProvidersThrowsErrorResponeException() {
        // expected params in api call
        providers.add("google");
        params.put(PROVIDERS_PARAM, "google");
        
        doThrow(errorResponeException()).when(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
        
        service.setAuthProviders(providers);
        
        verify(service).apiCall(SET_AUTH_PROVIDERS_METHOD, params);
    }
}
