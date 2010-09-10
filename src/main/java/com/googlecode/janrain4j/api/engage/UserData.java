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

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class UserData {

    private Profile profile = null;
    private AccessCredentials accessCredentials = null;
    private MergedPoco mergedPoco = null;
    
    UserData() {
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public AccessCredentials getAccessCredentials() {
        return accessCredentials;
    }
    
    void setAccessCredentials(AccessCredentials accessCredentials) {
        this.accessCredentials = accessCredentials;
    }
    
    public MergedPoco getMergedPoco() {
        return mergedPoco;
    }
    
    void setMergedPoco(MergedPoco mergedPoco) {
        this.mergedPoco = mergedPoco;
    }
}