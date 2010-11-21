/* Copyright 2010 Marcel Overdijk
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class MappingsResponseTest {

    private String json = null;
    private MappingsResponse response = null;
    
    @Test
    public void testMultipleMappings() throws Exception {
        
        json =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"http:\\/\\/brianellin.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(2, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
        assertTrue(mappings.contains("http://brianellin.com/"));
    }
    
    @Test
    public void testSingleMapping() throws Exception {
        
        json =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(1, mappings.size());
        assertTrue(mappings.contains("http://brian.myopenid.com/"));
    }
    
    @Test
    public void testNoMappings() throws Exception {
        
        json =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        List<String> mappings = response.getMappings();
        
        assertEquals(0, mappings.size());
    }
    
    @Test
    public void testSerializable() throws Exception {
        
        json =
            "{\n" +
            "  \"stat\": \"ok\",\n" +
            "  \"identifiers\": [\n" +
            "    \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"http:\\/\\/brianellin.com\\/\"\n" +
            "  ]\n" +
            "}";
        
        response = new MappingsResponse(json);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(response);
        oos.close();
        
        assertTrue(out.toByteArray().length > 0);
        assertNotNull(response.getResponseAsJSONObject());
    }
}
