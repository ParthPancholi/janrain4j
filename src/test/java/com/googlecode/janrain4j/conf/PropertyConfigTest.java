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

import static com.googlecode.janrain4j.conf.PropertyConfig.API_KEY_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.APPLICATION_DOMAIN_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.APPLICATION_ID_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.CONNECT_TIMEOUT_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.LANGUAGE_PREFERENCE_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.PROXY_HOST_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.PROXY_PASSWORD_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.PROXY_PORT_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.PROXY_USERNAME_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.READ_TIMEOUT_KEY;
import static com.googlecode.janrain4j.conf.PropertyConfig.TOKEN_URL_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class PropertyConfigTest {

    private PropertyConfig config = null;
    
    private String systemPropertyApiKey = "my-system-property-api-key";
    private String systemPropertyApplicationID = "my-system-property-application-id";
    private String systemPropertyApplicationDomain = "http://my-system-property-application-domain.com";
    private String systemPropertyTokenUrl = "http://my-system-property-token-url.com";
    private String systemPropertyLanguagePreference = "my-system-property-language-preference";
    private String systemPropertyProxyHost = "http://my-system-property-proxy-host.com";
    private int systemPropertyProxyPort = 8080;
    private String systemPropertyProxyUsername = "my-system-property-proxy-username";
    private String systemPropertyProxyPassword = "my-system-property-proxy-password";
    private int systemPropertyConnectTimeout = 30000;
    private int systemPropertyReadTimeout = 60000;
    
    @Before
    public void setUp() {
        System.clearProperty(API_KEY_KEY);
        System.clearProperty(APPLICATION_ID_KEY);
        System.clearProperty(APPLICATION_DOMAIN_KEY);
        System.clearProperty(TOKEN_URL_KEY);
        System.clearProperty(LANGUAGE_PREFERENCE_KEY);
        System.clearProperty(PROXY_HOST_KEY);
        System.clearProperty(PROXY_PORT_KEY);
        System.clearProperty(PROXY_USERNAME_KEY);
        System.clearProperty(PROXY_PASSWORD_KEY);
        System.clearProperty(CONNECT_TIMEOUT_KEY);
        System.clearProperty(READ_TIMEOUT_KEY);
    }
    
    @Test
    public void testPropertyConfig() {
        config = new PropertyConfig("janrain4j.properties");
        
        assertEquals("my-config-api-key", config.getApiKey());
        assertEquals("my-config-application-id", config.getApplicationID());
        assertEquals("http://my-config-application-domain.com", config.getApplicationDomain());
        assertEquals("http://my-config-token-url.com", config.getTokenUrl());
        assertEquals("my-config-language-preference", config.getLanguagePreference());
        assertEquals("http://my-config-proxy-host.com", config.getProxyHost());
        assertEquals(8080, config.getProxyPort());
        assertEquals("my-config-proxy-username", config.getProxyUsername());
        assertEquals("my-config-proxy-password", config.getProxyPassword());
        assertEquals(30000, config.getConnectTimeout());
        assertEquals(60000, config.getReadTimeout());
    }
    
    @Test
    public void testPropertyConfigWithSystemProperties() {
        System.setProperty(API_KEY_KEY, systemPropertyApiKey);
        System.setProperty(APPLICATION_ID_KEY, systemPropertyApplicationID);
        System.setProperty(APPLICATION_DOMAIN_KEY, systemPropertyApplicationDomain);
        System.setProperty(TOKEN_URL_KEY, systemPropertyTokenUrl);
        System.setProperty(LANGUAGE_PREFERENCE_KEY, systemPropertyLanguagePreference);
        System.setProperty(PROXY_HOST_KEY, systemPropertyProxyHost);
        System.setProperty(PROXY_PORT_KEY, Integer.toString(systemPropertyProxyPort));
        System.setProperty(PROXY_USERNAME_KEY, systemPropertyProxyUsername);
        System.setProperty(PROXY_PASSWORD_KEY, systemPropertyProxyPassword);
        System.setProperty(CONNECT_TIMEOUT_KEY, Integer.toString(systemPropertyConnectTimeout));
        System.setProperty(READ_TIMEOUT_KEY, Integer.toString(systemPropertyReadTimeout));
        
        config = new PropertyConfig("no.properties");
        
        assertEquals(systemPropertyApiKey, config.getApiKey());
        assertEquals(systemPropertyApplicationID, config.getApplicationID());
        assertEquals(systemPropertyApplicationDomain, config.getApplicationDomain());
        assertEquals(systemPropertyTokenUrl, config.getTokenUrl());
        assertEquals(systemPropertyLanguagePreference, config.getLanguagePreference());
        assertEquals(systemPropertyProxyHost, config.getProxyHost());
        assertEquals(systemPropertyProxyPort, config.getProxyPort());
        assertEquals(systemPropertyProxyUsername, config.getProxyUsername());
        assertEquals(systemPropertyProxyPassword, config.getProxyPassword());
        assertEquals(systemPropertyConnectTimeout, config.getConnectTimeout());
        assertEquals(systemPropertyReadTimeout, config.getReadTimeout());
    }
    
    @Test
    public void testPropertyConfigSystemPropertiesOverridden() {
        System.setProperty(API_KEY_KEY, systemPropertyApiKey);
        System.setProperty(APPLICATION_ID_KEY, systemPropertyApplicationID);
        System.setProperty(APPLICATION_DOMAIN_KEY, systemPropertyApplicationDomain);
        System.setProperty(TOKEN_URL_KEY, systemPropertyTokenUrl);
        System.setProperty(LANGUAGE_PREFERENCE_KEY, systemPropertyLanguagePreference);
        System.setProperty(PROXY_HOST_KEY, systemPropertyProxyHost);
        System.setProperty(PROXY_PORT_KEY, Integer.toString(systemPropertyProxyPort));
        System.setProperty(PROXY_USERNAME_KEY, systemPropertyProxyUsername);
        System.setProperty(PROXY_PASSWORD_KEY, systemPropertyProxyPassword);
        System.setProperty(CONNECT_TIMEOUT_KEY, Integer.toString(systemPropertyConnectTimeout));
        System.setProperty(READ_TIMEOUT_KEY, Integer.toString(systemPropertyReadTimeout));
        
        config = new PropertyConfig("janrain4j.properties");
        
        assertEquals("my-config-api-key", config.getApiKey());
        assertEquals("my-config-application-id", config.getApplicationID());
        assertEquals("http://my-config-application-domain.com", config.getApplicationDomain());
        assertEquals("http://my-config-token-url.com", config.getTokenUrl());
        assertEquals("my-config-language-preference", config.getLanguagePreference());
        assertEquals("http://my-config-proxy-host.com", config.getProxyHost());
        assertEquals(8080, config.getProxyPort());
        assertEquals("my-config-proxy-username", config.getProxyUsername());
        assertEquals("my-config-proxy-password", config.getProxyPassword());
        assertEquals(30000, config.getConnectTimeout());
        assertEquals(60000, config.getReadTimeout());
    }
    
    @Test
    public void testEmptyPropertyConfig() {
        config = new PropertyConfig("empty.properties");
        
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
    }
}
