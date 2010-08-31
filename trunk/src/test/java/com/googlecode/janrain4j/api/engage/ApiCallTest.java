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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.googlecode.janrain4j.http.HttpFailureException;
import com.googlecode.janrain4j.json.JSONObject;

public class ApiCallTest extends EngageServiceImplTestCase {

    private String method = "some_method";
    private String url = apiUrl + "/" + method;
    
    @Test
    public void testApiCallWithSuccessResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        JSONObject rsp = service.apiCall(method, params);
        assertNotNull(rsp);
        assertEquals("ok", rsp.getString("stat"));
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testApiCallWithErrorResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(errorResponse);
        
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithUnexpectedResponseStatus() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response = "{ \"stat\": \"unexpected\" }";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithInvalidJson() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response = "<xml></xml>";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testApiCallWithHttpFailure() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenThrow(new HttpFailureException("Some error"));
        
        service.apiCall(method, params);
        
        verify(httpClient).post(url, params);
    }
}
