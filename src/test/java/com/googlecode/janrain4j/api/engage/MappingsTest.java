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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.MAPPINGS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.PRIMARY_KEY_PARAM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class MappingsTest extends EngageServiceImplTestCase {

    @Test
    public void testMultiplMappings() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"http:\\/\\/brianellin.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(MAPPINGS_METHOD, params);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(2, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        assertTrue(mappings.contains("http://brianellin.com/"));
        
        verify(service).apiCall(MAPPINGS_METHOD, params);
    }
    
    @Test
    public void testSingleMapping() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(MAPPINGS_METHOD, params);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(1, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        
        verify(service).apiCall(MAPPINGS_METHOD, params);
    }
    
    @Test
    public void testNoMappings() throws Exception {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        
        String response =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "  ]\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(MAPPINGS_METHOD, params);
        
        List<String> mappings = service.mappings(primaryKey);
        
        assertEquals(0, mappings.size());
        
        verify(service).apiCall(MAPPINGS_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testSetStatusThrowsEngageFailureException() {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        
        doThrow(engageFailureException()).when(service).apiCall(MAPPINGS_METHOD, params);
        
        service.mappings(primaryKey);
        
        verify(service).apiCall(MAPPINGS_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testSetStatusThrowsErrorResponeException() {
        // expected params in api call
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        
        doThrow(errorResponeException()).when(service).apiCall(MAPPINGS_METHOD, params);
        
        service.mappings(primaryKey);
        
        verify(service).apiCall(MAPPINGS_METHOD, params);
    }
}
