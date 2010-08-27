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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ALL_MAPPINGS_METHOD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class AllMappingsTest extends EngageServiceImplTestCase {

    @Test
    public void testMultipleMappings() throws Exception {
        String response =
            "{\n" +
            "  \"mappings\": {\n" +
            "    \"1\": [\n" +
            "      \"http:\\/\\/cygnus.myopenid.com\\/\"\n" +
            "    ],\n" +
            "    \"2\": [\n" +
            "      \"http:\\/\\/brianellin.com\\/\",\n" +
            "      \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(ALL_MAPPINGS_METHOD, params);
        
        Map<String, List<String>> allMappings = service.allMappings();
        
        assertEquals(2, allMappings.size());
        
        assertTrue(allMappings.containsKey("1"));
        assertEquals(1, allMappings.get("1").size());
        assertTrue(allMappings.get("1").contains("http://cygnus.myopenid.com/"));
        
        assertTrue(allMappings.containsKey("2"));
        assertEquals(2, allMappings.get("2").size());
        assertTrue(allMappings.get("2").contains("http://brianellin.com/"));
        assertTrue(allMappings.get("2").contains("http://brian.myopenid.com/"));
        
        verify(service).apiCall(ALL_MAPPINGS_METHOD, params);
    }
    
    @Test
    public void testSingleMappings() throws Exception {
        String response =
            "{\n" +
            "  \"mappings\": {\n" +
            "    \"1\": [\n" +
            "      \"http:\\/\\/cygnus.myopenid.com\\/\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(ALL_MAPPINGS_METHOD, params);
        
        Map<String, List<String>> allMappings = service.allMappings();
        
        assertEquals(1, allMappings.size());
        
        assertTrue(allMappings.containsKey("1"));
        assertEquals(1, allMappings.get("1").size());
        assertTrue(allMappings.get("1").contains("http://cygnus.myopenid.com/"));
        
        verify(service).apiCall(ALL_MAPPINGS_METHOD, params);
    }
    
    @Test
    public void testNoMappings() throws Exception {
        String response =
            "{\n" +
            "  \"mappings\": {\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(ALL_MAPPINGS_METHOD, params);
        
        Map<String, List<String>> allMappings = service.allMappings();
        
        assertEquals(0, allMappings.size());
        
        verify(service).apiCall(ALL_MAPPINGS_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testAllMappingsThrowsEngageFailureException() {
        doThrow(engageFailureException()).when(service).apiCall(ALL_MAPPINGS_METHOD, params);
        
        service.allMappings();
        
        verify(service).apiCall(ALL_MAPPINGS_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testAllMappingsThrowsErrorResponeException() {
        doThrow(errorResponeException()).when(service).apiCall(ALL_MAPPINGS_METHOD, params);
        
        service.allMappings();
        
        verify(service).apiCall(ALL_MAPPINGS_METHOD, params);
    }
}
