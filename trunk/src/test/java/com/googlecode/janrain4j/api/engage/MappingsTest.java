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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.MAPPINGS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class MappingsTest extends EngageServiceImplTestCase {

    private String url = API_URL + MAPPINGS_METHOD;
    
    @Test
    public void testMultipleMappings() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"http:\\/\\/brianellin.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(2, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        assertTrue(mappings.contains("http://brianellin.com/"));
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testSingleMapping() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(1, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        
        verify(httpClient).post(url, params);
    }
    
    @Test
    public void testNoMappings() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "  ]\n" +
            "}";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(0, mappings.size());
        
        verify(httpClient).post(url, params);
    }
}
