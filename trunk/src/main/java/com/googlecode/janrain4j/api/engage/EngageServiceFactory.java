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
package com.googlecode.janrain4j.api.engage;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;

/**
 * Returns {@link EngageService} implementations.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class EngageServiceFactory {

    /**
     * Returns an <code>EngageService</code> using the <code>Config<code> set 
     * in {@link ConfigHolder}.
     * 
     * @return An <code>EngageService</code> instance.
     * @throws EngageFailureException If any unexpected unknown error occurs while creating the EngageService.
     */
    public static EngageService getInstance() throws EngageFailureException {
        return new EngageServiceImpl(ConfigHolder.getConfig());
    }
    
    /**
     * Returns an <code>EngageService</code> using the provided config.
     * 
     * @param config The config.
     * @return An <code>EngageService</code> instance.
     * @throws EngageFailureException If any unexpected unknown error occurs while creating the EngageService.
     */
    public static EngageService getInstance(Config config) throws EngageFailureException {
        return new EngageServiceImpl(config);
    }    
}
