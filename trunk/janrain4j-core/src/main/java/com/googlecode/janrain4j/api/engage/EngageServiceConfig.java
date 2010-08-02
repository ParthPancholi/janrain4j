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

import java.net.Authenticator;
import java.net.Proxy;

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
 * Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(host, port));
 * EngageServiceConfig config = withApiKey(apiKey).proxy(proxy);
 * </pre>
 * </blockquote>
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class EngageServiceConfig {

    /**
     * The default Janrain Engage API url: <code>https://rpxnow.com/api/v2</code>
     */
    public static final String DEFAULT_API_URL = "https://rpxnow.com/api/v2";
    
    private String apiKey;
    private String apiUrl;
    private int connectTimeout;
    private int readTimeout;
    private Proxy proxy;
    private Authenticator authenticator;
    
    private EngageServiceConfig() {
    }
    
    /**
     * @return The Janrain Engage API key.
     */
    public String getApiKey() {
        return apiKey;
    }
    
    /**
     * @return The Janrain Engage API url.
     */
    public String getApiUrl() {
        return apiUrl;
    }
    
    /**
     * @return The connect timeout.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }
    
    /**
     * @return The read timeout.
     */
    public int getReadTimeout() {
        return readTimeout;
    }
    
    /**
     * @return The proxy.
     */
    public Proxy getProxy() {
        return proxy;
    }
    
    /**
     * @return The authenticator.
     */
    public Authenticator getAuthenticator() {
        return authenticator;
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
     * Sets the proxy.
     * 
     * @param proxy The proxy.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig proxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }
    
    /**
     * Sets the HTTP authentication.
     * 
     * @param authenticator The authenticator.
     * @return <code>this</code> (for chaining)
     */
    public EngageServiceConfig authenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
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
            EngageServiceConfig config = new EngageServiceConfig();
            config.apiUrl = DEFAULT_API_URL;
            return config;
        }
        
        /**
         * Create an {@link EngageServiceConfig} with the given API key.
         * 
         * @param apiKey Your Janrain Engage API key.
         * @return The newly created EngageServiceConfig instance.
         */
        public static EngageServiceConfig withApiKey(String apiKey) {
            return withDefaults().apiKey(apiKey);
        }
    }
}
