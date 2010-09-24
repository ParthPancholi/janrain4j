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

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class MappingsResponseTest {

    private String jsonResponse = null;
    private MappingsResponse response = null;
    
    @Test
    public void testMultipleMappings() throws Exception {
        
        jsonResponse =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"http:\\/\\/brianellin.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(2, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        assertTrue(mappings.contains("http://brianellin.com/"));
    }
    
    @Test
    public void testSingleMapping() throws Exception {
        
        jsonResponse =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(1, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
    }
    
    @Test
    public void testNoMappings() throws Exception {
        
        jsonResponse =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(jsonResponse);
        
        assertEquals(jsonResponse, response.getResponseAsJSON());
        assertEquals(new JSONObject(jsonResponse).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(0, mappings.size());
    }
}