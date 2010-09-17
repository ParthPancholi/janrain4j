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
package com.googlecode.janrain4j.api.engage.response;

import java.util.List;

import com.googlecode.janrain4j.api.engage.EngageService;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see EngageService#getContacts(String)
 */
public class ContactsResponse extends AbstractEngageResponse {

    private static final long serialVersionUID = -7619050740310590636L;
    
    private List<Contact> contacts = null;
    
    public ContactsResponse(String jsonResponse) {
        super(jsonResponse);
        // TODO
    }
    
    /**
     * TODO
     */
    public List<Contact> getContacts() {
        return contacts;
    }
}
