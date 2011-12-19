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

import com.googlecode.janrain4j.api.engage.EngagePartnerService;
import com.googlecode.janrain4j.api.engage.EngagePartnerServiceFactory;
import com.googlecode.janrain4j.conf.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngagePartnerServiceFactory.class)
public class EngagePartnerServiceFactoryBeanTest {

    private EngagePartnerServiceFactoryBean factory = null;
    private EngagePartnerService engagePartnerService = null;
    private Config config = null;
    
    @Before
    public void setUp() {
        factory = new EngagePartnerServiceFactoryBean();
        mockStatic(EngagePartnerServiceFactory.class);
        engagePartnerService = mock(EngagePartnerService.class);
        config = mock(Config.class);
    }
    
    @Test
    public void testGetObject() throws Exception {
        when(EngagePartnerServiceFactory.getEngagePartnerService()).thenReturn(engagePartnerService);
        
        factory.afterPropertiesSet();
        assertSame(engagePartnerService, factory.getObject());
        
        verifyStatic();
        EngagePartnerServiceFactory.getEngagePartnerService();
    }
    
    @Test
    public void testGetObjectWithConfig() throws Exception {
        when(EngagePartnerServiceFactory.getEngagePartnerService(config)).thenReturn(engagePartnerService);
        
        factory.setConfig(config);
        factory.afterPropertiesSet();
        assertSame(engagePartnerService, factory.getObject());
        
        verifyStatic();
        EngagePartnerServiceFactory.getEngagePartnerService(config);
    }
    
    @Test
    public void testGetObjectType() {
        assertTrue(factory.getObjectType().isAssignableFrom(EngagePartnerService.class));
    }
    
    @Test
    public void testIsSingleton() {
        assertTrue(factory.isSingleton());
    }
}
