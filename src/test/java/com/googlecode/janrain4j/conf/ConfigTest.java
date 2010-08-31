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

import static com.googlecode.janrain4j.conf.Config.Builder.build;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ConfigTest {

    private String apiKey = "my-api-key";
    private String apiUrl = "http://my-api-url.com";
    private String proxyHost = "http://my-proxy.com";
    private int proxyPort = 8080;
    private String proxyUsername = "my-username";
    private String proxyPassword = "my-password";
    private int connectTimeout = 60000;
    private int readTimeout = 120000;
    private String embedUrl = "http://my-embed-url.com";
    private String tokenUrl = "http://my-token-url.com";
    
    @Test
    public void testBuilderDefaultValues() {
        Config config = build();
        
        assertNull(config.getApiKey());
        assertEquals("https://rpxnow.com/api/v2", config.getApiUrl());
        assertNull(config.getProxyHost());
        assertEquals(-1, config.getProxyPort());
        assertNull(config.getProxyUsername());
        assertNull(config.getProxyPassword());
        assertEquals(-1, config.getConnectTimeout());
        assertEquals(-1, config.getReadTimeout());
        assertNull(config.getEmbedUrl());
        assertNull(config.getTokenUrl());
    }
    
    @Test
    public void testMutators() {
        Config config = build()
            .apiKey(apiKey)
            .apiUrl(apiUrl)
            .proxyHost(proxyHost)
            .proxyPort(proxyPort)
            .proxyUsername(proxyUsername)
            .proxyPassword(proxyPassword)
            .connectTimeout(connectTimeout)
            .readTimeout(readTimeout)
            .embedUrl(embedUrl)
            .tokenUrl(tokenUrl);
        
        assertEquals(apiKey, config.getApiKey());
        assertEquals(apiUrl, config.getApiUrl());
        assertEquals(proxyHost, config.getProxyHost());
        assertEquals(proxyPort, config.getProxyPort());
        assertEquals(proxyUsername, config.getProxyUsername());
        assertEquals(proxyPassword, config.getProxyPassword());
        assertEquals(connectTimeout, config.getConnectTimeout());
        assertEquals(readTimeout, config.getReadTimeout());
        assertEquals(embedUrl, config.getEmbedUrl());
        assertEquals(tokenUrl, config.getTokenUrl());
    }    
}
