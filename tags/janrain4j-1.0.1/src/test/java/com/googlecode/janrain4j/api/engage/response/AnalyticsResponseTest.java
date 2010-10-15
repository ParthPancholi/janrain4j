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

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class AnalyticsResponseTest {

    private String json = null;
    private AnalyticsResponse response = null;
    
    @Test
    public void testAnalyticsResponse() throws Exception {
        
        json =
            "{\n" +
            "  \"url\": \"http:\\/\\/rpxnow.com\\/export?access_token=19e936b707e7862269c...&end=02\\/10\\/2010&api=true\",\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new AnalyticsResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        assertEquals("http://rpxnow.com/export?access_token=19e936b707e7862269c...&end=02/10/2010&api=true", response.getUrl());
    }
}
