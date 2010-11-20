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
 * <code>UserDetails</code> implementation whose properties are based on the 
 * Janrain {@link UserDataResponse} response. Developers may use this class 
 * directly or subclass it.
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
    
    /**
     * Returns the user data Janrain Engage knows about the user signing into your website.
     * 
     * @return The user data with Janrain Engage knows about the user signing into your website.
     */
    public UserDataResponse getUserDataResponse() {
        return userDataResponse;
    }
    
    /**
     * Returns a dictionary of fields forming the user's profile.
     * 
     * @return The user's profile.
     */
    public Profile getProfile() {
        return userDataResponse.getProfile();
    }
    
    /**
     * Returns the user's authorization credentials if the user logged in with 
     * a provider that allows account access after authentication.
     * 
     * @return The access credentials or <code>null</code> if not found in response.
     */
    public AccessCredentials getAccessCredentials() {
        return userDataResponse.getAccessCredentials();
    }
    
    /**
     * Returns the merged <a href="http://portablecontacts.net/">Portable Contacts</a> 
     * data if the extended request argument was 'true' and extended profile 
     * data were available.
     * 
     * @return The merged Portable Contacts data or <code>null</code> if not found in response.
     */
    public Contact getMergedPoco() {
        return userDataResponse.getMergedPoco();
    }
    
    /**
     * Returns the user's friends' identifiers if the extended request argument 
     * was 'true' and friends data were available.
     * 
     * @return The user's friends' identifiers or <code>null</code> if not found in response.
     */
    public List<String> getFriends() {
        return userDataResponse.getFriends();
    }
}
