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

/**
 * A dictionary of name parts.
 * 
 * @author Marcel Overdijk
 * @see <a href="http://rpxnow.com/docs#profile_name">Profile data documentation</a>
 * @since 1.0
 */
public class Name {

    private String formatted = null;
    private String familyName = null;
    private String givenName = null;
    private String middleName = null;
    private String honorificPrefix = null;
    private String honorificSuffix = null;
    
    /**
     * Returns the full name, including all middle names, titles, and suffixes 
     * as appropriate, formatted for display (e.g. Mr. Joseph Robert Smarr, Esq.).
     */
    public String getFormatted() {
        return formatted;
    }
    
    /**
     * Sets the the full name, including all middle names, titles, and suffixes 
     * as appropriate, formatted for display (e.g. Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param formatted The full name.
     */
    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
    
    /**
     * Returns the family name of this Contact, or "Last Name" in most Western 
     * languages (e.g. Smarr given the full name Mr. Joseph Robert Smarr, Esq.).
     */
    public String getFamilyName() {
        return familyName;
    }
    
    /**
     * Sets the family name of this Contact, or "Last Name" in most Western 
     * languages (e.g. Smarr given the full name Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param familyName The family name.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    
    /**
     * Returns the given name of this Contact, or "First Name" in most Western 
     * languages (e.g. Joseph given the full name Mr. Joseph Robert Smarr, Esq.).
     */
    public String getGivenName() {
        return givenName;
    }
    
    /**
     * Sets the given name of this Contact, or "First Name" in most Western 
     * languages (e.g. Joseph given the full name Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param givenName The given name.
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    
    /**
     * Returns the middle name(s) of this Contact (e.g. Robert given the full 
     * name Mr. Joseph Robert Smarr, Esq.).
     */
    public String getMiddleName() {
        return middleName;
    }
    
    /**
     * Sets the middle name(s) of this Contact (e.g. Robert given the full name 
     * Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param middleName The middle name(s).
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * Returns the honorific prefix(es) of this Contact, or "Title" in most 
     * Western languages (e.g. Mr. given the full name Mr. Joseph Robert Smarr, Esq.).
     */
    public String getHonorificPrefix() {
        return honorificPrefix;
    }
    
    /**
     * Sets the honorific prefix(es) of this Contact, or "Title" in most 
     * Western languages (e.g. Mr. given the full name Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param honorificPrefix The honorific prefix(es).
     */
    public void setHonorificPrefix(String honorificPrefix) {
        this.honorificPrefix = honorificPrefix;
    }
    
    /**
     * Returns the honorific suffix(es) of this Contact, or "Suffix" in most 
     * Western languages (e.g. Esq. given the full name Mr. Joseph Robert Smarr, Esq.).
     */
    public String getHonorificSuffix() {
        return honorificSuffix;
    }
    
    /**
     * Sets the honorific suffix(es) of this Contact, or "Suffix" in most Western 
     * languages (e.g. Esq. given the full name Mr. Joseph Robert Smarr, Esq.).
     * 
     * @param honorificSuffix The honorific suffix(es).
     */
    public void setHonorificSuffix(String honorificSuffix) {
        this.honorificSuffix = honorificSuffix;
    }
}
