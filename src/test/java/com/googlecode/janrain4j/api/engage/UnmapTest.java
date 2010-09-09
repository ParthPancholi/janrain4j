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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_URL;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.IDENTIFIER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNLINK_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.UNMAP_METHOD;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class UnmapTest extends EngageServiceImplTestCase {

    private String url = API_URL + UNMAP_METHOD;
    
    @Test
    public void testUnmapIdentifier() throws Exception {
        // expected params in api call
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(UNLINK_PARAM, Boolean.toString(false));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
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
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);
        
        service.unmap(primaryKey, false);
        
        verify(httpClient).post(url, params);
    }
}