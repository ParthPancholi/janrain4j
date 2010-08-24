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

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class EngageServiceImplTest {

    private EngageServiceImpl service = null;
    private EngageServiceConfig config = null;
    private String url = null;
    private Map<String, String> parameters = null;
    
    private String apiKey = "my-api-key";
    private String identier = "my-identifier";
    private String status = "Hello World!";
    private String successResponse = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok'/>";
    
    @Before
    public void setUp() {
        config = mock(EngageServiceConfig.class);
        service = new EngageServiceImpl(config);
        parameters = new HashMap<String, String>();
    }
    
    @Test
    public void testSetStatus() {
        url = null; // TODO use real url + parameters instead of any*
        // TODO parameters. create static keys like API_KEY_PARAMETER = "" 
        // when(httpResponse.getContentAsDocument()).thenReturn(buildTestDocument(successResponse));
        // when(httpClient.post(anyString(), anyMap())).thenReturn(httpResponse);
        service.setStatus(identier, status);
    }
}
