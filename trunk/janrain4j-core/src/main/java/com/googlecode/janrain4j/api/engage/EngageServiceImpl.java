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

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Marcel Overdijk
 */
public class EngageServiceImpl implements EngageService {

    EngageServiceConfig config = null;
    
    EngageServiceImpl(EngageServiceConfig config) {
        this.config = config;
    }
    
    public Profile authInfo(String token) {
        return authInfo(token, false);
    }
    
    public Profile authInfo(String token, boolean extended) {
        // TODO
        Profile profile = new Profile();
        return profile;
    }
    
    public List<Contact> getContacts() {
        // TODO
        List<Contact> contacts = new ArrayList<Contact>();
        return contacts;
    }
    
    public void setStatus(String identifier, String status) {
        // TODO
    }
    
    public void map(String identifier, String primaryKey) {
        map(identifier, primaryKey, true);
    }
    
    public void map(String identifier, String primaryKey, boolean overwrite) {
        // TODO
    }

    public void unmap(String primaryKey) {
        unmap(primaryKey, false);
    }

    public void unmap(String primaryKey, boolean unlink) {
        unmap(null, true, primaryKey, unlink);
    }

    public void unmap(String identifier, String primaryKey) {
        unmap(identifier, primaryKey, false);
    }

    public void unmap(String identifier, String primaryKey, boolean unlink) {
        unmap(identifier, false, primaryKey, unlink);
    }
    
    protected void unmap(String identifier, boolean allIdentifiers, String primaryKey, boolean unlink) {
        // TODO
    }
    
    public List<String> mappings(String primaryKey) {
        // TODO
        List<String> mappings = new ArrayList<String>();
        return mappings;
    }
    
    public List<Mapping> allMappings() {
        // TODO
        List<Mapping> mappings = new ArrayList<Mapping>();
        return mappings;
    }
    
    public void activity(String identifier, Activity activity) {
        // TODO
    }
    
    public URL analytics(Date start, Date end) {
        // TODO
        return null;
    }
    
    public void setAuthProviders(List<String> providers) {
        // TODO
    }
}
