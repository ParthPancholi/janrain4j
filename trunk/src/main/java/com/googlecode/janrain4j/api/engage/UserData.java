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
package com.googlecode.janrain4j.api.engage;

import java.io.Serializable;
import java.util.List;

/**
 * The <code>UserData</code> contains all the information Janrain Engage knows 
 * about the user signing into your website.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class UserData implements Serializable {

    private static final long serialVersionUID = -1963449966616725840L;
    
    private Profile profile = null;
    private AccessCredentials accessCredentials = null;
    private MergedPoco mergedPoco = null;
    private List<String> friends = null;
    
    UserData() {
    }
    
    /**
     * Returns a dictionary of fields forming the user's profile.
     */
    public Profile getProfile() {
        return profile;
    }
    
    void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    /**
     * Returns the user's authorization credentials if the user logged in with 
     * a provider that allows account access after authentication.
     */
    public AccessCredentials getAccessCredentials() {
        return accessCredentials;
    }
    
    void setAccessCredentials(AccessCredentials accessCredentials) {
        this.accessCredentials = accessCredentials;
    }
    
    /**
     * Returns the merged <a href="http://portablecontacts.net/">Portable Contacts</a> 
     * data if the extended request argument was 'true' and extended profile 
     * data were available.
     */
    public MergedPoco getMergedPoco() {
        return mergedPoco;
    }
    
    void setMergedPoco(MergedPoco mergedPoco) {
        this.mergedPoco = mergedPoco;
    }
    
    /**
     * Returns the user's friends' identifiers if the extended request argument 
     * was 'true' and friends data were available.
     */
    public List<String> getFriends() {
        return friends;
    }
    
    void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
