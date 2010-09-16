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
package com.googlecode.janrain4j.api.engage.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class AllMappingsResponseTest {

    private String jsonResponse = null;
    private AllMappingsResponse response = null;
    
    @Test
    public void testMultipleMappings() throws Exception {
        
        jsonResponse =
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
        
        response = new AllMappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        Map<String, List<String>> allMappings = response.getAllMappings();
        
        assertEquals(2, allMappings.size());
        
        assertTrue(allMappings.containsKey("1"));
        assertEquals(1, allMappings.get("1").size());
        assertTrue(allMappings.get("1").contains("http://cygnus.myopenid.com/"));
        
        assertTrue(allMappings.containsKey("2"));
        assertEquals(2, allMappings.get("2").size());
        assertTrue(allMappings.get("2").contains("http://brianellin.com/"));
        assertTrue(allMappings.get("2").contains("http://brian.myopenid.com/"));
    }
    
    @Test
    public void testSingleMappings() throws Exception {
        
        jsonResponse =
            "{\n" +
            "  \"mappings\": {\n" +
            "    \"1\": [\n" +
            "      \"http:\\/\\/cygnus.myopenid.com\\/\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new AllMappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        Map<String, List<String>> allMappings = response.getAllMappings();
        
        assertEquals(1, allMappings.size());
        
        assertTrue(allMappings.containsKey("1"));
        assertEquals(1, allMappings.get("1").size());
        assertTrue(allMappings.get("1").contains("http://cygnus.myopenid.com/"));
    }
    
    @Test
    public void testNoMappings() throws Exception {
        
        jsonResponse =
            "{\n" +
            "  \"mappings\": {\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new AllMappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        Map<String, List<String>> allMappings = response.getAllMappings();
        
        assertEquals(0, allMappings.size());
    }
}