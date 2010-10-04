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
package com.googlecode.janrain4j.api.engage.response.poco;

import com.googlecode.janrain4j.json.JSONObject;

/**
 * Phone number for the <code>Contact</code>.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Contact
 */
@SuppressWarnings("serial")
public class PhoneNumber extends AbstractPluralField {

    public static final String TYPE_MOBILE = "mobile";
    public static final String TYPE_FAX = "fax";
    public static final String TYPE_PAGER = "pager";
    
    protected PhoneNumber() {
    }
    
    public static PhoneNumber fromJSON(JSONObject json) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setValue(json.optString("value"));
        phoneNumber.setType(json.optString("type"));
        phoneNumber.setPrimary(json.optBoolean("primary", false));
        return phoneNumber;
    }
    
    /**
     * Returns true if the type of this instance is mobile. 
     */
    public boolean isMobile() {
        return TYPE_MOBILE.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is fax. 
     */
    public boolean isFax() {
        return TYPE_FAX.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is pager. 
     */
    public boolean isPager() {
        return TYPE_PAGER.equalsIgnoreCase(type);
    }
}
