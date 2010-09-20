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

import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONWriter;

/**
 * Property to be posted to the user's activity stream.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Activity
 */
public class Property {

    private String key = null;
    private PropertyValue value = null;
    
    /**
     * Create a new <code>Property</code> with a <code>String<code> value.
     * 
     * @param key The key.
     * @param value The value.
     */
    public Property(String key, String value) {
        this.key = key;
        this.value = new StringPropertyValue(value);
    }
    
    /**
     * Create a new <code>Property</code> with a text and href value.
     * 
     * @param key The key.
     * @param text The text.
     * @param href The href.
     */
    public Property(String key, String text, String href) {
        this.key = key;
        this.value = new LinkPropertyValue(text, href);
    }
    
    public void writeJSON(JSONWriter writer) throws JSONException {
        writer.key(key);
        value.writeJSON(writer);
    }
    
    /**
     * Returns the key of the property.
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Sets the key of the property.
     * 
     * @param key The key.
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * Returns the value of the property.
     */
    public PropertyValue getValue() {
        return value;
    }
    
    /**
     * Sets the value of the property.
     * 
     * @param value The value.
     */
    public void setValue(PropertyValue value) {
        this.value = value;
    }
}
