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

/**
 * Returns {@link com.googlecode.janrain4j.api.engage.EngagePartnerService} implementations.
 *
 * @author Sergey Tsymbler
 * @since 1.2
 */
public class EngagePartnerServiceFactory {
    /**
     * Returns an <code>EngagePartnerService</code> using the <code>Config<code> set
     * in {@link com.googlecode.janrain4j.conf.ConfigHolder}.
     *
     * @return An <code>EngagePartnerService</code> instance.
     */
    public static EngagePartnerService getEngagePartnerService() {
        return new EngagePartnerServiceImpl();
    }

    /**
     * Returns an <code>EngagePartnerService</code> using the provided config.
     *
     * @param config The config.
     * @return An <code>EngagePartnerService</code> instance.
     */
    public static EngagePartnerService getEngagePartnerService(Config config) {
        return new EngagePartnerServiceImpl(config);
    }
}
