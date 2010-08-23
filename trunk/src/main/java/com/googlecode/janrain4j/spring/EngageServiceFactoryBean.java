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
package com.googlecode.janrain4j.spring;

import static com.googlecode.janrain4j.api.engage.EngageServiceConfig.Builder.*;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceConfig;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.internal.http.HttpClient;

/**
 * Spring {@link org.springframework.beans.factory.FactoryBean} that creates 
 * an {@link com.googlecode.janrain4j.api.engage.EngageService}.
 * <p>
 * Example configuration:
 * </p>
 * <pre class="code">&lt;bean class="com.googlecode.janrain4j.spring.EngageServiceFactoryBean"
 *      p:apiKey="${janrain.engage.api.key}" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @see com.googlecode.janrain4j.api.engage.EngageService
 * @since 1.0
 */
public class EngageServiceFactoryBean implements FactoryBean<EngageService>, InitializingBean {

    private EngageService engageService;
    
    private String apiKey = EngageServiceConfig.DEFAULT_API_KEY;
    private String apiUrl = EngageServiceConfig.DEFAULT_API_URL;
    private String proxyHost = EngageServiceConfig.DEFAULT_PROXY_HOST;
    private int proxyPort = EngageServiceConfig.DEFAULT_PROXY_PORT;
    private String proxyUsername = EngageServiceConfig.DEFAULT_PROXY_USERNAME;
    private String proxyPassword = EngageServiceConfig.DEFAULT_PROXY_PASSWORD;
    private int connectTimeout = EngageServiceConfig.DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = EngageServiceConfig.DEFAULT_READ_TIMEOUT;
    private Class<? extends HttpClient> httpClientClass = EngageServiceConfig.DEFAULT_HTTP_CLIENT_CLASS;
    
    public EngageService getObject() throws Exception {
        return this.engageService;
    }

    public Class<? extends EngageService> getObjectType() {
        return EngageService.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        
        // create engage service config
        EngageServiceConfig config = withApiKey(apiKey)
                .apiUrl(apiUrl)
                .proxy(proxyHost, proxyPort)
                .proxyAuthentication(proxyUsername, proxyPassword)
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .httpClientClass(httpClientClass);

        // create engage service
        this.engageService = EngageServiceFactory.getEngageService(config);
    }
    
    /**
     * Sets the Janrain Engage API key.
     * 
     * @param apiKey Your Janrain Engage API key.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Sets the Janrain Engage API url.
     * 
     * @param apiUrl The base API url.
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    /**
     * Sets the proxy host.
     * 
     * @param host The proxy host.
     */
    public void setProxyHost(String host) {
        this.proxyHost = host;
    }
    
    /**
     * Sets the proxy port
     * 
     * @param port The proxy port.
     */
    public void setProxyPort(int port) {
        this.proxyPort = port;
    }
    
    /**
     * Sets the proxy username.
     * 
     * @param username The proxy username.
     */
    public void setProxyUsername(String username) {
        this.proxyUsername = username;
    }
    
    /**
     * Sets the proxy password.
     * 
     * @param password The proxy password.
     */
    public void setProxyPassword(String password) {
        this.proxyPassword = password;
    }
    
    /**
     * Set the connect timeout, in milliseconds, to be used when opening the 
     * communications link to the resource.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The connect timeout value in milliseconds.
     */
    public void setConnectTimeout(int timeout) {
        this.connectTimeout = timeout;
    }
    
    /**
     * Sets the read timeout, in milliseconds, to be used when reading from the 
     * input steam.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The read timeout value in milliseconds.
     */
    public void setReadTimeout(int timeout) {
        this.readTimeout = timeout;
    }
    
    /**
     * Sets the <code>HttpClient</code> implementation class to use for making 
     * HTTP requests.
     * 
     * @param clazz The <code>HttpClient</code> implementation class.
     */
    public void setHttpClientClass(Class<? extends HttpClient> clazz) {
        this.httpClientClass = clazz;
    }
}
