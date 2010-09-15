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
package com.googlecode.janrain4j.api.engage;

import java.io.Serializable;

/**
 * If the user signed in with a provider that allows account access after 
 * authentication, this will be present and contain the user's authorization 
 * credentials.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class AccessCredentials implements Serializable {

    private static final long serialVersionUID = -1325646539192011350L;
    
    public static final String TYPE_OAUTH = "OAuth";
    public static final String TYPE_FACEBOOK = "Facebook";
    public static final String TYPE_WINDOWS_LIVE = "WindowsLive";
    
    private String type = null;
    
    // OAuth access credentials
    private String oauthToken = null;
    private String oauthTokenSecret = null;
    
    // Facebook access credentials
    private String uid = null;
    private String accessToken = null;
    private Long expires = null;
    
    // Windows Live credentials
    private String eact = null;
    
    AccessCredentials() {
    }
    
    /**
     * Returns true if the signed in user is authenticated using OAuth. 
     */
    public boolean isOauth() {
        return TYPE_OAUTH.equals(type);
    }
    
    /**
     * Returns true if the signed in user is authenticated using Facebook.
     */
    public boolean isFacebook() {
        return TYPE_FACEBOOK.equals(type);
    }
    
    /**
     * Returns true if the signed in user is authenticated using Windows Live.
     */
    public boolean isWindowsLive() {
        return TYPE_WINDOWS_LIVE.equals(type);
    }
    
    /**
     * Returns the authentication type.
     */
    public String getType() {
        return type;
    }
    
    void setType(String type) {
        this.type = type;
    }
    
    /**
     * Returns the OAuth token (only available with OAuth authentication).
     */
    public String getOauthToken() {
        return oauthToken;
    }
    
    void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }
    
    /**
     * Returns the OAuth token secret (only available with OAuth authentication).
     */
    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }
    
    void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }
    
    /**
     * Returns the UID (only available with Facebook authentication).
     */
    public String getUid() {
        return uid;
    }
    
    void setUid(String uid) {
        this.uid = uid;
    }
    
    /**
     * Returns the access token (only available with Facebook authentication).
     */
    public String getAccessToken() {
        return accessToken;
    }
    
    void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    /**
     * Returns the expires Unix timestamp (only available with Facebook authentication).
     */
    public Long getExpires() {
        return expires;
    }
    
    void setExpires(Long expires) {
        this.expires = expires;
    }
    
    /**
     * Returns the eact token (only available with Windows Live authentication).
     */
    public String getEact() {
        return eact;
    }
    
    void setEact(String eact) {
        this.eact = eact;
    }
}
