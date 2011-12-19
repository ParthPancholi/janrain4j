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
package com.googlecode.janrain4j.api.engage;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngagePartnerServiceFactory.class)
public class EngagePartnerServiceFactoryTest {

    private Config config = null;
    private EngagePartnerServiceImpl service = null;

    @Before
    public void setUp() {
        config = mock(Config.class);
        service = mock(EngagePartnerServiceImpl.class);
    }

    @Test
    public void testGetInstance() throws Exception {
        ConfigHolder.setConfig(config);

        whenNew(EngagePartnerServiceImpl.class).withNoArguments().thenReturn(service);

        EngagePartnerServiceFactory.getEngagePartnerService();

        verifyNew(EngagePartnerServiceImpl.class).withNoArguments();
    }

    @Test
    public void testGetInstanceWithConfig() throws Exception {
        whenNew(EngagePartnerServiceImpl.class).withArguments(config).thenReturn(service);

        EngagePartnerServiceFactory.getEngagePartnerService(config);

        verifyNew(EngagePartnerServiceImpl.class).withArguments(config);
    }
}
