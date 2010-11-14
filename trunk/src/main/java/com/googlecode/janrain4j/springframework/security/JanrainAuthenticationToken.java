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

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
@SuppressWarnings("serial")
public class JanrainAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal = null;
    private UserDataResponse userData = null;
    
    public JanrainAuthenticationToken(UserDataResponse userData) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principal = userData.getProfile().getIdentifier();
        this.userData = userData;
        setAuthenticated(false);
    }
    
    public Object getCredentials() {
        return userData.getAccessCredentials();
    }
    
    public Object getPrincipal() {
        return principal;
    }
    
    public UserDataResponse getUserData() {
        return userData;
    }
}
