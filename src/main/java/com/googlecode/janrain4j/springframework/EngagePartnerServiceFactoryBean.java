/* Copyright 2011 Sergey Tsymbler
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

import com.googlecode.janrain4j.api.engage.EngagePartnerService;
import com.googlecode.janrain4j.api.engage.EngagePartnerServiceFactory;
import com.googlecode.janrain4j.conf.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * {@link org.springframework.beans.factory.FactoryBean} that sets up an {@link com.googlecode.janrain4j.api.engage.EngagePartnerService} and exposes it for 
 * bean references.
 * 
 * <p>Usage example:
 * <pre>
 * &lt;bean class="com.googlecode.janrain4j.springframework.EngagePartnerServiceFactoryBean" /&gt;
 * </pre>
 * 
 * @author Sergey Tsymbler
 * @since 1.2
 */
public class EngagePartnerServiceFactoryBean implements FactoryBean<EngagePartnerService>, InitializingBean {

    private EngagePartnerService engagePartnerService = null;
    private Config config = null;
    
    public EngagePartnerService getObject() throws Exception {
        return engagePartnerService;
    }

    public Class<? extends EngagePartnerService> getObjectType() {
        return EngagePartnerService.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        if (config == null) {
            engagePartnerService = EngagePartnerServiceFactory.getEngagePartnerService();
        }
        else {
            engagePartnerService = EngagePartnerServiceFactory.getEngagePartnerService(config);
        }
    }
    
    public void setConfig(Config config) {
        this.config = config;
    }
}
