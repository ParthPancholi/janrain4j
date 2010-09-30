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
public class InstantMessagingAddress extends AbstractPluralField {

    public static final String TYPE_AIM = "aim";
    public static final String TYPE_GTALK = "gtalk";
    public static final String TYPE_ICQ = "icq";
    public static final String TYPE_XMPP = "xmpp";
    public static final String TYPE_MSN = "msn";
    public static final String TYPE_SKYPE = "skype";
    public static final String TYPE_QQ = "qq";
    public static final String TYPE_YAHOO = "yahoo";
    
    private InstantMessagingAddress() {
    }
    
    public static InstantMessagingAddress fromJSON(JSONObject json) {
        // TODO
        return null;
    }
    
    /**
     * Returns true if the type of this instance is aim. 
     */
    public boolean isAim() {
        return TYPE_AIM.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is gtalk. 
     */
    public boolean isGtalk() {
        return TYPE_GTALK.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is icq. 
     */
    public boolean isIcq() {
        return TYPE_ICQ.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is xmpp. 
     */
    public boolean isXmpp() {
        return TYPE_XMPP.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is msn. 
     */
    public boolean isMsn() {
        return TYPE_MSN.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is skype. 
     */
    public boolean isSkype() {
        return TYPE_SKYPE.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is qq. 
     */
    public boolean isQq() {
        return TYPE_QQ.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is yahoo. 
     */
    public boolean isYahoo() {
        return TYPE_YAHOO.equalsIgnoreCase(type);
    }
}
