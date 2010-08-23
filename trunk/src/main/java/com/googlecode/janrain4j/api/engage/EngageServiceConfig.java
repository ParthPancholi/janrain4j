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
package com.googlecode.janrain4j.api.engage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.googlecode.janrain4j.internal.http.HttpClient;
import com.googlecode.janrain4j.internal.http.HttpClientConfig;
import com.googlecode.janrain4j.internal.http.HttpClientImpl;
import com.googlecode.janrain4j.internal.http.HttpFailureException;

/**
 * User-configurable properties of the {@link EngageService}. The recommended 
 * way to instantiate a <code>EngageServiceConfig</code> object is to 
 * statically import {@link EngageServiceConfig.Builder}.* and invoke a static 
 * creation method followed by an instance mutator (if needed):
 * <blockquote>
 * <pre>
 * import static com.googlecode.janrain4j.api.engage.EngageServiceConfig.Builder.*;
 * 
 * ...
 * 
 * // specify API key
 * EngageServiceConfig config = withApiKey(apiKey);
 * 
 * // specify API key and proxy
 * EngageServiceConfig config = withApiKey(apiKey).proxy(proxyHost, proxyPort);
 * 
 * // overview of all configurable properties
 * EngageServiceConfig config = withApiKey(apiKey)
 *         .apiUrl(apiUrl)
 *         .proxy(proxyHost, proxyPort)
 *         .proxyAuthentication(proxyUsername, proxyPassword)
 *         .connectTimeout(connectTimeout)
 *         .readTimeout(readTimeout)
 * </pre>
 * </blockquote>
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class EngageServiceConfig {

    public static final String DEFAULT_API_KEY = null;
    public static final String DEFAULT_API_URL = "https://rpxnow.com/api/v2";
    public static final String DEFAULT_PROXY_HOST = HttpClientConfig.DEFAULT_PROXY_HOST;
    public static final int DEFAULT_PROXY_PORT = HttpClientConfig.DEFAULT_PROXY_PORT;
    public static final String DEFAULT_PROXY_USERNAME = HttpClientConfig.DEFAULT_PROXY_USERNAME;
    public static final String DEFAULT_PROXY_PASSWORD = HttpClientConfig.DEFAULT_PROXY_PASSWORD;
    public static final int DEFAULT_CONNECT_TIMEOUT = HttpClientConfig.DEFAULT_CONNECT_TIMEOUT;
    public static final int DEFAULT_READ_TIMEOUT = HttpClientConfig.DEFAULT_READ_TIMEOUT;
    public static final Class<? extends HttpClient> DEFAULT_HTTP_CLIENT_CLASS = HttpClientImpl.class;
    
    private String apiKey = DEFAULT_API_KEY;
    private String apiUrl = DEFAULT_API_URL;
    private String proxyHost = DEFAULT_PROXY_HOST;
    private int proxyPort = DEFAULT_PROXY_PORT;
    private String proxyUsername = DEFAULT_PROXY_USERNAME;
    private String proxyPassword = DEFAULT_PROXY_PASSWORD;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;
    private Class<? extends HttpClient> httpClientClass = DEFAULT_HTTP_CLIENT_CLASS;
    
    private EngageServiceConfig() {
    }
    
    /**
     * Returns the Janrain Engage API key.
     */
    public String getApiKey() {
        return apiKey;
    }
    
    /**
     * Returns the Janrain Engage API url.
     */
    public String getApiUrl() {
        return apiUrl;
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
     * Returns the http client implementation class.
     */
    public Class<? extends HttpClient> getHttpClientClass() {
        return httpClientClass;
    }
    
    /**
     * Sets the Janrain Engage API key.
     * 
     * @param apiKey Your Janrain Engage API key.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
    
    /**
     * Sets the Janrain Engage API url.
     * 
     * @param apiUrl The base API url.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig apiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }
    
    /**
     * Sets the proxy.
     * 
     * @param host The proxy host.
     * @param port The proxy port.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig proxy(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
        return this;
    }
    
    /**
     * Sets the proxy authentication.
     * 
     * @param username The proxy username.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig proxyAuthentication(String username, String password) {
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
    public EngageServiceConfig connectTimeout(int timeout) {
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
    public EngageServiceConfig readTimeout(int timeout) {
        this.readTimeout = timeout;
        return this;
    }
    
    /**
     * Sets the <code>HttpClient</code> implementation class to use for making 
     * HTTP requests.
     * 
     * @param client The <code>HttpClient</code> implementation class.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig httpClientClass(Class<? extends HttpClient> clazz) {
        this.httpClientClass = clazz;
        return this;
    }
    
    /**
     * Contains static creation methods for {@link EngageServiceConfig}.
     *  
     * @author Marcel Overdijk
     * @since 1.0
     */
    public static class Builder {
        
        private Builder() {
        }
        
        private static EngageServiceConfig withDefaults() {
            return new EngageServiceConfig();
        }
        
        /**
         * Create an {@link EngageServiceConfig} with the given API key.
         * 
         * @param apiKey Your Janrain Engage API key.
         * @return The newly created <code>EngageServiceConfig</code> instance.
         */
        public static EngageServiceConfig withApiKey(String apiKey) {
            return withDefaults().apiKey(apiKey);
        }
    }
}
