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
 * Instant messaging address for the <code>Contact</code>.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Contact
 */
@SuppressWarnings("serial")
public class IM extends AbstractPluralField {

    public static final String TYPE_AIM = "aim";
    public static final String TYPE_GOOGLE_TALK = "gtalk";
    public static final String TYPE_ICQ = "icq";
    public static final String TYPE_XMPP = "xmpp";
    public static final String TYPE_MSN = "msn";
    public static final String TYPE_SKYPE = "skype";
    public static final String TYPE_QQ = "qq";
    public static final String TYPE_YAHOO = "yahoo";
    
    protected IM() {
    }
    
    public static IM fromJSON(JSONObject json) {
        IM im = new IM();
        im.setValue(json.optString("value"));
        im.setType(json.optString("type"));
        im.setPrimary(json.optBoolean("primary", false));
        return im;
    }
    
    /**
     * Returns true if the type of this instance is AIM. 
     */
    public boolean isAIM() {
        return TYPE_AIM.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is Google Talk. 
     */
    public boolean isGoogleTalk() {
        return TYPE_GOOGLE_TALK.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is ICQ. 
     */
    public boolean isICQ() {
        return TYPE_ICQ.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is XMPP. 
     */
    public boolean isXMPP() {
        return TYPE_XMPP.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is MSN. 
     */
    public boolean isMSN() {
        return TYPE_MSN.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is Skype. 
     */
    public boolean isSkype() {
        return TYPE_SKYPE.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is QQ. 
     */
    public boolean isQQ() {
        return TYPE_QQ.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is Yahoo. 
     */
    public boolean isYahoo() {
        return TYPE_YAHOO.equalsIgnoreCase(type);
    }
}
