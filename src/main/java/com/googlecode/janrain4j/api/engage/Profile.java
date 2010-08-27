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

import java.util.Date;

/**
 * A dictionary of fields forming the user's profile. This data may have been 
 * obtained through SREG, HCard, but is represented in the standard Portable 
 * Contacts schema.
 * 
 * @author Marcel Overdijk
 * @see <a href="http://rpxnow.com/docs#profile_data">Profile data documentation</a>
 * @since 1.0
 */
public class Profile {

    private String identifier = null;
    private String providerName = null;
    private String primaryKey = null;
    private String displayName = null;
    private String preferredUsername = null;
    private Name name = null;
    private String gender = null;
    private Date birthday = null;
    private String utcOffset = null;
    private String email = null;
    private String verifiedEmail = null;
    private String url = null;
    private String phoneNumber = null;
    private String photo = null;
    private Address address = null;
    private boolean limitedData = false;
    
    public Profile(String identifier) {
        this.identifier = identifier;
    }
    
    /**
     * Returns the user's OpenID URL. Use this value to sign the user in to 
     * your website. This field is always present.
     */
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * Sets the user's OpenID URL.
     * 
     * @param identifier The user's OpenID URL.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    /**
     * Returns the human-readable name of the authentication provider that was 
     * used for this authentication. For well-known providers, Janrain Engage 
     * sends values such as "Google", "Facebook", and "MySpace"; "Other" is 
     * sent for other providers. New provider names are added over time.
     */
    public String getProviderName() {
        return providerName;
    }
    
    /**
     * Sets the human-readable name of the authentication provider that was 
     * used for this authentication.
     * 
     * @param providerName The name of the provider.
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    
    /**
     * Returns the primary key of the user in your database. Only present if 
     * you are using the mapping API.
     * 
     * @see <a href="http://rpxnow.com/docs#mappings">Mappings</a>
     */
    public String getPrimaryKey() {
        return primaryKey;
    }
    
    /**
     * Sets the primary key of the user in your database.
     * 
     * @param primaryKey The primary key.
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    /**
     * Returns the name of the user, suitable for display to end-users.
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Sets the name of the user, suitable for display to end-users.
     * 
     * @param displayName The name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns the preferred username of the user on sites that ask for a 
     * username.
     */
    public String getPreferredUsername() {
        return preferredUsername;
    }
    
    /**
     * Sets the preferred username of the user on sites that ask for a username.
     * 
     * @param preferredUsername The preferred username.
     */
    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }
    
    /**
     * Returns the name of the user.
     * 
     * @see <a href="http://rpxnow.com/docs#profile_name">Profile data documentation</a>
     */
    public Name getName() {
        return name;
    }
    
    /**
     * Sets the name of the user.
     * 
     * @param name The name.
     */
    public void setName(Name name) {
        this.name = name;
    }
    
    /**
     * Returns the gender of the user. Canonical values are 'male', and 
     * 'female', but may be any value.
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Sets the gender of the user. Canonical values are 'male', and 
     * 'female', but may be any value.
     * 
     * @param gender The gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Returns the date of birth of the user. Year field may be 0000 if 
     * unavailable.
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * Sets the date of birth of the user.
     * 
     * @param birthday The date of birth.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * Returns the offset from UTC of user's current time zone, as of the time 
     * this response was returned. The value MUST conform to the offset portion 
     * of xs:dateTime, e.g. -08:00. Note that this value MAY change over time 
     * due to daylight saving time, and is thus meant to signify only the 
     * current value of the user's timezone offset.
     */
    public String getUtcOffset() {
        return utcOffset;
    }
    
    /**
     * Sets the offset from UTC of user's current time zone, as of the time 
     * this response was returned. The value MUST conform to the offset portion 
     * of xs:dateTime, e.g. -08:00.
     * 
     * @param utcOffset The offset.
     */
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
    
    /**
     * Returns the email address at which the user may be reached.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email address at which the user may be reached.
     * 
     * @param email The email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the email address at which the person may be reached.
     */
    public String getVerifiedEmail() {
        return verifiedEmail;
    }
    
    /**
     * Sets the email address at which the person may be reached.
     * 
     * @param verifiedEmail The email address.
     */
    public void setVerifiedEmail(String verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }
    
    /**
     * Returns the URL of a webpage relating to this person.
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Sets the URL of a webpage relating to this person.
     * 
     * @param url The URL of a webpage.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Returns the phone number at which the user may be reached.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Sets the phone number at which the user may be reached.
     * 
     * @param phoneNumber The phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Returns the URL to a photo (GIF/JPG/PNG) of the user.
     */
    public String getPhoto() {
        return photo;
    }
    
    /**
     * Sets the URL to a photo (GIF/JPG/PNG) of the user.
     * 
     * @param photo The URL to a photo.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    /**
     * Returns the address of the user.
     * 
     * @see <a href="http://rpxnow.com/docs#profile_address">Profile data documentation</a>
     */
    public Address getAddress() {
        return address;
    }
    
    /**
     * Sets the address of the user.
     * 
     * @param address The address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /**
     * Returns true if Janrain Engage was able to retrieve only limited public 
     * data from the user's profile (e.g., because the login session has 
     * expired or the user logged out from their account). If Janrain Engage 
     * succeeded in retrieving complete set of data, this field will be set to 
     * false.
     */
    public boolean hasLimitedData() {
        return limitedData;
    }
    
    /**
     * Sets if Janrain Engage was able to retrieve only limited public data 
     * from the user's profile (e.g., because the login session has expired or 
     * the user logged out from their account).
     * 
     * @param limitedData 'true' or 'false'. If 'true', Janrain Engage was able to retrieve only limited public data from the user's profile.
     */
    public void setLimitedData(boolean limitedData) {
        this.limitedData = limitedData;
    }
}
