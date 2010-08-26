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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ALL_IDENTIFIERS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.IDENTIFIER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNLINK_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNMAP_METHOD;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class UnmapTest extends EngageServiceImplTestCase {

    @Test
    public void testUnmapIdentifier() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(identifier, primaryKey);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test
    public void testUnmapIdentifierWithUnlinkIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(true));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(identifier, primaryKey, true);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test
    public void testUnmapIdentifierWithUnlinkIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(identifier, primaryKey, false);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test
    public void testUnmapAllIdentifiers() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(primaryKey);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test
    public void testUnmapAllIdentifiersWithUnlinkIsTrue() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(true));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(primaryKey, true);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test
    public void testUnmapAllIdentifiersWithUnlinkIsFalse() throws Exception {
        // expected params in api call
        params.put(ALL_IDENTIFIERS_PARAM, Boolean.toString(true));
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doReturn(new JSONObject(successResponse)).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(primaryKey, false);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testUnmapThrowsEngageFailureException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doThrow(engageFailureException()).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(identifier, primaryKey);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testUnmapThrowsErrorResponeException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        
        doThrow(errorResponeException()).when(service).apiCall(UNMAP_METHOD, params);
        
        service.unmap(identifier, primaryKey);
        
        verify(service).apiCall(UNMAP_METHOD, params);
    }
}
