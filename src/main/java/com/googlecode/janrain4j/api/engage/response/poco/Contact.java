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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.googlecode.janrain4j.api.engage.response.ContactsResponse;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * A dictionary containing the <a href="http://portablecontacts.net/">Portable Contacts</a> data.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see UserDataResponse
 * @see ContactsResponse
 */
@SuppressWarnings("serial")
public class Contact implements Serializable {

    private String id = null;
    private String displayName = null;
    private Name name = null;
    private String nickname = null;
    private Date published = null;
    private Date updated = null;
    private Date birthday = null;
    private Date anniversary = null;
    private String gender = null;
    private String note = null;
    private String preferredUsername = null;
    private String utcOffset = null;
    private boolean connected = false;
    
    private List<Email> emails = null;
    private List<Url> urls = null;
    private List<PhoneNumber> phoneNumbers = null;
    private List<InstantMessagingAddress> ims = null;
    private List<Photo> photos = null;
    private List<String> tags = null;
    private List<Relationship> relationships = null;
    private List<Address> addresses = null;
    private List<Organization> organizations = null;
    private List<Account> accounts = null;
    
    private Contact() {
    }
    
    public static Contact fromJSON(JSONObject json) {
        // TODO
        return null;
    }
    
    /**
     * Returns TODO
     */
    public String getId() {
        return id;
    }
    
    void setId(String id) {
        this.id = id;
    }
    
    /**
     * Returns TODO
     */
    public String getDisplayName() {
        return displayName;
    }
    
    void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns TODO
     */
    public Name getName() {
        return name;
    }
    
    void setName(Name name) {
        this.name = name;
    }
    
    /**
     * Returns TODO
     */
    public String getNickname() {
        return nickname;
    }
    
    void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * Returns TODO
     */
    public Date getPublished() {
        return published;
    }
    
    void setPublished(Date published) {
        this.published = published;
    }
    
    /**
     * Returns TODO
     */
    public Date getUpdated() {
        return updated;
    }
    
    void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    /**
     * Returns TODO
     */
    public Date getBirthday() {
        return birthday;
    }
    
    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * Returns TODO
     */
    public Date getAnniversary() {
        return anniversary;
    }
    
    void setAnniversary(Date anniversary) {
        this.anniversary = anniversary;
    }
    
    /**
     * Returns TODO
     */
    public String getGender() {
        return gender;
    }
    
    void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Returns TODO
     */
    public String getNote() {
        return note;
    }
    
    void setNote(String note) {
        this.note = note;
    }
    
    /**
     * Returns TODO
     */
    public String getPreferredUsername() {
        return preferredUsername;
    }
    
    void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }
    
    /**
     * Returns TODO
     */
    public String getUtcOffset() {
        return utcOffset;
    }
    
    void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
    
    /**
     * Returns TODO
     */
    public boolean isConnected() {
        return connected;
    }
    
    void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    /**
     * Returns TODO
     */
    public List<Email> getEmails() {
        return emails;
    }
    
    void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
    /**
     * Returns TODO
     */
    public List<Url> getUrls() {
        return urls;
    }
    
    void setUrls(List<Url> urls) {
        this.urls = urls;
    }
    
    /**
     * Returns TODO
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
    
    void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    
    /**
     * Returns TODO
     */
    public List<InstantMessagingAddress> getIms() {
        return ims;
    }
    
    void setInstantMessagingAddresses(List<InstantMessagingAddress> ims) {
        this.ims = ims;
    }
    
    /**
     * Returns TODO
     */
    public List<Photo> getPhotos() {
        return photos;
    }
    
    void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
    /**
     * Returns TODO
     */
    public List<String> getTags() {
        return tags;
    }
    
    void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    /**
     * Returns TODO
     */
    public List<Relationship> getRelationships() {
        return relationships;
    }
    
    void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
    
    /**
     * Returns TODO
     */
    public List<Address> getAddresses() {
        return addresses;
    }
    
    void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * Returns TODO
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }
    
    void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
    
    /**
     * Returns TODO
     */
    public List<Account> getAccounts() {
        return accounts;
    }
    
    void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
