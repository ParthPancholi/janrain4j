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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.conf.Config;

/**
 * {@link FactoryBean} that sets up an {@link EngageService} and exposes it for 
 * bean references.
 * 
 * <p>Usage example:
 * <pre>
 * &lt;bean class="com.googlecode.janrain4j.springframework.EngageServiceFactoryBean" /&gt;
 * </pre>
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
public class EngageServiceFactoryBean implements FactoryBean<EngageService>, InitializingBean {

    private EngageService engageService = null;
    private Config config = null;
    
    public EngageService getObject() throws Exception {
        return engageService;
    }

    public Class<? extends EngageService> getObjectType() {
        return EngageService.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        if (config == null) {
            engageService = EngageServiceFactory.getEngageService();
        }
        else {
            engageService = EngageServiceFactory.getEngageService(config);
        }
    }
    
    public void setConfig(Config config) {
        this.config = config;
    }
}
