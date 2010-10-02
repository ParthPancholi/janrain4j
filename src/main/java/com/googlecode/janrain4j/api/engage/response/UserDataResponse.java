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
package com.googlecode.janrain4j.api.engage.response;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.response.accesscredentials.AccessCredentials;
import com.googlecode.janrain4j.api.engage.response.poco.Contact;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.json.JSONArray;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * The <code>UserDataResponse</code> contains all the information Janrain Engage 
 * knows about the user signing into your website.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
@SuppressWarnings("serial")
public class UserDataResponse extends AbstractEngageResponse {

    private Profile profile = null;
    private JSONObject profileJSONObject = null;
    private AccessCredentials accessCredentials = null;
    private JSONObject accessCredentialsJSONObject = null;
    private Contact mergedPoco = null;
    private JSONObject mergedPocoJSONObject = null;
    private List<String> friends = null;
    private JSONArray friendsJSONArray = null;
    private boolean limitedData = false;
    
    public UserDataResponse(String json) {
        super(json);
        try {
            JSONObject rsp = getResponseAsJSONObject();
            
            // Profile
            JSONObject rspProfile = rsp.getJSONObject("profile");
            profileJSONObject = rspProfile;
            profile = Profile.fromJSON(rspProfile);
            
            // Access Credentials
            JSONObject rspAccessCredentials = rsp.optJSONObject("accessCredentials");
            if (rspAccessCredentials != null) {
                accessCredentialsJSONObject = rspAccessCredentials;
                accessCredentials = AccessCredentials.fromJSON(rspAccessCredentials);
            }
            
            // Merged Poco
            JSONObject rspMergedPoco = rsp.optJSONObject("merged_poco");
            if (rspMergedPoco != null) {
                mergedPocoJSONObject = rspMergedPoco;
                mergedPoco = Contact.fromJSON(rspMergedPoco);
            }
            
            // Friends
            JSONArray rspFriends = rsp.optJSONArray("friends");
            friendsJSONArray = rspFriends;
            if (rspFriends != null) {
                friends = new ArrayList<String>();
                for (int i = 0; i < rspFriends.length(); i++) {
                    try {
                        friends.add(rspFriends.getString(i));
                    }
                    catch (JSONException e) {
                        throw new EngageFailureException("Unexpected JSON error", json, e);
                    }
                }
            }
            
            // Limited Data
            limitedData = rsp.optBoolean("limitedData", false);
        }
        catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", json, e);
        }
    }
    
    /**
     * Returns a dictionary of fields forming the user's profile.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Returns the user's profile as a <code>JSONObject</code>.
     */
    public JSONObject getProfileAsJSONObject() {
        return profileJSONObject;
    }
    
    /**
     * Returns the user's authorization credentials if the user logged in with 
     * a provider that allows account access after authentication.
     */
    public AccessCredentials getAccessCredentials() {
        return accessCredentials;
    }
    
    /**
     * Returns the user's authorization credentials as a <code>JSONObject</code>.
     */
    public JSONObject getAccessCredentialsAsJSONObject() {
        return accessCredentialsJSONObject;
    }
    
    /**
     * Returns the merged <a href="http://portablecontacts.net/">Portable Contacts</a> 
     * data if the extended request argument was 'true' and extended profile 
     * data were available.
     */
    public Contact getMergedPoco() {
        return mergedPoco;
    }
    
    /**
     * Returns the merged <a href="http://portablecontacts.net/">Portable Contacts</a> 
     * data as a <code>JSONObject</code>.
     */
    public JSONObject getMergedPocoAsJSONObject() {
        return mergedPocoJSONObject;
    }
    
    /**
     * Returns the user's friends' identifiers if the extended request argument 
     * was 'true' and friends data were available.
     */
    public List<String> getFriends() {
        return friends;
    }
    
    /**
     * Returns the user's friends' identifiers as a <code>JSONArray</code>.
     */
    public JSONArray getFriendsAsJSONArray() {
        return friendsJSONArray;
    }
    
    /**
     * Returns true if Janrain Engage was able to retrieve only limited public 
     * data from the user's profile (e.g., because the login session has 
     * expired or the user logged out from their account). If Janrain Engage 
     * succeeded in retrieving complete set of data, this field will be set to 
     * false. Used only with Facebook.
     */
    public boolean isLimitedData() {
        return limitedData;
    }
}
