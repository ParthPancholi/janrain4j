/* Copyright 2010 Marcel Overdijk

import java.net.HttpURLConnection;

import java.net.HttpURLConnection;

import java.sql.Connection;
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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.conf.Config;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngageServiceFactory.class)
public class EngageServiceFactoryBeanTest {

    private EngageServiceFactoryBean factory = null;
    private EngageService engageService = null;
    private Config config = null;
    
    @Before
    public void setUp() {
        factory = new EngageServiceFactoryBean();
        mockStatic(EngageServiceFactory.class);
        engageService = mock(EngageService.class);
        config = mock(Config.class);
    }
    
    @Test
    public void testGetObject() throws Exception {
        when(EngageServiceFactory.getEngageService()).thenReturn(engageService);
        
        factory.afterPropertiesSet();
        assertSame(engageService, factory.getObject());
        
        verifyStatic();
        EngageServiceFactory.getEngageService();
    }
    
    @Test
    public void testGetObjectWithConfig() throws Exception {
        when(EngageServiceFactory.getEngageService(config)).thenReturn(engageService);
        
        factory.setConfig(config);
        factory.afterPropertiesSet();
        assertSame(engageService, factory.getObject());
        
        verifyStatic();
        EngageServiceFactory.getEngageService(config);
    }
    
    @Test
    public void testGetObjectType() {
        assertTrue(factory.getObjectType().isAssignableFrom(EngageService.class));
    }
    
    @Test
    public void testIsSingleton() {
        assertTrue(factory.isSingleton());
    }
}
