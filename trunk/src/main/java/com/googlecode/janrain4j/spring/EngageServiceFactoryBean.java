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

/**
 * Spring {@link org.springframework.beans.factory.FactoryBean} that creates 
 * an {@link com.googlecode.janrain4j.api.engage.EngageService}.
 *
 * Example configuration:
 * 
 * <pre class="code">&lt;bean class="com.googlecode.janrain4j.spring.EngageServiceFactoryBean"
 *   p:apiKey="your-engage-api-key" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @see com.googlecode.janrain4j.api.engage.EngageService
 * @since 1.0
 */
public class EngageServiceFactoryBean implements FactoryBean<EngageService>, InitializingBean {

    private EngageService engageService;
    
    private String apiKey;
    private String apiUrl;
    
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
        EngageServiceConfig config = withApiKey(this.apiKey);
        
        if (this.apiUrl != null && this.apiUrl.length() > 0)
            config.apiUrl(apiUrl);
        
        this.engageService = EngageServiceFactory.getEngageService(config);
        // TODO set proxy + authenticator
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
}
