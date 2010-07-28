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
import java.util.Date;
import java.util.List;

public interface EngageService {

    public Profile authInfo(String token);
    
    public Profile authInfo(String token, boolean extended);
    
    public List<Contact> getContacts();
    
    public void setStatus(String identifier, String status);
    
    public void map(String identifier, String primaryKey);
    
    public void map(String identifier, String primaryKey, boolean overwrite);
    
    public void unmap(String primaryKey);
    
    public void unmap(String primaryKey, boolean unlink);
    
    public void unmap(String identifier, String primaryKey);
    
    public void unmap(String identifier, String primaryKey, boolean unlink);
    
    public List<String> mappings(String primaryKey);
    
    public List<Mapping> allMappings();
    
    public void activity(String identifier, Activity activity);
    
    public URL analytics(Date start, Date end);
    
    public void setAuthProviders(List<String> providers);
}
