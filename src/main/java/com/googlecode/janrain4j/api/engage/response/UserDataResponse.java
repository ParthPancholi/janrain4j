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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
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
public class UserDataResponse extends AbstractEngageResponse {

    private static final long serialVersionUID = 236447883427917238L;
    
    private Profile profile = null;
    private JSONObject profileJsonObject = null;
    private AccessCredentials accessCredentials = null;
    private JSONObject accessCredentialsJsonObject = null;
    private MergedPoco mergedPoco = null;
    private List<String> friends = null;
    private JSONArray friendsJsonArray = null;
    private boolean limitedData = false;
    
    public UserDataResponse(String jsonResponse) {
        super(jsonResponse);
        try {
            JSONObject rsp = getResponseAsJSONObject();
            
            // Profile
            JSONObject rspProfile = rsp.getJSONObject("profile");
            profileJsonObject = rspProfile;
            profile = new Profile();
            profile.setIdentifier(rspProfile.getString("identifier"));
            profile.setProviderName(rspProfile.getString("providerName"));
            profile.setPrimaryKey(rspProfile.optString("primaryKey", null));
            profile.setDisplayName(rspProfile.optString("displayName", null));
            profile.setPreferredUsername(rspProfile.optString("preferredUsername", null));
            JSONObject rspName = rspProfile.optJSONObject("name");
            if (rspName != null) {
                Name name = new Name();
                name.setFormatted(rspName.optString("formatted", null));
                name.setFamilyName(rspName.optString("familyName", null));
                name.setGivenName(rspName.optString("givenName", null));
                name.setMiddleName(rspName.optString("middleName", null));
                name.setHonorificPrefix(rspName.optString("honorificPrefix", null));
                name.setHonorificSuffix(rspName.optString("honorificSuffix", null));
                profile.setName(name);
            }
            profile.setGender(rspProfile.optString("gender", null));
            String birthday = rspProfile.optString("birthday", null);
            if (birthday != null && birthday.length() > 0) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    profile.setBirthday(dateFormatter.parse(birthday));
                }
                catch (ParseException ignore) {
                }
            }
            profile.setUtcOffset(rspProfile.optString("utcOffset", null));
            profile.setEmail(rspProfile.optString("email", null));
            profile.setEmail(rspProfile.optString("email", null));
            profile.setVerifiedEmail(rspProfile.optString("verifiedEmail", null));
            profile.setUrl(rspProfile.optString("url", null));
            profile.setPhoneNumber(rspProfile.optString("phoneNumber", null));
            profile.setPhoto(rspProfile.optString("photo", null));
            JSONObject rspAddress = rspProfile.optJSONObject("address");
            if (rspAddress != null) {
                Address address = new Address();
                address.setFormatted(rspAddress.optString("formatted", null));
                address.setStreetAddress(rspAddress.optString("streetAddress", null));
                address.setLocality(rspAddress.optString("locality", null));
                address.setRegion(rspAddress.optString("region", null));
                address.setPostalCode(rspAddress.optString("postalCode", null));
                address.setCountry(rspAddress.optString("country", null));
                profile.setAddress(address);
            }
            
            // Access Credentials
            JSONObject rspAccessCredentials = rsp.optJSONObject("accessCredentials");
            accessCredentialsJsonObject = rspAccessCredentials;
            if (rspAccessCredentials != null) {
                accessCredentials = new AccessCredentials();
                accessCredentials.setType(rspAccessCredentials.optString("type"));
                accessCredentials.setOauthToken(rspAccessCredentials.optString("oauthToken"));
                accessCredentials.setOauthTokenSecret(rspAccessCredentials.optString("oauthTokenSecret"));
                accessCredentials.setUid(rspAccessCredentials.optString("uid"));
                accessCredentials.setAccessToken(rspAccessCredentials.optString("accessToken"));
                accessCredentials.setExpires(rspAccessCredentials.optLong("expires"));
                accessCredentials.setEact(rspAccessCredentials.optString("eact"));
            }
            
            // Merged Poco
            // TODO
            
            // Friends
            JSONArray rspFriends = rsp.optJSONArray("friends");
            friendsJsonArray = rspFriends;
            if (rspFriends != null) {
                friends = new ArrayList<String>();
                for (int i = 0; i < rspFriends.length(); i++) {
                    try {
                        friends.add(rspFriends.getString(i));
                    }
                    catch (JSONException e) {
                        throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
                    }
                }
            }
            
            // Limited Data
            limitedData = rsp.optBoolean("limitedData", false);
        }
        catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
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
        return profileJsonObject;
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
        return accessCredentialsJsonObject;
    }
    
    /**
     * Returns the merged <a href="http://portablecontacts.net/">Portable Contacts</a> 
     * data if the extended request argument was 'true' and extended profile 
     * data were available.
     */
    public MergedPoco getMergedPoco() {
        return mergedPoco;
    }
    
    // TODO get merged poco as JSONObject or JSONArray
    
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
        return friendsJsonArray;
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
