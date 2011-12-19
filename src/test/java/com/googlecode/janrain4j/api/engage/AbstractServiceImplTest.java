/* Copyright 2011 Sergey Tsymbler
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

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;
import com.googlecode.janrain4j.http.HttpClient;
import com.googlecode.janrain4j.http.HttpClientFactory;
import com.googlecode.janrain4j.http.HttpFailureException;
import com.googlecode.janrain4j.http.HttpResponse;
import com.googlecode.janrain4j.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.janrain4j.api.engage.AbstractService.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.AbstractService.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.AbstractService.JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EngagePartnerServiceImpl.class, HttpClientFactory.class})
public class AbstractServiceImplTest {

    public static final String API_URL = "https://rpxnow.com/api/v2/";

    protected AbstractService service = null;

    private Config config = null;

    private HttpClient httpClient = null;

    private HttpResponse httpResponse = null;

    private Map<String, String> params = null;

    private String apiKey = "my-api-key";

    private String partnerApiKey = "my-partner-api-key";

    private List<String> setStatusProviderNames = Arrays.asList("my-set-status-provider-name1", "my-set-status-provider-name2");
    private List<String> activityProviderNames = Arrays.asList("my-activity-provider-name1", "my-activity-provider-name2");

    private String method = "some_method";
    private String url = null;

    private String successResponse = "{ \"stat\": \"ok\", \"apiKey\": \"my-api-key\", \"adminUrl\": \"http://admin_url.com\", \"inviteUrl\": \"http://admin_url.com\", \"realm\": \"my-app-domain.rpxnow.com\", \"appId\": \"my-app-id\" }";

    private int errorCode = 99;
    private String errorMessage = "Some error message";
    private String errorResponse = "{ \"err\": { \"msg\": \"" + errorMessage + "\", \"code\": " + errorCode + " }, \"stat\": \"fail\" }";

    @Before
    public void setUp() throws Exception {
        // mock config
        config = mock(Config.class);
        when(config.getApiKey()).thenReturn(apiKey);
        when(config.getPartnerApiKey()).thenReturn(partnerApiKey);
        when(config.getSetStatusProviderNames()).thenReturn(setStatusProviderNames);
        when(config.getActivityProviderNames()).thenReturn(activityProviderNames);

        // mock http client and response
        httpClient = mock(HttpClient.class);
        mockStatic(HttpClientFactory.class);
        when(HttpClientFactory.getHttpClient(config)).thenReturn(httpClient);
        httpResponse = mock(HttpResponse.class);

        // create service to test
        service = new AbstractService(config) {
            @Override
            protected String getBaseUrl() {
                return API_URL;
            }
        };

        // expected params in api call
        params = new HashMap<String, String>();
    }

    @Test
    public void testApiCallWithSuccessResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);

        // api url
        url = API_URL + method;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);

        // call service
        String jsonResponse = service.apiCall(method, params);

        JSONObject rsp = new JSONObject(jsonResponse);
        assertNotNull(rsp);
        assertEquals("ok", rsp.getString("stat"));

        verify(httpClient).post(url, params);
    }

    @Test(expected = ErrorResponeException.class)
    public void testApiCallWithErrorResponse() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);

        // api url
        url = API_URL + method;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(errorResponse);

        // call service
        service.apiCall(method, params);

        verify(httpClient).post(url, params);
    }


    @Test(expected = EngageFailureException.class)
    public void testApiCallWithUnexpectedResponseStatus() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);

        // api url
        url = API_URL + method;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn("{ \"stat\": \"unexpected\" }");

        // call service
        service.apiCall(method, params);

        verify(httpClient).post(url, params);
    }

    @Test(expected = EngageFailureException.class)
    public void testApiCallWithInvalidJson() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);

        // api url
        url = API_URL + method;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn("<xml></xml>");

        // call service
        service.apiCall(method, params);

        verify(httpClient).post(url, params);
    }

    @Test(expected = EngageFailureException.class)
    public void testApiCallWithHttpFailure() throws Exception {
        // expected params in api call
        params.put(FORMAT_PARAM, JSON);
        params.put(API_KEY_PARAM, apiKey);

        // api url
        url = API_URL + method;

        when(httpClient.post(url, params)).thenThrow(new HttpFailureException("Some error"));

        // call service
        service.apiCall(method, params);

        verify(httpClient).post(url, params);
    }

    @Test
    public void testConfigSet() {
        service = new AbstractService(config) {
            @Override
            protected String getBaseUrl() {
                return API_URL;
            }
        };
        Config otherConfig = mock(Config.class);
        ConfigHolder.setConfig(otherConfig);

        assertNotSame(otherConfig, service.getConfig());
    }

    @Test
    public void testNoConfigSet() {
        service = new AbstractService() {
            @Override
            protected String getBaseUrl() {
                return API_URL;
            }
        };
        ConfigHolder.setConfig(config);

        assertSame(config, service.getConfig());

        Config newConfig = mock(Config.class);
        ConfigHolder.setConfig(newConfig);

        assertSame(newConfig, service.getConfig());
    }
}
