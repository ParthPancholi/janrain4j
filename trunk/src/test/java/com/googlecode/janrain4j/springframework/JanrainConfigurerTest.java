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
package com.googlecode.janrain4j.springframework;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;

public class JanrainConfigurerTest {

    private JanrainConfigurer configurer = null;
    
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
    
    @Before
    public void setUp() {
        configurer = new JanrainConfigurer();
        ConfigHolder.setConfig(null);
    }
    
    @Test
    public void testJanrainConfigurer() throws Exception {
        configurer.setApiKey(apiKey);
        configurer.setApplicationID(applicationID);
        configurer.setApplicationDomain(applicationDomain);
        configurer.setTokenUrl(tokenUrl);
        configurer.setLanguagePreference(languagePreference);
        configurer.setProxyHost(proxyHost);
        configurer.setProxyPort(proxyPort);
        configurer.setProxyUsername(proxyUsername);
        configurer.setProxyPassword(proxyPassword);
        configurer.setConnectTimeout(connectTimeout);
        configurer.setReadTimeout(readTimeout);
        configurer.afterPropertiesSet();
        
        Config config = ConfigHolder.getConfig();
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
    }
}
