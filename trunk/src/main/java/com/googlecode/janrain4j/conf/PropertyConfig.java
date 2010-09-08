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

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
class PropertyConfig extends Config {

    public static final String JANRAIN4J_PROPERTIES = "janrain4j.properties";
    
    public static final String KEY_PREFIX = "janrain4j.";
    
    public static final String API_KEY_KEY = KEY_PREFIX + "api.key";
    public static final String APPLICATION_ID_KEY = KEY_PREFIX + "application.id";
    public static final String APPLICATION_DOMAIN_KEY = KEY_PREFIX + "application.domain";
    public static final String TOKEN_URL_KEY = KEY_PREFIX + "token.url";
    public static final String LANGUAGE_PREFERENCE_KEY = KEY_PREFIX + "language.preference";
    public static final String PROXY_HOST_KEY = KEY_PREFIX + "proxy.host";
    public static final String PROXY_PORT_KEY = KEY_PREFIX + "proxy.port";
    public static final String PROXY_USERNAME_KEY = KEY_PREFIX + "proxy.username";
    public static final String PROXY_PASSWORD_KEY = KEY_PREFIX + "proxy.password";
    public static final String CONNECT_TIMEOUT_KEY = KEY_PREFIX + "connect.timeout";
    public static final String READ_TIMEOUT_KEY = KEY_PREFIX + "read.timeout";
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public PropertyConfig() {
        this(JANRAIN4J_PROPERTIES);
    }
    
    public PropertyConfig(String file) {
        
        log.info("Loading janrain4j properties file: " + file + "...");
        
        Properties properties;
        try {
            properties = (Properties) System.getProperties().clone();
        }
        catch (SecurityException e) {
            log.info("Unable to retrieve system properties", e);
            properties = new Properties();
        }
        try {
            properties.load(getClass().getResourceAsStream("/" + file));
            log.info("Successfully loaded janrain4j properties file");
        }
        catch (Exception e) {
            log.error("Unable to load properties file: " + file, e);
        }
        
        if (properties.containsKey(API_KEY_KEY)) {
            this.apiKey(properties.getProperty(API_KEY_KEY));
        }
        
        if (properties.containsKey(APPLICATION_ID_KEY)) {
            this.applicationID(properties.getProperty(APPLICATION_ID_KEY));
        }
        
        if (properties.containsKey(APPLICATION_DOMAIN_KEY)) {
            this.applicationDomain(properties.getProperty(APPLICATION_DOMAIN_KEY));
        }
        
        if (properties.containsKey(TOKEN_URL_KEY)) {
            this.tokenUrl(properties.getProperty(TOKEN_URL_KEY));
        }
        
        if (properties.containsKey(LANGUAGE_PREFERENCE_KEY)) {
            this.languagePreference(properties.getProperty(LANGUAGE_PREFERENCE_KEY));
        }
        
        if (properties.containsKey(PROXY_HOST_KEY)) {
            this.proxyHost(properties.getProperty(PROXY_HOST_KEY));
        }
        
        if (properties.containsKey(PROXY_PORT_KEY)) {
            this.proxyPort(parseInt(properties.getProperty(PROXY_PORT_KEY)));
        }
        
        if (properties.containsKey(PROXY_USERNAME_KEY)) {
            this.proxyUsername(properties.getProperty(PROXY_USERNAME_KEY));
        }
        
        if (properties.containsKey(PROXY_PASSWORD_KEY)) {
            this.proxyPassword(properties.getProperty(PROXY_PASSWORD_KEY));
        }
        
        if (properties.containsKey(CONNECT_TIMEOUT_KEY)) {
            this.connectTimeout(parseInt(properties.getProperty(CONNECT_TIMEOUT_KEY)));
        }
        
        if (properties.containsKey(READ_TIMEOUT_KEY)) {
            this.readTimeout(parseInt(properties.getProperty(READ_TIMEOUT_KEY)));
        }
    }
    
    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
