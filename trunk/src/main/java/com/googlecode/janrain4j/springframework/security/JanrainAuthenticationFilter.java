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

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

/**
 * Filter which processes Janrain authentication requests.
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
public class JanrainAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private EngageService engageService = null;
    private boolean extended = false;
    
    public JanrainAuthenticationFilter() {
        super("/j_spring_janrain_security_check");
    }
    
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        Assert.notNull(engageService, "engageService must be set");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        
        String token = request.getParameter("token");
        
        if (token != null && token.length() > 0) {
            
            try {
                UserDataResponse userDataResponse = engageService.authInfo(token, extended);
                
                JanrainAuthenticationToken janrainAuthenticationToken = new JanrainAuthenticationToken(userDataResponse);
                janrainAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
                
                return getAuthenticationManager().authenticate(janrainAuthenticationToken);
            }
            catch (EngageFailureException e) {
                throw new AuthenticationServiceException("Unable to retrieve Janrain user information", e);
            }
            catch (ErrorResponeException e) {
                throw new AuthenticationServiceException("Unable to retrieve Janrain user information", e);
            }
        }
        
        return null;
    }
    
    /**
     * Sets the <code>EngageService</code>.
     * 
     * @param engageService The <code>EngageService</code>.
     */
    public void setEngageService(EngageService engageService) {
        this.engageService = engageService;
    }
    
    /**
     * Configures the <code>EngageService</code> to return the extended Simple 
     * Registration and HCard data in addition to the normalized Portable Contacts 
     * format.
     * 
     * @param extended 'true' or 'false'(default).
     * @see EngageService#authInfo(String, boolean)
     */
    public void setExtended(boolean extended) {
        this.extended = extended;
    }
}
