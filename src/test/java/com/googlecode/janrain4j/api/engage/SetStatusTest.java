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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.IDENTIFIER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.LOCATION_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.SET_STATUS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.STATUS_PARAM;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class SetStatusTest extends EngageServiceImplTestCase {

    private String status = "Hello World!";
    private String location = "37.4220 -122.0843";
    
    @Test
    public void testSetStatus() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(SET_STATUS_METHOD, params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall(SET_STATUS_METHOD, params);
    }
    
    @Test
    public void testSetStatusWithLocation() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        params.put(LOCATION_PARAM, location);
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(SET_STATUS_METHOD, params);
        
        service.setStatus(identifier, status, location);
        
        verify(service).apiCall(SET_STATUS_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testSetStatusThrowsEngageFailureException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        
        doThrow(engageFailureException()).when(service).apiCall(SET_STATUS_METHOD, params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall(SET_STATUS_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testSetStatusThrowsErrorResponeException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        
        doThrow(errorResponeException()).when(service).apiCall(SET_STATUS_METHOD, params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall(SET_STATUS_METHOD, params);
    }
}
