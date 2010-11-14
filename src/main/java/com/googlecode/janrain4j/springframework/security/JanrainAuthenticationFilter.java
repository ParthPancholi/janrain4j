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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
public class JanrainAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private Log log = LogFactory.getLog(this.getClass());

    private AuthenticationUserDetailsService authenticationUserDetailsService = null;
    private EngageService engageService = null;
    private boolean extended = false;
    
    public JanrainAuthenticationFilter() {
        super("/j_spring_janrain_security_check");
    }
    
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        if (authenticationDetailsSource == null) {
            // TODO use default janrain authentication user detail service?
        }
        Assert.notNull(engageService, "engageService must be specified");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        
        Authentication authentication = null;
        
        String token = request.getParameter("token");
        
        if (token != null && token.length() > 0) {
            
            if (log.isDebugEnabled()) {
                log.debug("Janrain Engage token: " + token);
            }
            
            try {
                UserDataResponse userDataResponse = engageService.authInfo(token, extended);
                JanrainAuthenticationToken janrainAuthenticationToken = new JanrainAuthenticationToken(userDataResponse);
                
                authentication = janrainAuthenticationToken;
                authentication.setAuthenticated(true); // TODO for testing
                
                // authentication = this.getAuthenticationManager().authenticate(janrainAuthenticationToken);
            }
            catch (EngageFailureException e) {
                throw new AuthenticationServiceException("TODO"); // TODO
            }
            catch (ErrorResponeException e) {
                throw new AuthenticationServiceException("TODO"); // TODO
            }
        }
        
        return authentication;
    }
    
    public void setAuthenticationUserDetailsService(AuthenticationUserDetailsService authenticationUserDetailsService) {
        this.authenticationUserDetailsService = authenticationUserDetailsService;
    }
    
    public void setEngageService(EngageService engageService) {
        this.engageService = engageService;
    }
    
    public void setExtended(boolean extended) {
        this.extended = extended;
    }
}
