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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.janrain4j.api.engage.response.ContactsResponse;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.json.JSONArray;
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

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    
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
    private List<IM> ims = null;
    private List<Photo> photos = null;
    private List<String> tags = null;
    private List<Relationship> relationships = null;
    private List<Address> addresses = null;
    private List<Organization> organizations = null;
    private List<Account> accounts = null;
    
    protected Contact() {
    }
    
    public static Contact fromJSON(JSONObject json) {
        Contact contact = new Contact();
        contact.setId(json.optString("id", null));
        contact.setDisplayName(json.optString("displayName", null));
        contact.setName(Name.fromJSON(json.optJSONObject("name")));
        contact.setNickname(json.optString("nickname", null));
        contact.setPublished(parseDateTime(json.optString("published", null)));
        contact.setUpdated(parseDateTime(json.optString("updated", null)));
        contact.setBirthday(parseDate(json.optString("birthday", null)));
        contact.setAnniversary(parseDate(json.optString("anniversary", null)));
        contact.setGender(json.optString("gender", null));
        contact.setNote(json.optString("note", null));
        contact.setPreferredUsername(json.optString("preferredUsername", null));
        contact.setUtcOffset(json.optString("utcOffset", null));
        contact.setConnected(json.optBoolean("connected"));
        
        JSONArray emailsJSONArray = json.optJSONArray("emails");
        if (emailsJSONArray != null) {
            List<Email> emails = new ArrayList<Email>();
            for (int i = 0; i < emailsJSONArray.length(); i++) {
                emails.add(Email.fromJSON(emailsJSONArray.optJSONObject(i)));
            }
            contact.setEmails(emails);
        }
        
        JSONArray urlsJSONArray = json.optJSONArray("urls");
        if (urlsJSONArray != null) {
            List<Url> urls = new ArrayList<Url>();
            for (int i = 0; i < urlsJSONArray.length(); i++) {
                urls.add(Url.fromJSON(urlsJSONArray.optJSONObject(i)));
            }
            contact.setUrls(urls);
        }
        
        JSONArray phoneNumbersJSONArray = json.optJSONArray("phoneNumbers");
        if (phoneNumbersJSONArray != null) {
            List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
            for (int i = 0; i < phoneNumbersJSONArray.length(); i++) {
                phoneNumbers.add(PhoneNumber.fromJSON(phoneNumbersJSONArray.optJSONObject(i)));
            }
            contact.setPhoneNumbers(phoneNumbers);
        }
        
        JSONArray imsJSONArray = json.optJSONArray("ims");
        if (imsJSONArray != null) {
            List<IM> ims = new ArrayList<IM>();
            for (int i = 0; i < imsJSONArray.length(); i++) {
                ims.add(IM.fromJSON(imsJSONArray.optJSONObject(i)));
            }
            contact.setIms(ims);
        }
        
        JSONArray photosJSONArray = json.optJSONArray("photos");
        if (photosJSONArray != null) {
            List<Photo> photos = new ArrayList<Photo>();
            for (int i = 0; i < photosJSONArray.length(); i++) {
                photos.add(Photo.fromJSON(photosJSONArray.optJSONObject(i)));
            }
            contact.setPhotos(photos);
        }
        
        JSONArray tagsJSONArray = json.optJSONArray("tags");
        if (tagsJSONArray != null) {
            List<String> tags = new ArrayList<String>();
            for (int i = 0; i < tagsJSONArray.length(); i++) {
                tags.add(tagsJSONArray.optString(i));
            }
            contact.setTags(tags);
        }
        
        JSONArray relationshipsJSONArray = json.optJSONArray("relationships");
        if (relationshipsJSONArray != null) {
            List<Relationship> relationships = new ArrayList<Relationship>();
            for (int i = 0; i < relationshipsJSONArray.length(); i++) {
                Relationship relationship = new Relationship();
                relationship.setValue(relationshipsJSONArray.optString(i));
                relationships.add(relationship);
            }
            contact.setRelationships(relationships);
        }
        
        JSONArray addressesJSONArray = json.optJSONArray("addresses");
        if (addressesJSONArray != null) {
            List<Address> addresses = new ArrayList<Address>();
            for (int i = 0; i < addressesJSONArray.length(); i++) {
                addresses.add(Address.fromJSON(addressesJSONArray.optJSONObject(i)));
            }
            contact.setAddresses(addresses);
        }
        
        JSONArray organizationsJSONArray = json.optJSONArray("organizations");
        if (organizationsJSONArray != null) {
            List<Organization> organizations = new ArrayList<Organization>();
            for (int i = 0; i < organizationsJSONArray.length(); i++) {
                organizations.add(Organization.fromJSON(organizationsJSONArray.optJSONObject(i)));
            }
            contact.setOrganizations(organizations);
        }
        
        JSONArray accountsJSONArray = json.optJSONArray("accounts");
        if (accountsJSONArray != null) {
            List<Account> accounts = new ArrayList<Account>();
            for (int i = 0; i < accountsJSONArray.length(); i++) {
                accounts.add(Account.fromJSON(accountsJSONArray.optJSONObject(i)));
            }
            contact.setAccounts(accounts);
        }
        
        return contact;
    }
    
    private static Date parseDate(String date) {
        if (date != null && date.length() > 0) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            try {
                return dateFormatter.parse(date);
            }
            catch (ParseException ignore) {
            }
        }
        return null;
    }
    
    private static Date parseDateTime(String dateTime) {
        if (dateTime != null && dateTime.length() > 0) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_PATTERN);
            try {
                return dateFormatter.parse(dateTime);
            }
            catch (ParseException ignore) {
            }
        }
        return null;
    }
    
    /**
     * Returns the unique identifier for the contact.
     */
    public String getId() {
        return id;
    }
    
    void setId(String id) {
        this.id = id;
    }
    
    /**
     * Returns the name of the contact, suitable for display to end-users.
     */
    public String getDisplayName() {
        return displayName;
    }
    
    void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns the broken-out components and fully formatted version of the 
     * contact's real name.
     */
    public Name getName() {
        return name;
    }
    
    void setName(Name name) {
        this.name = name;
    }
    
    /**
     * Returns the casual way to address the Contact in real life, e.g. "Bob" 
     * or "Bobby" instead of "Robert".
     */
    public String getNickname() {
        return nickname;
    }
    
    void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * Returns the date the contact was first added to the user's address book 
     * or friends list (i.e. the creation date of this entry).
     */
    public Date getPublished() {
        return published;
    }
    
    void setPublished(Date published) {
        this.published = published;
    }
    
    /**
     * Returns the most recent date the details of the contact were updated 
     * (i.e. the modified date of this entry).
     */
    public Date getUpdated() {
        return updated;
    }
    
    void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    /**
     * Returns the birthday of the contact.
     */
    public Date getBirthday() {
        return birthday;
    }
    
    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * Returns the wedding anniversary of the contact.
     */
    public Date getAnniversary() {
        return anniversary;
    }
    
    void setAnniversary(Date anniversary) {
        this.anniversary = anniversary;
    }
    
    /**
     * Returns the gender of the contact.
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Returns true if the gender of the contact is male. 
     */
    public boolean isMale() {
        return MALE.equalsIgnoreCase(gender);
    }
    
    /**
     * Returns true if the gender of the contact is female. 
     */
    public boolean isFemale() {
        return FEMALE.equalsIgnoreCase(gender);
    }
    
    void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Returns notes about this contact, with an unspecified meaning or usage.
     */
    public String getNote() {
        return note;
    }
    
    void setNote(String note) {
        this.note = note;
    }
    
    /**
     * Returns the preferred username of the contact on sites that ask for a 
     * username (e.g. jsmarr or daveman692).
     */
    public String getPreferredUsername() {
        return preferredUsername;
    }
    
    void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }
    
    /**
     * Returns the offset from UTC of the contact's current time zone, as of the 
     * time this response was returned.
     */
    public String getUtcOffset() {
        return utcOffset;
    }
    
    void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
    
    /**
     * Returns true if the user and the Contact have established a 
     * bi-directionally asserted connection of some kind on the Service 
     * Provider's service.
     */
    public boolean isConnected() {
        return connected;
    }
    
    void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    /**
     * Returns the email addresses for the contact.
     */
    public List<Email> getEmails() {
        return emails;
    }
    
    void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
    /**
     * Returns the URL's of web pages relating to the contact.
     */
    public List<Url> getUrls() {
        return urls;
    }
    
    void setUrls(List<Url> urls) {
        this.urls = urls;
    }
    
    /**
     * Returns the phone numbers for the contact.
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
    
    void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    
    /**
     * Returns the instant messaging addresses for the contact.
     */
    public List<IM> getIms() {
        return ims;
    }
    
    void setIms(List<IM> ims) {
        this.ims = ims;
    }
    
    /**
     * Returns the URL's of photos of the contact.
     */
    public List<Photo> getPhotos() {
        return photos;
    }
    
    void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
    /**
     * Returns the user-defined categories or labels for the contact, e.g. "favorite" or "web20".
     */
    public List<String> getTags() {
        return tags;
    }
    
    void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    /**
     * Returns the bi-directionally asserted relationships that were established 
     * between the user and the contact by the Service Provider.
     */
    public List<Relationship> getRelationships() {
        return relationships;
    }
    
    void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
    
    /**
     * Returns the physical mailing addresses for the contact.
     */
    public List<Address> getAddresses() {
        return addresses;
    }
    
    void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * Returns the current or past organizational affiliations of the contact.
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }
    
    void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
    
    /**
     * Returns the online accounts held by the contact.
     */
    public List<Account> getAccounts() {
        return accounts;
    }
    
    void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
