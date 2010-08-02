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

import static com.googlecode.janrain4j.api.engage.EngageServiceConfig.Builder.*;

/**
 * Creates {@link EngageService} implementations.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class EngageServiceFactory {

    /**
     * Creates an <code>EngageService</code> using the provided config.
     * 
     * @param config The <code>EngageServiceConfig</code>.
     * @return An <code>EngageService</code> instance.
     */
    public static EngageService getEngageService(EngageServiceConfig config) {
        return new EngageServiceImpl(config);
    }
    
    /**
     * Creates an <code>EngageService</code> using the provided API key.
     * 
     * @param apiKey Your Janrain Engage API key.
     * @return An <code>EngageService</code> instance.
     */
    public static EngageService getEngageService(String apiKey) {
        return new EngageServiceImpl(withApiKey(apiKey));
    }
}
