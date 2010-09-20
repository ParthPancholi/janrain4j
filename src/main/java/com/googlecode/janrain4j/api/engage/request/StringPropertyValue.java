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
 * <code>PropertyValue</code> with a <code>String<code> value.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Property
 */
public class StringPropertyValue implements PropertyValue {

    private String value = null;
    
    /**
     * Create a new <code>StringPropertyValue</code>.
     * 
     * @param value The value.
     */
    public StringPropertyValue(String value) {
        this.value = value;
    }
    
    public void writeJSON(JSONWriter writer) throws JSONException {
        writer.value(value);
    }
    
    /**
     * Returns the <code>String</code> value. 
     */
    public String getValue() {
        return value;
    }
    
    /**
     * Sets the <code>String</code> value.
     * 
     * @param value The value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
