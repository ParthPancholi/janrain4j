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
package com.googlecode.janrain4j.conf;

/**
 * TODO
 * 
 * User-configurable properties of the {@link EngageService}. The recommended 
 * way to instantiate a <code>EngageServiceConfig</code> object is to 
 * statically import {@link EngageServiceConfig.Builder}.* and invoke a static 
 * creation method followed by an instance mutator (if needed):
 * <blockquote>
 * <pre>
 * import static com.googlecode.janrain4j.api.engage.EngageServiceConfig.Builder.*;
 * 
 * ...
 * 
 * // specify API key
 * EngageServiceConfig config = withApiKey(apiKey);
 * 
 * // specify API key and proxy
 * EngageServiceConfig config = withApiKey(apiKey).proxy(proxyHost, proxyPort);
 * 
 * // overview of all configurable properties
 * EngageServiceConfig config = withApiKey(apiKey)
 *         .apiUrl(apiUrl)
 *         .proxy(proxyHost, proxyPort)
 *         .proxy(proxyHost, proxyPort, proxyUsername, proxyPassword)
 *         .connectTimeout(connectTimeout)
 *         .readTimeout(readTimeout)
 * </pre>
 * </blockquote>
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
class PropertyConfig extends Config {

    public PropertyConfig() {
    }
}
