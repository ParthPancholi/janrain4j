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
 * URL of a web page related to the <code>Contact</code>.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Contact
 */
@SuppressWarnings("serial")
public class Url extends AbstractPluralField {

    public static final String TYPE_BLOG = "blog";
    public static final String TYPE_PROFILE = "profile";
    
    private Url() {
    }
    
    public static Url fromJSON(JSONObject json) {
        // TODO
        return null;
    }
    
    /**
     * Returns true if the type of this instance is blog. 
     */
    public boolean isBlog() {
        return TYPE_BLOG.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is profile. 
     */
    public boolean isProfile() {
        return TYPE_PROFILE.equalsIgnoreCase(type);
    }
}
