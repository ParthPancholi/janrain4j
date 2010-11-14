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
package com.googlecode.janrain4j.springframework.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
public class JanrainAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private Log log = LogFactory.getLog(this.getClass());
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("[*** MARCEL ***] authenticate called..."); // TODO remove
        if (supports(authentication.getClass())) {
            JanrainAuthenticationToken token = (JanrainAuthenticationToken) authentication;
            
        }
        return null;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return JanrainAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void afterPropertiesSet() throws Exception {
        // TODO
    }
}
