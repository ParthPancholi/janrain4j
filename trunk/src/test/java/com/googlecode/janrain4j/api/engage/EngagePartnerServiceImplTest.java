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

import com.googlecode.janrain4j.api.engage.response.ApplicationResponse;
import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;
import com.googlecode.janrain4j.http.HttpClient;
import com.googlecode.janrain4j.http.HttpClientFactory;
import com.googlecode.janrain4j.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.API_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.API_PARTNER_URI;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.CREATE_METHOD;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.DELETE_METHOD;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.ADD_DOMAIN_METHOD;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.DOMAIN_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.PROVIDER_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.PERMISSIONS_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.EMAIL_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.FORMAT_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.JSON;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.NAME_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.PARTNER_KEY_PARAM;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.SET_PROVIDER_PERMISSIONS_METHOD;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.SET_PROVIDER_PROPERTIES_METHOD;
import static com.googlecode.janrain4j.api.engage.EngagePartnerServiceImpl.VERIFY_APP_DOMAIN_METHOD;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EngagePartnerServiceImpl.class, HttpClientFactory.class})
public class EngagePartnerServiceImplTest {

    protected EngagePartnerServiceImpl service = null;

    private Config config = null;

    private HttpClient httpClient = null;

    private HttpResponse httpResponse = null;

    private Map<String, String> params = null;

    private String apiKey = "my-api-key";

    private String partnerApiKey = "my-partner-api-key";

    private String appId = "my-app-id";
    private String appName = "my-app-name";
    private String appDomain = "my-app-domain.rpxnow.com";
    private String appAdminEmail = "my-app-admin@janrain.com";
    private String domain = "my-domain.com";
    private String providerName = "facebook";
    private String fbAppId = "FACEBOOK_APP_ID";
    private String fbSecret = "FACEBOOK_SECRET";

    private List<String> setStatusProviderNames = Arrays.asList("my-set-status-provider-name1", "my-set-status-provider-name2");
    private List<String> activityProviderNames = Arrays.asList("my-activity-provider-name1", "my-activity-provider-name2");
    private String url = null;
    private String successResponse = "{ \"stat\": \"ok\", \"apiKey\": \"my-api-key\", \"adminUrl\": \"http://admin_url.com\", \"inviteUrl\": \"http://admin_url.com\", \"realm\": \"my-app-domain.rpxnow.com\", \"appId\": \"" + appId + "\" }";
    private String okResponse = "{ \"stat\": \"ok\" }";

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
        service = new EngagePartnerServiceImpl(config);

        // expected params in api call
        params = new HashMap<String, String>();
    }

    @Test
    public void testCreateApplication() throws Exception {
        params.put(NAME_PARAM, appName);
        params.put(DOMAIN_PARAM, appDomain);
        params.put(EMAIL_PARAM, appAdminEmail);
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + CREATE_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(successResponse);

        ApplicationResponse expected = mock(ApplicationResponse.class);
        whenNew(ApplicationResponse.class).withArguments(successResponse).thenReturn(expected);

        // call service
        assertSame(expected, service.createApplication(appName, appDomain, appAdminEmail));

        verify(httpClient).post(url, params);
        verifyNew(ApplicationResponse.class).withArguments(successResponse);
    }

    @Test
    public void testDeleteApplication() throws Exception {
        params.put(API_KEY_PARAM, apiKey);
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + DELETE_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(okResponse);

        // call service
        service.deleteApplication(apiKey);

        verify(httpClient).post(url, params);
    }

    @Test
    public void test() throws Exception {
        // build provider properties
        Map<String, String> appDomainProperties = new HashMap<String, String>();
        appDomainProperties.put("code", "<meta name=\"google-site-verification\" content=\"sfAWwevEsW35fUy7xSom4YEb49HDUn_ItcE1DW-01x4\"/>");

        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.putAll(appDomainProperties);
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + VERIFY_APP_DOMAIN_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(okResponse);

        // call service
        service.verifyApplicationDomain(apiKey, providerName, appDomainProperties);

        verify(httpClient).post(url, params);
    }

    @Test
    public void testAddDomain() throws Exception {
        params.put(API_KEY_PARAM, apiKey);
        params.put(DOMAIN_PARAM, domain);
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + ADD_DOMAIN_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(okResponse);

        // call service
        service.addDomain(apiKey,domain);

        verify(httpClient).post(url, params);
    }

    @Test
    public void testSetProviderProperties() throws Exception {
        // build provider properties
        Map<String, String> providerProperties = new HashMap<String, String>();
        providerProperties.put("fbAppID", fbAppId);
        providerProperties.put("fbSecret", fbSecret);

        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.putAll(providerProperties);
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + SET_PROVIDER_PROPERTIES_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(okResponse);

        // call service
        service.setProviderProperties(apiKey, providerName, providerProperties);

        verify(httpClient).post(url, params);
    }

    @Test
    public void testSetProviderPermissions() throws Exception {
        // build provider permissions
        Collection<String> providerPermissions = new ArrayList<String>();
        for (int i = 1; i < 5; i++) {
            providerPermissions.add("PROVIDER_PERMISSION_" + i);
        }

        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.put(PERMISSIONS_PARAM, StringUtils.collectionToCommaDelimitedString(providerPermissions));
        params.put(PARTNER_KEY_PARAM, partnerApiKey);
        params.put(FORMAT_PARAM, JSON);

        // api url
        url = API_PARTNER_URI + SET_PROVIDER_PERMISSIONS_METHOD;

        when(httpClient.post(url, params)).thenReturn(httpResponse);
        when(httpResponse.getContent()).thenReturn(okResponse);

        // call service
        service.setProviderPermissions(apiKey, providerName, providerPermissions);

        verify(httpClient).post(url, params);
    }

    @Test
    public void testConfigSet() {
        service = new EngagePartnerServiceImpl(config);
        Config otherConfig = mock(Config.class);
        ConfigHolder.setConfig(otherConfig);

        assertNotSame(otherConfig, service.getConfig());
    }

    @Test
    public void testNoConfigSet() {
        service = new EngagePartnerServiceImpl();
        ConfigHolder.setConfig(config);

        assertSame(config, service.getConfig());

        Config newConfig = mock(Config.class);
        ConfigHolder.setConfig(newConfig);

        assertSame(newConfig, service.getConfig());
    }
}
