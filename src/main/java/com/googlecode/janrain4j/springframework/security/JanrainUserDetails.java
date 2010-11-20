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
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.accesscredentials.AccessCredentials;
import com.googlecode.janrain4j.api.engage.response.poco.Contact;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.1
 */
@SuppressWarnings("serial")
public class JanrainUserDetails extends User {

    private UserDataResponse userDataResponse = null;
    
    public JanrainUserDetails(UserDataResponse userDataResponse) {
        this(userDataResponse, AuthorityUtils.NO_AUTHORITIES);
        this.userDataResponse = userDataResponse;
    }
    
    public JanrainUserDetails(UserDataResponse userDataResponse, Collection<GrantedAuthority> authorities) {
        super(userDataResponse.getProfile().getIdentifier(), "unused", true, true, true, true, authorities);
        Assert.notNull(userDataResponse, "userDataResponse cannot be null");
        this.userDataResponse = userDataResponse;
    }
    
    public UserDataResponse getUserDataResponse() {
        return userDataResponse;
    }
    
    public Profile getProfile() {
        return userDataResponse.getProfile();
    }
    
    public AccessCredentials getAccessCredentials() {
        return userDataResponse.getAccessCredentials();
    }
    
    public Contact getMergedPoco() {
        return userDataResponse.getMergedPoco();
    }
    
    public List<String> getFriends() {
        return userDataResponse.getFriends();
    }
    
    public boolean isLimitedData() {
        return userDataResponse.isLimitedData();
    }
}
