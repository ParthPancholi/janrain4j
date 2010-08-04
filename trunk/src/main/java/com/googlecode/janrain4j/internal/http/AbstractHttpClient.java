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
 * Abstract {@link HttpClient} which convenient helper methods. 
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public abstract class AbstractHttpClient implements HttpClient {

    protected HttpClientConfig config;
    
    public AbstractHttpClient() {
    }
    
    /**
     * @param config The <code>HttpClientConfig</code>.
     */
    public AbstractHttpClient(HttpClientConfig config) {
        this.config = config;
    }
    
    /**
     * Returns the <code>HttpClientConfig</code>.
     */
    public HttpClientConfig getConfig() {
        return config;
    }

    /**
     * Sets the <code>HttpClientConfig</code>.
     * @param config The <code>HttpClientConfig</code>.
     */
    public void setConfig(HttpClientConfig config) {
        this.config = config;
    }
    
    /**
     * Indicates if the connection needs to use a proxy. 
     * 
     * @return A boolean indicating if the connection needs to use a proxy.
     */
    public boolean useProxy() {
        return (config.getProxyHost() != null && config.getProxyHost().length() > 0);
    }
    
    /**
     * Indicates if the connection needs to use proxy authentication. 
     * 
     * @return A boolean indicating if the connection needs to use proxy authentication.
     */
    public boolean useProxyAuthentication() {
        return (config.getProxyUsername() != null && config.getProxyUsername().length() > 0);
    }
}
