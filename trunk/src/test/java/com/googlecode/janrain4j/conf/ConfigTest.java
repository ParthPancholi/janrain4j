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
package com.googlecode.janrain4j.conf;

import static com.googlecode.janrain4j.conf.Config.DEFAULT_SET_STATUS_PROVIDER_NAMES;
import static com.googlecode.janrain4j.conf.Config.DEFAULT_ACTIVITY_PROVIDER_NAMES;
import static com.googlecode.janrain4j.conf.Config.Builder.build;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ConfigTest {

    private Config config = null;
    
    private String apiKey = "my-api-key";
    private String applicationID = "my-application-id";
    private String applicationDomain = "my-application-domain";
    private String tokenUrl = "http://my-token-url.com";
    private String languagePreference = "nl";
    private String proxyHost = "http://my-proxy.com";
    private int proxyPort = 8080;
    private String proxyUsername = "my-username";
    private String proxyPassword = "my-password";
    private int connectTimeout = 60000;
    private int readTimeout = 120000;
    private List<String> setStatusProviderNames = Arrays.asList("my-set-status-provider-name1", "my-set-status-provider-name2");
    private List<String> activityProviderNames = Arrays.asList("my-activity-provider-name1", "my-activity-provider-name2");
    
    @Test
    public void testBuilderDefaultValues() {
        config = build();
        
        assertNull(config.getApiKey());
        assertNull(config.getApplicationID());
        assertNull(config.getApplicationDomain());
        assertNull(config.getTokenUrl());
        assertNull(config.getLanguagePreference());
        assertNull(config.getProxyHost());
        assertEquals(-1, config.getProxyPort());
        assertNull(config.getProxyUsername());
        assertNull(config.getProxyPassword());
        assertEquals(-1, config.getConnectTimeout());
        assertEquals(-1, config.getReadTimeout());
        assertEquals(DEFAULT_SET_STATUS_PROVIDER_NAMES, config.getSetStatusProviderNames());
        assertEquals(DEFAULT_ACTIVITY_PROVIDER_NAMES, config.getActivityProviderNames());
    }
    
    @Test
    public void testMutators() {
        config = build()
            .apiKey(apiKey)
            .applicationID(applicationID)
            .applicationDomain(applicationDomain)
            .tokenUrl(tokenUrl)
            .languagePreference(languagePreference)
            .proxyHost(proxyHost)
            .proxyPort(proxyPort)
            .proxyUsername(proxyUsername)
            .proxyPassword(proxyPassword)
            .connectTimeout(connectTimeout)
            .readTimeout(readTimeout)
            .setStatusProviderNames(setStatusProviderNames)
            .activityProviderNames(activityProviderNames);
        
        assertEquals(apiKey, config.getApiKey());
        assertEquals(applicationID, config.getApplicationID());
        assertEquals(applicationDomain, config.getApplicationDomain());
        assertEquals(tokenUrl, config.getTokenUrl());
        assertEquals(languagePreference, config.getLanguagePreference());
        assertEquals(proxyHost, config.getProxyHost());
        assertEquals(proxyPort, config.getProxyPort());
        assertEquals(proxyUsername, config.getProxyUsername());
        assertEquals(proxyPassword, config.getProxyPassword());
        assertEquals(connectTimeout, config.getConnectTimeout());
        assertEquals(readTimeout, config.getReadTimeout());
        assertEquals(setStatusProviderNames, config.getSetStatusProviderNames());
        assertEquals(activityProviderNames, config.getActivityProviderNames());
    }    
}
