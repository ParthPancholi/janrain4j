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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.ANALYTICS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.API_URL;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.END_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.JSON;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.START_PARAM;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AnalyticsTest extends EngageServiceImplTestCase {

    private String url = API_URL + ANALYTICS_METHOD;
    
    private SimpleDateFormat dateFormatter = null;
    
    private Date start = null; 
    private Date end = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        start = dateFormatter.parse("08/01/2010");
        end = dateFormatter.parse("08/31/2010");
    }
    
    @Test
    public void testAnalytics() throws Exception {
        // expected params in api call
        params.put(START_PARAM, dateFormatter.format(start));
        params.put(END_PARAM, dateFormatter.format(end));
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);
        
        String response =
            "{\n" +
            "  \"url\": \"http:\\/\\/rpxnow.com\\/export?access_token=19e936b707e7862269c...&end=02\\/10\\/2010&api=true\",\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(response);
        
        URL theUrl = service.analytics(start, end);
        
        assertEquals("http://rpxnow.com/export?access_token=19e936b707e7862269c...&end=02/10/2010&api=true", theUrl.toString());
        
        verify(httpClient).post(url, params);
    }
}
