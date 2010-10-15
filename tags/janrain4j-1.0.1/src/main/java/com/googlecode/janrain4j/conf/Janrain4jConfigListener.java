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

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <code>ServletContextListener</code> for custom janrain4j initialization. 
 * This listener supports a <code>janrain4jConfigLocation</code> init parameter 
 * to specify the location of the janrain4j properties file.
 * 
 * <p>If used in a Spring environment the location can be:
 * <ul>
 *     <li>a "classpath:" location (e.g. <code>classpath:janrain4j.properties</code>)</li>
 *     <li>an absolute file URL (e.g. <code>file:C:/janrain4j.properties</code>)</li>
 *     <li>a plain path relative to the web application root directory (e.g. <code>/WEB-INF/janrain4j.properties</code>)</li>
 * </ul>
 * Also Spring <code>${}</code> placeholders are supported. The needed Spring  
 * classes are dynamically instantiated so there is no hard dependency to the 
 * Spring Framework.
 * 
 * <p>In non Spring environments <code>Class.getResource("/" + location)</code> 
 * is used to locate the janrain4j properties file.
 * 
 * <p>Example context-param configuration:
 * <pre>
 * &lt;context-param&gt;
 *     &lt;param-name&gt;janrain4jConfigLocation&lt;/param-name&gt;
 *     &lt;param-value&gt;my-janrain4j.properties&lt;/param-value&gt;
 * &lt;/context-param>
 * 
 * or
 * 
 * &lt;context-param&gt;
 *     &lt;param-name&gt;janrain4jConfigLocation&lt;/param-name&gt;
 *     &lt;param-value&gt;classpath:META-INF/janrain4j-${system.property.runtime.environment}.properties&lt;/param-value&gt;
 * &lt;/context-param>
 * </pre>
 * 
 * <p>Example listener configuration:
 * <pre>
 * &lt;/context-param>
 * 
 * &lt;listener&gt;
 *     &lt;listener-class&gt;com.googlecode.janrain4j.conf.Janrain4jConfigListener&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre> 
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class Janrain4jConfigListener implements ServletContextListener {

    public static final String CONFIG_LOCATION_PARAM = "janrain4jConfigLocation";
    
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        Config config = null;
        if (location != null && location.length() > 0) {
            config = new PropertyConfig(servletContext, location);
        }
        else {
            config = new PropertyConfig();
        }
        ConfigHolder.setConfig(config);
    }
    
    public void contextDestroyed(ServletContextEvent event) {
    }
}
