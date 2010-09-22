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
package com.googlecode.janrain4j.springframework;

import static com.googlecode.janrain4j.conf.Config.Builder.build;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;

/**
 * {@link FactoryBean} that creates a {@link Config} and stores it the 
 * {@link ConfigHolder}.
 * 
 * Example configuration:
 * 
 * <pre class="code">&lt;bean class="com.googlecode.janrain4j.springframework.ConfigFactoryBean"
 *   p:apiKey="your-engage-api-key" .. /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Config
 * @see ConfigHolder
 * @see FactoryBean
 */
public class ConfigFactoryBean implements FactoryBean<Config>, InitializingBean {

    private Config config;
    
    private String apiKey = null;
    private String applicationID = null;
    private String applicationDomain = null;
    private String tokenUrl = null;
    private String languagePreference = null;
    private String proxyHost = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    private String proxyPassword = null;
    private int connectTimeout = -1;
    private int readTimeout = -1;
    
    public Config getObject() throws Exception {
        return this.config;
    }
    
    public Class<? extends Config> getObjectType() {
        return Config.class;
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public void afterPropertiesSet() throws Exception {
        this.config = build();
        if (apiKey != null && apiKey.length() > 0) {
            this.config.apiKey(apiKey);
        }
        if (applicationID != null && applicationID.length() > 0) {
            this.config.applicationID(applicationID);
        }
        if (applicationDomain != null && applicationDomain.length() > 0) {
            this.config.applicationDomain(applicationDomain);
        }
        if (tokenUrl != null && tokenUrl.length() > 0) {
            this.config.tokenUrl(tokenUrl);
        }
        if (languagePreference != null && languagePreference.length() > 0) {
            this.config.languagePreference(languagePreference);
        }
        if (proxyHost != null && proxyHost.length() > 0) {
            this.config.proxyHost(proxyHost);
        }
        if (proxyPort > -1) {
            this.config.proxyPort(proxyPort);
        }
        if (proxyUsername != null && proxyUsername.length() > 0) {
            this.config.proxyUsername(proxyUsername);
        }
        if (proxyPassword != null && proxyPassword.length() > 0) {
            this.config.proxyPassword(proxyPassword);
        }
        if (connectTimeout > -1) {
            this.config.connectTimeout(connectTimeout);
        }
        if (readTimeout > -1) {
            this.config.readTimeout(readTimeout);
        }
        ConfigHolder.setConfig(this.config);
    }
    
    /**
     * Returns the Janrain API key.
     */
    public String getApiKey() {
        return apiKey;
    }
    
    /**
     * Sets the Janrain API key.
     * 
     * @param apiKey Your Janrain API key.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Returns the Janrain application ID.
     */
    public String getApplicationID() {
        return applicationID;
    }
    
    /**
     * Sets the Janrain application ID.
     * 
     * @param applicationID The application ID.
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
    
    /**
     * Returns the Janrain application domain.
     */
    public String getApplicationDomain() {
        return applicationDomain;
    }
    
    /**
     * Sets the Janrain application domain.
     * 
     * @param applicationDomain The application domain.
     */
    public void setApplicationDomain(String applicationDomain) {
        this.applicationDomain = applicationDomain;
    }
    
    /**
     * Returns the token url.
     */
    public String getTokenUrl() {
        return tokenUrl;
    }
    
    /**
     * Sets the token url.
     * 
     * @param tokenUrl The token url.
     */
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
    
    /**
     * Returns the language preference.
     */
    public String getLanguagePreference() {
        return languagePreference;
    }
    
    /**
     * Sets the language preference.
     * 
     * @param languagePreference The language preference.
     */
    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }
    
    /**
     * Returns the proxy host.
     */
    public String getProxyHost() {
        return proxyHost;
    }
    
    /**
     * Sets the proxy host.
     * 
     * @param host The proxy host.
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
     * @param port The proxy port.
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
     * @param username The proxy username.
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
     * @param password The proxy password.
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
     * Set the connect timeout, in milliseconds, to be used when opening the 
     * communications link to the resource.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The connect timeout value in milliseconds.
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
     * Sets the read timeout, in milliseconds, to be used when reading from the 
     * input steam.
     * <p>
     * 0 implies that the option is disabled (i.e., timeout of infinity).
     * </p>
     * 
     * @param timeout The read timeout value in milliseconds.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
