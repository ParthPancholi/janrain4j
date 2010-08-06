/* Copyright 2010 Marcel Overdijk
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

import org.junit.Test

class HttpClientConfigTest {

    HttpClientConfig config = null
    
    String proxyHost = "mydomain.com" 
    int proxyPort = 80
    String proxyUsername = "victoria"
    String proxyPassword = "secret"
    int connectTimeout = 30000
    int readTimeout = 60000
    Map<?, ?> additionalProperties = [p1: "v1", p2: "v2"]
    
    @Test
    void testWithDefaults() {
        config = withDefaults()
        assertEquals HttpClientConfig.DEFAULT_PROXY_HOST, config.getProxyHost()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PORT, config.getProxyPort()
        assertEquals HttpClientConfig.DEFAULT_PROXY_USERNAME, config.getProxyUsername()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PASSWORD, config.getProxyPassword()
        assertEquals HttpClientConfig.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout()
        assertEquals HttpClientConfig.DEFAULT_READ_TIMEOUT, config.getReadTimeout()
        assertEquals HttpClientConfig.DEFAULT_ADDITIONAL_PROPERTIES, config.getAdditionalProperties()
    }
    
    @Test
    void testWithProxy() {
        config = withProxy(proxyHost, proxyPort)
        assertEquals proxyHost, config.getProxyHost()
        assertEquals proxyPort, config.getProxyPort()
        assertEquals HttpClientConfig.DEFAULT_PROXY_USERNAME, config.getProxyUsername()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PASSWORD, config.getProxyPassword()
        assertEquals HttpClientConfig.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout()
        assertEquals HttpClientConfig.DEFAULT_READ_TIMEOUT, config.getReadTimeout()
        assertEquals HttpClientConfig.DEFAULT_ADDITIONAL_PROPERTIES, config.getAdditionalProperties()
    }
    
    @Test
    void testWithPRoxyAuthentication() {
        config = withProxyAuthentication(proxyUsername, proxyPassword);
        assertEquals HttpClientConfig.DEFAULT_PROXY_HOST, config.getProxyHost()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PORT, config.getProxyPort()
        assertEquals proxyUsername, config.getProxyUsername()
        assertEquals proxyPassword, config.getProxyPassword()
        assertEquals HttpClientConfig.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout()
        assertEquals HttpClientConfig.DEFAULT_READ_TIMEOUT, config.getReadTimeout()
        assertEquals HttpClientConfig.DEFAULT_ADDITIONAL_PROPERTIES, config.getAdditionalProperties()
    }
    
    @Test
    void testWithConnectTimeout() {
        config = withConnectTimeout(connectTimeout)
        assertEquals HttpClientConfig.DEFAULT_PROXY_HOST, config.getProxyHost()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PORT, config.getProxyPort()
        assertEquals HttpClientConfig.DEFAULT_PROXY_USERNAME, config.getProxyUsername()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PASSWORD, config.getProxyPassword()
        assertEquals connectTimeout, config.getConnectTimeout()
        assertEquals HttpClientConfig.DEFAULT_READ_TIMEOUT, config.getReadTimeout()
        assertEquals HttpClientConfig.DEFAULT_ADDITIONAL_PROPERTIES, config.getAdditionalProperties()
    }
    
    @Test
    void testWithReadTimeout() {
        config = withReadTimeout(readTimeout)
        assertEquals HttpClientConfig.DEFAULT_PROXY_HOST, config.getProxyHost()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PORT, config.getProxyPort()
        assertEquals HttpClientConfig.DEFAULT_PROXY_USERNAME, config.getProxyUsername()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PASSWORD, config.getProxyPassword()
        assertEquals HttpClientConfig.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout()
        assertEquals readTimeout, config.getReadTimeout()
        assertEquals HttpClientConfig.DEFAULT_ADDITIONAL_PROPERTIES, config.getAdditionalProperties()
    }
    
    @Test
    void testWithAdditionalProperties() {
        config = withAdditionalProperties(additionalProperties)
        assertEquals HttpClientConfig.DEFAULT_PROXY_HOST, config.getProxyHost()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PORT, config.getProxyPort()
        assertEquals HttpClientConfig.DEFAULT_PROXY_USERNAME, config.getProxyUsername()
        assertEquals HttpClientConfig.DEFAULT_PROXY_PASSWORD, config.getProxyPassword()
        assertEquals HttpClientConfig.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout()
        assertEquals HttpClientConfig.DEFAULT_READ_TIMEOUT, config.getReadTimeout()
        assertEquals additionalProperties, config.getAdditionalProperties()
    }
    
    @Test
    void testWithAllConfigOptions() {
        config = withDefaults()
                .proxy(proxyHost, proxyPort)
                .proxyAuthentication(proxyUsername, proxyPassword)
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .additionalProperties(additionalProperties)
        assertEquals proxyHost, config.getProxyHost()
        assertEquals proxyPort, config.getProxyPort()
        assertEquals proxyUsername, config.getProxyUsername()
        assertEquals proxyPassword, config.getProxyPassword()
        assertEquals connectTimeout, config.getConnectTimeout()
        assertEquals readTimeout, config.getReadTimeout()
        assertEquals additionalProperties, config.getAdditionalProperties()
    }
}
