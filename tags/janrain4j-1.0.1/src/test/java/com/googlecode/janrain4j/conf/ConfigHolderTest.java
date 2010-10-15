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
package com.googlecode.janrain4j.conf;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConfigHolder.class)
public class ConfigHolderTest {

    @Test
    public void testSetGetConfig() {
        Config config = mock(Config.class);
        ConfigHolder.setConfig(config);
        
        assertSame(config, ConfigHolder.getConfig());
    }
    
    @Test
    public void testGetConfigCreatesNewConfig() throws Exception {
        PropertyConfig config = mock(PropertyConfig.class);
        ConfigHolder.setConfig(null);
        
        whenNew(PropertyConfig.class).withNoArguments().thenReturn(config);
        
        assertNotNull(ConfigHolder.getConfig());
        
        verifyNew(PropertyConfig.class);
    }
}
