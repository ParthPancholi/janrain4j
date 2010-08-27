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
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.END_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.START_PARAM;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class AnalyticsTest extends EngageServiceImplTestCase {

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
        
        String response =
            "{\n" +
            "  \"url\": \"http:\\/\\/rpxnow.com\\/export?access_token=19e936b707e7862269c...&end=02\\/10\\/2010&api=true\",\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(ANALYTICS_METHOD, params);
        
        URL url = service.analytics(start, end);
        
        assertEquals("http://rpxnow.com/export?access_token=19e936b707e7862269c...&end=02/10/2010&api=true", url.toString());
        
        verify(service).apiCall(ANALYTICS_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testAnalyticsThrowsEngageFailureException() {
        // expected params in api call
        params.put(START_PARAM, dateFormatter.format(start));
        params.put(END_PARAM, dateFormatter.format(end));
        
        doThrow(engageFailureException()).when(service).apiCall(ANALYTICS_METHOD, params);
        
        service.analytics(start, end);
        
        verify(service).apiCall(ANALYTICS_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testAnalyticsThrowsErrorResponeException() {
        // expected params in api call
        params.put(START_PARAM, dateFormatter.format(start));
        params.put(END_PARAM, dateFormatter.format(end));
        
        doThrow(errorResponeException()).when(service).apiCall(ANALYTICS_METHOD, params);
        
        service.analytics(start, end);
        
        verify(service).apiCall(ANALYTICS_METHOD, params);
    }
}
