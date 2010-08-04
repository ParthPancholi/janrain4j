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
 * User-configurable properties of the {@link HttpClientImpl}.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class HttpClientConfig {

    private String proxyHost;
    private int proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private int connectTimeout;
    private int readTimeout;
    
    private HttpClientConfig() {
    }
    
    /**
     * Indicates if the connection needs to use a proxy. 
     * 
     * @return A boolean indicating if the connection needs to use a proxy.
     */
    public boolean useProxy() {
        return (proxyHost != null && proxyHost.length() > 0);
    }
    
    /**
     * Indicates if the connection needs to use proxy authentication. 
     * 
     * @return A boolean indicating if the connection needs to use proxy authentication.
     */
    public boolean useProxyAuthentication() {
        return (proxyUsername != null && proxyUsername.length() > 0);
    }
    
    /**
     * Returns the proxy host.
     */
    public String getProxyHost() {
        return proxyHost;
    }
    
    /**
     * Returns the proxy port.
     */
    public int getProxyPort() {
        return proxyPort;
    }
    
    /**
     * Returns the proxy username.
     */
    public String getProxyUsername() {
        return proxyUsername;
    }
    
    /**
     * Returns the proxy password.
     */
    public String getProxyPassword() {
        return proxyPassword;
    }
    
    /**
     * Returns the connect timeout.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }
    
    /**
     * Returns the read timeout.
     */
    public int getReadTimeout() {
        return readTimeout;
    }
    
    /**
     * Sets the proxy host.
     * 
     * @param host The proxy host.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxyHost(String host) {
        this.proxyHost = host;
        return this;
    }
    
    /**
     * Sets the proxy port.
     * 
     * @param port The proxy port.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxyPort(int port) {
        this.proxyPort = port;
        return this;
    }
    
    /**
     * Sets the proxy username.
     * 
     * @param username The proxy username.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxyUsername(String username) {
        this.proxyUsername = username;
        return this;
    }
    
    /**
     * Sets the proxy password.
     * 
     * @param password The proxy password.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxyPassword(String password) {
        this.proxyPassword = password;
        return this;
    }
    
    /**
     * Set the connect timeout, in milliseconds, to be used when opening the 
     * communications link to the resource.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The connect timeout value in milliseconds.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig connectTimeout(int timeout) {
        this.connectTimeout = timeout;
        return this;
    }
    
    /**
     * Sets the read timeout, in milliseconds, to be used when reading from the 
     * input steam.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The read timeout value in milliseconds.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig readTimeout(int timeout) {
        this.readTimeout = timeout;
        return this;
    }
    
    /**
     * Contains static creation methods for {@link HttpClientConfig}.
     *  
     * @author Marcel Overdijk
     * @since 1.0
     */
    public static class Builder {
        
        private Builder() {
        }
        
        public static HttpClientConfig withDefaults() {
            return new HttpClientConfig();
        }
    }
}
