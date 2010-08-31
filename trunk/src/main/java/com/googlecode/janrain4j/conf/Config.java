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
package com.googlecode.janrain4j.conf;

/**
 * User-configurable properties. In most cases it is not needed to create a 
 * <code>Config</code> manually. To create a <code>Config</code> 
 * programatically, the recommended way is to statically import 
 * {@link Config.Builder}.* and invoke the static build method followed by the 
 * desired instance mutators:
 * <blockquote>
 * <pre>
 * import static com.googlecode.janrain4j.conf.Config.Builder.*;
 * 
 * ...
 * 
 * // specify API key
 * Config config = build().apiKey(apiKey);
 * 
 * // specify API key and proxy
 * Config config = build().apiKey(apiKey).proxy(proxyHost, proxyPort);
 * 
 * // overview of all configurable properties
 * Config config = build()
 *         .apiKey(apiKey)
 *         .apiUrl(apiUrl)
 *         .proxy(proxyHost, proxyPort)
 *         .proxy(proxyHost, proxyPort, proxyUsername, proxyPassword)
 *         .connectTimeout(connectTimeout)
 *         .readTimeout(readTimeout)
 *         .tokenUrl(tokenUrl)
 *         .embedUrl(embedUrl);
 * </pre>
 * </blockquote>
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class Config {

    private String apiKey = null;
    private String apiUrl = "https://rpxnow.com/api/v2";
    private String proxyHost = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    private String proxyPassword = null;
    private int connectTimeout = -1;
    private int readTimeout = -1;
    private String embedUrl = null;
    private String tokenUrl = null;
    
    Config() {
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
     * Returns the embed url.
     */
    public String getEmbedUrl() {
        return embedUrl;
    }
    
    /**
     * Returns the token url.
     */
    public String getTokenUrl() {
        return tokenUrl;
    }
    
    /**
     * Sets the Janrain Engage API key.
     * 
     * @param apiKey Your Janrain Engage API key.
     * @return <code>this</code> (for chaining)
     */
    public Config apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
    
    /**
     * Sets the Janrain Engage API url.
     * 
     * @param apiUrl The base API url.
     * @return <code>this</code> (for chaining)
     */
    public Config apiUrl(String apiUrl) {
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
    public Config proxy(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
        return this;
    }
    
    /**
     * Sets the proxy using authentication.
     * 
     * @param host The proxy host.
     * @param port The proxy port.
     * @param username The proxy username.
     * @param password The proxy password.
     * @return <code>this</code> (for chaining)
     */
    public Config proxy(String host, int port, String username, String password) {
        this.proxyHost = host;
        this.proxyPort = port;
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
    public Config connectTimeout(int timeout) {
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
    public Config readTimeout(int timeout) {
        this.readTimeout = timeout;
        return this;
    }
    
    /**
     * Sets the embed url.
     * 
     * @param embedUrl The embed url.
     * @return <code>this</code> (for chaining)
     */
    public Config embedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
        return this;
    }
    
    /**
     * Sets the token url.
     * 
     * @param tokenUrl The token url.
     * @return <code>this</code> (for chaining)
     */
    public Config tokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }
    
    /**
     * Contains static creation methods for <code>Config</code>. 
     */
    public static class Builder {

        private Builder() {
        }
        
        /**
         * Create a <code>Config</code> with default values.
         * 
         * @return The newly created <code>Config</code> instance.
         */
        public static Config build() {
            return new Config();
        }
    }
}
