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

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

/**
 * Finalizes the Janrain authentication by loading the local user details. 
 * The <code>UserDetails</code> are loaded by calling the configured 
 * <code>AuthenticationUserDetailsService</code>.
 * <p>
 * If no <code>AuthenticationUserDetailsService</code> is configured this 
 * provider will return a new {@link JanrainUserDetails} instance containing 
 * the {@link UserDataResponse}.
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
public class JanrainAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationUserDetailsService authenticationUserDetailsService;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        if (supports(authentication.getClass())) {
            
            JanrainAuthenticationToken janrainAuthenticationToken = (JanrainAuthenticationToken) authentication;
            UserDataResponse userDataResponse = janrainAuthenticationToken.getUserDataResponse();
            
            UserDetails userDetails = null;
            
            if (authenticationUserDetailsService == null) {
                userDetails = new JanrainUserDetails(userDataResponse);
            }
            else {
                userDetails = authenticationUserDetailsService.loadUserDetails(janrainAuthenticationToken);
            }
            
            return new JanrainAuthenticationToken(userDetails, userDetails.getAuthorities(), userDataResponse);
        }
        
        return null;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return JanrainAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
    /**
     * Sets the <code>AuthenticationUserDetailsService</code> to load the local 
     * user details.
     * 
     * @param authenticationUserDetailsService The <code>AuthenticationUserDetailsService</code>.
     */
    public void setAuthenticationUserDetailsService(AuthenticationUserDetailsService authenticationUserDetailsService) {
        this.authenticationUserDetailsService = authenticationUserDetailsService;
    }
}
