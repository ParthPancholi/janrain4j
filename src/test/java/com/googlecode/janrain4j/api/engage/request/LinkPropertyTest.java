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
package com.googlecode.janrain4j.api.engage.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.json.JSONStringer;

public class LinkPropertyTest {

    private LinkProperty property = null;
    
    JSONStringer json = null;
    
    private String key = "my-key";
    private String text = "my-text";
    private String href = "my-href";
    
    @Before
    public void setUp() {
        json = new JSONStringer();
    }
    
    @Test
    public void testLinkProperty() throws Exception {
        property = new LinkProperty(key, text, href);
        json.object();
        property.writeJSON(json);
        json.endObject();
        assertEquals("{\"my-key\":{\"text\":\"my-text\",\"href\":\"my-href\"}}", json.toString());
    }
}
