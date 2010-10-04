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
 * URL of a photo of the <code>Contact</code>.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Contact
 */
@SuppressWarnings("serial")
public class Photo extends AbstractPluralField {

    protected Photo() {
    }
    
    public static Photo fromJSON(JSONObject json) {
        Photo photo = new Photo();
        photo.setValue(json.optString("value"));
        photo.setType(json.optString("type"));
        photo.setPrimary(json.optBoolean("primary", false));
        return photo;
    }
}
