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
import com.googlecode.janrain4j.api.engage.response.poco.Contact;

/**
 * The <code>ContactsResponse</code> contains all list of contacts for an 
 * indentifier in the <a href="http://portablecontacts.net/">Portable Contacts</a> format.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see EngageService#getContacts(String)
 */
@SuppressWarnings("serial")
public class ContactsResponse extends AbstractEngageResponse {

    private List<Contact> contacts = null;
    
    public ContactsResponse(String json) {
        super(json);
        // TODO
    }
    
    /**
     * TODO
     */
    public List<Contact> getContacts() {
        return contacts;
    }
}
