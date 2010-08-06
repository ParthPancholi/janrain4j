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

import java.util.Map;

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
    public static final Map<?, ?> DEFAULT_ADDITIONAL_PROPERTIES = null;
    
    private String proxyHost = DEFAULT_PROXY_HOST;
    private int proxyPort = DEFAULT_PROXY_PORT;
    private String proxyUsername = DEFAULT_PROXY_USERNAME;
    private String proxyPassword = DEFAULT_PROXY_PASSWORD;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;
    private Map<?, ?> additionalProperties = DEFAULT_ADDITIONAL_PROPERTIES; 
    
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
    
    public Map<?, ?> getAdditionalProperties() {
        return additionalProperties;
    }
    
    /**
     * Sets the proxy.
     * 
     * @param host The proxy host.
     * @param port The proxy port.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxy(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
        return this;
    }
    
    /**
     * Sets the proxy authentication.
     * 
     * @param username The proxy username.
     * @param password The proxy password.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig proxyAuthentication(String username, String password) {
        this.proxyUsername = username;
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
     * Sets the additional properties.
     * 
     * @param properties The additional properties.
     * @return <code>this</code> (for chaining)
     */
    public HttpClientConfig additionalProperties(Map<?, ?> properties) {
        this.additionalProperties = properties;
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
        
        /**
         * Create an {@link HttpClientConfig} with default values.
         * 
         * @return The newly created <code>HttpClientConfig</code> instance.
         */
        public static HttpClientConfig withDefaults() {
            return new HttpClientConfig();
        }
        
        /**
         * Create an {@link HttpClientConfig} with the given proxy.
         * 
         * @param host The proxy host.
         * @param port The proxy port.
         * @return The newly created <code>HttpClientConfig</code> instance.
         */
        public static HttpClientConfig withProxy(String host, int port) {
            return withDefaults().proxy(host, port);
        }
        
        /**
         * Create an {@link HttpClientConfig} with the given proxy authentication.
         * 
         * @param username The proxy username.
         * @param password The proxy password.
         * @return The newly created <code>HttpClientConfig</code> instance.
         */        
        public static HttpClientConfig withProxyAuthentication(String username, String password) {
            return withDefaults().proxyAuthentication(username, password);
        }
        
        /**
         * Create an {@link HttpClientConfig} with the given connect timeout.
         * <p>
         * 0 implies that the option is disabled (i.e., timeout of infinity).
         * </p>
         * 
         * @param timeout The connect timeout value in milliseconds.
         * @return The newly created <code>HttpClientConfig</code> instance.
         */  
        public static HttpClientConfig withConnectTimeout(int timeout) {
            return withDefaults().connectTimeout(timeout);
        }
        
        /**
         * Create an {@link HttpClientConfig} with the given read timeout.
         * <p>
         * 0 implies that the option is disabled (i.e., timeout of infinity).
         * </p>
         * 
         * @param timeout The connect timeout value in milliseconds.
         * @return The newly created <code>HttpClientConfig</code> instance.
         */  
        public static HttpClientConfig withReadTimeout(int timeout) {
            return withDefaults().readTimeout(timeout);
        }
        
        /**
         * Create an {@link HttpClientConfig} with the given additional properties.
         * 
         * @param properties The additional properties.
         * @return The newly created <code>HttpClientConfig</code> instance.
         */  
        public static HttpClientConfig withAdditionalProperties(Map<?, ?> properties) {
            return withDefaults().additionalProperties(properties);
        }
    }
}
