/* Copyright 2010 Marcel Overdijk

import java.net.HttpURLConnection;

import java.net.HttpURLConnection;

import java.sql.Connection;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.googlecode.janrain4j.api.engage.response.poco.Contact;
import com.googlecode.janrain4j.json.JSONObject;

public class ContactsResponseTest {

    private String json = null;
    private ContactsResponse response = null;
    
    // TODO response seems to include paging information... ticket created
    
    @Test
    public void testContactsResponse() throws Exception {
        
        json = 
            "{\n" +
            "  \"response\": {\n" +
            "    \"startIndex\": 1,\n" +
            "    \"itemsPerPage\": 5,\n" +
            "    \"entry\": [\n" +
            "      {\n" +
            "        \"displayName\": \"Bob Johnson\",\n" +
            "        \"emails\": [\n" +
            "          {\n" +
            "            \"type\": \"other\",\n" +
            "            \"value\": \"bob@example.com\",\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"displayName\": \"Cindy Smith\",\n" +
            "        \"emails\": [\n" +
            "          {\n" +
            "            \"type\": \"other\",\n" +
            "            \"value\": \"cindy.smith@example.com\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"displayName\": \"Fred Williams\",\n" +
            "        \"emails\": [\n" +
            "          {\n" +
            "            \"type\": \"other\",\n" +
            "            \"value\": \"fred.williams@example.com\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    ],\n" +
            "    \"totalResults\": 5\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        response = new ContactsResponse(json);
        
        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());
        
        List<Contact> contacts = response.getContacts();
        assertNotNull(contacts);
        assertEquals(3, contacts.size());
        assertEquals("Bob Johnson", contacts.get(0).getDisplayName());
        assertEquals("Cindy Smith", contacts.get(1).getDisplayName());
        assertEquals("Fred Williams", contacts.get(2).getDisplayName());
    }
}
