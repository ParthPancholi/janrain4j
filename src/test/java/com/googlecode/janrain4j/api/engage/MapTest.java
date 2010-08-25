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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.MAP_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.OVERWRITE_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class MapTest extends EngageServiceImplTestCase {

    @Test
    public void testMap() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        
        doReturn(buildElement(successResponse)).when(service).apiCall(MAP_METHOD, params);
        
        service.map(identifier, primaryKey);
        
        verify(service).apiCall(MAP_METHOD, params);
    }
    
    @Test
    public void testMapWithOverwriteIsTrue() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        
        doReturn(buildElement(successResponse)).when(service).apiCall(MAP_METHOD, params);
        
        service.map(identifier, primaryKey, true);
        
        verify(service).apiCall(MAP_METHOD, params);
    }
    
    @Test
    public void testMapWithOverwriteIsFalse() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(false));
        
        doReturn(buildElement(successResponse)).when(service).apiCall(MAP_METHOD, params);
        
        service.map(identifier, primaryKey, false);
        
        verify(service).apiCall(MAP_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testMapThrowsEngageFailureException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        
        doThrow(engageFailureException()).when(service).apiCall(MAP_METHOD, params);
        
        service.map(identifier, primaryKey);
        
        verify(service).apiCall(MAP_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testMapThrowsErrorResponeException() {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(true));
        
        doThrow(errorResponeException()).when(service).apiCall(MAP_METHOD, params);
        
        service.map(identifier, primaryKey);
        
        verify(service).apiCall(MAP_METHOD, params);
    }
}
