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

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

/**
 * {@link org.springframework.security.core.Authentication} implementation for 
 * Janrain authentication.
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
@SuppressWarnings("serial")
public class JanrainAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal = null;
    private UserDataResponse userDataResponse = null;
    
    /**
     * Constructor used for an authentication request. The {@link
     * org.springframework.security.core.Authentication#isAuthenticated()} will
     * return <code>false</code>.
     *
     * @param userDataResponse The <code>UserDataResponse</code>.
     */
    public JanrainAuthenticationToken(UserDataResponse userDataResponse) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principal = userDataResponse.getProfile().getIdentifier();
        this.userDataResponse = userDataResponse;
        setAuthenticated(false);
    }
    
    /**
     * Constructor used for an authentication response. The {@link
     * org.springframework.security.core.Authentication#isAuthenticated()} will
     * return <code>true</code>.
     *
     * @param principal The authenticated principal.
     * @param authorities The granted authorities.
     * @param userDataResponse The <code>UserDataResponse</code>.
     */
    public JanrainAuthenticationToken(Object principal, Collection<GrantedAuthority> authorities, UserDataResponse userDataResponse) {
        super(authorities);
        this.principal = principal;
        this.userDataResponse = userDataResponse;
        setAuthenticated(true);
    }
    
    /**
     * Returns the credentials.
     */
    public Object getCredentials() {
        return userDataResponse.getAccessCredentials();
    }
    
    /**
     * Returns the principal.
     */
    public Object getPrincipal() {
        return principal;
    }
    
    /**
     * Returns the user data Janrain Engage knows about the user signing into your website.
     */
    public UserDataResponse getUserDataResponse() {
        return userDataResponse;
    }
}
