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
package com.googlecode.janrain4j.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceConfig;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngageServiceConfig.Builder.class)
public class EngageServiceFactoryBeanTest {

    private EngageServiceFactoryBean factoryBean = null;
    
    private EngageServiceConfig config = null;
    
    private String apiKey = "my-api-key";
    private String proxyHost = "http://my-proxy.com";
    private int proxyPort = 80;
    private String proxyUsername = "Victoria";
    private String proxyPassword = "Secret";
    private int connectTimeout = 30000;
    private int readTimeout = 60000;
    
    @Before
    public void setUp() {
        mockStatic(EngageServiceConfig.Builder.class);
        config = mock(EngageServiceConfig.class);
        when(EngageServiceConfig.Builder.withApiKey(apiKey)).thenReturn(config);
        
        factoryBean = new EngageServiceFactoryBean();
        factoryBean.setApiKey(apiKey);
    }
    
    @Test
    public void testGetObject() throws Exception {
        assertNull(factoryBean.getObject());
        factoryBean.afterPropertiesSet();
        assertNotNull(factoryBean.getObject());
    }
    
    @Test
    public void testGetObjectType() {
        assertEquals(EngageService.class, factoryBean.getObjectType());
    }
    
    @Test
    public void testIsSingleton() {
        assertTrue(factoryBean.isSingleton());
    }
    
    @Test
    public void testAfterPropertiesSetWithDefaults() throws Exception {
        factoryBean.afterPropertiesSet();
        
        verify(config, never()).proxy(anyString(), anyInt());
        verify(config, never()).proxy(anyString(), anyInt(), anyString(), anyString());
        verify(config, never()).connectTimeout(anyInt());
        verify(config, never()).readTimeout(anyInt());
    }
    
    @Test
    public void testAfterPropertiesSetWithProxy() throws Exception {
        factoryBean.setProxyHost(proxyHost);
        factoryBean.setProxyPort(proxyPort);
        factoryBean.afterPropertiesSet();
        
        verify(config).proxy(proxyHost, proxyPort);
        
        verify(config, never()).proxy(anyString(), anyInt(), anyString(), anyString());
        verify(config, never()).connectTimeout(anyInt());
        verify(config, never()).readTimeout(anyInt());
    }
    
    @Test
    public void testAfterPropertiesSetWithProxyAuthentication() throws Exception {
        factoryBean.setProxyHost(proxyHost);
        factoryBean.setProxyPort(proxyPort);
        factoryBean.setProxyUsername(proxyUsername);
        factoryBean.setProxyPassword(proxyPassword);
        factoryBean.afterPropertiesSet();
        
        verify(config).proxy(proxyHost, proxyPort, proxyUsername, proxyPassword);
        
        verify(config, never()).proxy(anyString(), anyInt());
        verify(config, never()).connectTimeout(anyInt());
        verify(config, never()).readTimeout(anyInt());
    }
    
    @Test
    public void testAfterPropertiesSetWithConnectTimeout() throws Exception {
        factoryBean.setConnectTimeout(connectTimeout);
        factoryBean.afterPropertiesSet();
        
        verify(config).connectTimeout(connectTimeout);
        
        verify(config, never()).proxy(anyString(), anyInt());
        verify(config, never()).proxy(anyString(), anyInt(), anyString(), anyString());
        verify(config, never()).readTimeout(anyInt());
    }
    
    @Test
    public void testAfterPropertiesSetWithReadTimeout() throws Exception {
        factoryBean.setReadTimeout(readTimeout);
        factoryBean.afterPropertiesSet();
        
        verify(config).readTimeout(readTimeout);
        
        verify(config, never()).proxy(anyString(), anyInt());
        verify(config, never()).proxy(anyString(), anyInt(), anyString(), anyString());
        verify(config, never()).connectTimeout(anyInt());
    }
}
