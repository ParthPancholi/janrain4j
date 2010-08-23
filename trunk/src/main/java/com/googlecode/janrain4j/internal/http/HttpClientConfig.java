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
package com.googlecode.janrain4j.internal.http;

/**
 * User-configurable properties of the {@link HttpClient}.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class HttpClientConfig {

    public static final String DEFAULT_PROXY_HOST = null;
    public static final int DEFAULT_PROXY_PORT = -1;
    public static final String DEFAULT_PROXY_USERNAME = null;
    public static final String DEFAULT_PROXY_PASSWORD = null;
    public static final int DEFAULT_CONNECT_TIMEOUT = -1;
    public static final int DEFAULT_READ_TIMEOUT = -1;
    
    private String proxyHost = DEFAULT_PROXY_HOST;
    private int proxyPort = DEFAULT_PROXY_PORT;
    private String proxyUsername = DEFAULT_PROXY_USERNAME;
    private String proxyPassword = DEFAULT_PROXY_PASSWORD;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;
    
    /**
     * Returns the proxy host.
     */
    public String getProxyHost() {
        return proxyHost;
    }
    
    /**
     * Sets the proxy host.
     * 
     * @param proxyHost The proxy host.
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }
    
    /**
     * Returns the proxy port.
     */
    public int getProxyPort() {
        return proxyPort;
    }
    
    /**
     * Sets the proxy port.
     * 
     * @param proxyPort The proxy port.
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    /**
     * Returns the proxy username.
     */
    public String getProxyUsername() {
        return proxyUsername;
    }
    
    /**
     * Sets the proxy username.
     * 
     * @param proxyUsername The proxy username.
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }
    
    /**
     * Returns the proxy password.
     */
    public String getProxyPassword() {
        return proxyPassword;
    }
    
    /**
     * Sets the proxy password.
     * 
     * @param proxyPassword The proxy password.
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
    
    /**
     * Returns the connect timeout.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }
    
    /**
     * Sets the connect timeout.
     * 
     * @param connectTimeout The connect timeout.
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    
    /**
     * Returns the read timeout.
     */
    public int getReadTimeout() {
        return readTimeout;
    }
    
    /**
     * Sets the read timeout.
     * 
     * @param readTimeout The read timeout.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
