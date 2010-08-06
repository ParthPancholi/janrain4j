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
package com.googlecode.janrain4j.internal.http

import static com.googlecode.janrain4j.internal.http.HttpClientConfig.Builder.*
import static org.junit.Assert.*

import org.junit.Before 
import org.junit.Test

class HttpClientImplTest {

    HttpClientImpl client = null
    
    HttpClientConfig config = null
    HttpURLConnection connection = null
    
    String url = "http://www.mydomain.com"
    String proxyHost = "http://www.myproxy.com"
    int proxyPort = 80
    String proxyUsername = "victoria"
    String proxyPassword = "secret"
    int connectTimeout = 30000
    int readTimeout = 60000
    
    @Before
    void setUp() {
        config = withDefaults()
    }
    
    @Test
    public void testGetConnectionWithDefaults() {
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        assertFalse connection.usingProxy()
        assertNull client.authenticator
        assertEquals 0, connection.getConnectTimeout()
        assertEquals 0, connection.getReadTimeout()
    }
    
    @Test
    public void testGetConnectionWithProxy() {
        config.proxy proxyHost, proxyPort
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        assertTrue connection.usingProxy()
        assertNull client.authenticator
        assertEquals 0, connection.getConnectTimeout()
        assertEquals 0, connection.getReadTimeout()
    }
    
    @Test
    public void testGetConnectionWithProxyAuthentication() {
        config.proxy proxyHost, proxyPort
        config.proxyAuthentication proxyUsername, proxyPassword
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        // TODO assertTrue connection.usingProxy()
        assertNotNull client.authenticator
        assertEquals proxyUsername, client.authenticator.getPasswordAuthentication().getUserName()
        assertArrayEquals proxyPassword.toCharArray(), client.authenticator.getPasswordAuthentication().getPassword()
        assertEquals 0, connection.getConnectTimeout()
        assertEquals 0, connection.getReadTimeout()
    }
    
    @Test
    public void testGetConnectionWithConnectTimeout() {
        config.connectTimeout connectTimeout
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        assertFalse connection.usingProxy()
        assertNull client.authenticator
        assertEquals connectTimeout, connection.getConnectTimeout()
        assertEquals 0, connection.getReadTimeout()
    }
    
    @Test
    public void testGetConnectionWithReadTimeout() {
        config.readTimeout readTimeout
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        assertFalse connection.usingProxy()
        assertNull client.authenticator
        assertEquals 0, connection.getConnectTimeout()
        assertEquals readTimeout, connection.getReadTimeout()
    }
    
    @Test
    void testGetConnectionWithAllConfigOptions() {
        config.proxy proxyHost, proxyPort
        config.proxyAuthentication proxyUsername, proxyPassword
        config.connectTimeout connectTimeout
        config.readTimeout readTimeout
        client = new HttpClientImpl(config)
        connection = client.getConnection(url)
        // TODO assertTrue connection.usingProxy()
        assertNotNull client.authenticator
        assertEquals proxyUsername, client.authenticator.getPasswordAuthentication().getUserName()
        assertArrayEquals proxyPassword.toCharArray(), client.authenticator.getPasswordAuthentication().getPassword()
        assertEquals connectTimeout, connection.getConnectTimeout()
        assertEquals readTimeout, connection.getReadTimeout()
    }
    
    @Test(expected = IOException.class)
    public void testGetConnectionWithMalformedURL() {
        client = new HttpClientImpl(null)
        connection = client.getConnection("malformed url")
    }
    
    // TODO post
}
