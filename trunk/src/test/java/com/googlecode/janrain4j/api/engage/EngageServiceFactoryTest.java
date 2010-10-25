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
package com.googlecode.janrain4j.api.engage;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngageServiceFactory.class)
public class EngageServiceFactoryTest {

    private Config config = null;
    private EngageServiceImpl service = null;
    
    @Before
    public void setUp() {
        config = mock(Config.class);
        service = mock(EngageServiceImpl.class);
    }
    
    @Test
    public void testGetInstance() throws Exception {
        ConfigHolder.setConfig(config);
        
        whenNew(EngageServiceImpl.class).withNoArguments().thenReturn(service);

        EngageServiceFactory.getEngageService();
        
        verifyNew(EngageServiceImpl.class).withNoArguments();
    }
    
    @Test
    public void testGetInstanceWithConfig() throws Exception {
        whenNew(EngageServiceImpl.class).withArguments(config).thenReturn(service);
        
        EngageServiceFactory.getEngageService(config);
        
        verifyNew(EngageServiceImpl.class).withArguments(config);
    }
}
