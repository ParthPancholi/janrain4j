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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.http.HttpClient;
import com.googlecode.janrain4j.http.HttpClientFactory;
import com.googlecode.janrain4j.http.HttpResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpClientFactory.class)
public class EngageServiceImplTestCase {

    protected EngageServiceImpl service = null;
    
    protected Config config = null;
    
    protected HttpClient httpClient = null;
    
    protected HttpResponse httpResponse = null;
    
    protected Map<String, String> params = null;
    
    protected String apiKey = "my-api-key";
    protected String identifier = "my-identifier";
    protected String primaryKey = "my-primary-key";
    
    protected String successResponse = "{ \"stat\": \"ok\" }";
    
    protected int errorCode = 99;
    protected String errorMessage = "Some error message";
    protected String errorResponse = "{ \"err\": { \"msg\": \"" + errorMessage + "\", \"code\": " + errorCode + " }, \"stat\": \"fail\" }";
    
    protected String userDataResponse =
        "{" +
        "  \"profile\": {\n" +
        "    \"displayName\": \"brian\",\n" +
        "    \"preferredUsername\": \"brian\",\n" +
        "    \"url\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
        "    \"providerName\": \"Other\",\n" +
        "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
        "  },\n" +
        "  \"stat\": \"ok\"\n" +
        "}";
    
    @Before
    public void setUp() throws Exception {
        config = mock(Config.class);
        when(config.getApiKey()).thenReturn(apiKey);
        
        httpClient = mock(HttpClient.class);
        mockStatic(HttpClientFactory.class);
        when(HttpClientFactory.getHttpClient(config)).thenReturn(httpClient);
        
        httpResponse = mock(HttpResponse.class);
        
        service = new EngageServiceImpl(config);
        
        params = new HashMap<String, String>();
    }
    
    protected EngageFailureException engageFailureException() {
        return engageFailureException("Some error");
    }
    
    protected EngageFailureException engageFailureException(String message) {
        return new EngageFailureException(message);
    }
    
    protected ErrorResponeException errorResponeException() {
        return errorResponeException(errorCode, errorMessage);
    }
    
    protected ErrorResponeException errorResponeException(int code, String message) {
        return new ErrorResponeException(code, message);
    }
}
