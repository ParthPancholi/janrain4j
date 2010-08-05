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

import java.util.Map;

import com.googlecode.janrain4j.internal.http.HttpClient;
import com.googlecode.janrain4j.internal.http.HttpClientImpl;

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

    /**
     * The default Janrain Engage API url.
     */
    public static final String DEFAULT_API_URL = "https://rpxnow.com/api/v2";
    
    private String apiKey = null;
    private String apiUrl = null;
    private String proxyHost = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    private String proxyPassword = null;
    private int connectTimeout = -1;
    private int readTimeout = -1;
    private Class<? extends HttpClient> httpClientImpl;
    private Map<?, ?> httpClientImplAdditionalProperties;
    
    private EngageServiceConfig() {
        apiUrl = DEFAULT_API_URL;
        httpClientImpl = HttpClientImpl.class;
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
     * Returns the <code>HttpClient</code> implementation class.
     */
    public Class<? extends HttpClient> getHttpClientImpl() {
        return httpClientImpl;
    }
    
    /**
     * Returns the additional properties to be passed on to the 
     * <code>HttpClient</code> implementation class.
     */
    public Map<?, ?> getHttpClientImplAdditionalProperties() {
        return httpClientImplAdditionalProperties;
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
     * @param clazz The <code>HttpClient</code> implementation class.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig httpClientImpl(Class<? extends HttpClient> clazz) {
        this.httpClientImpl = clazz;
        return this;
    }
    
    /**
     * Sets the additional properties to be passed on to the 
     * <code>HttpClient</code> implementation class.
     * 
     * @param properties The additional properties.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig httpClientImplAdditionalProperties(Map<?, ?> properties) {
        this.httpClientImplAdditionalProperties = properties;
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
