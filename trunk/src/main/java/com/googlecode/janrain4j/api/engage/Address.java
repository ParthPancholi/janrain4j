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
 * A dictionary of address parts.
 * 
 * @author Marcel Overdijk
 * @see <a href="http://rpxnow.com/docs#profile_address">Profile data documentation</a>
 * @since 1.0
 */
public class Address {

    private String formatted = null;
    private String streetAddress = null;
    private String locality = null;
    private String region = null;
    private String postalCode = null;
    private String country = null;
    
    /**
     * Returns the full mailing address, formatted for display or use with a 
     * mailing label.
     */
    public String getFormatted() {
        return formatted;
    }
    
    /**
     * Sets the full mailing address, formatted for display or use with a 
     * mailing label.
     * 
     * @param formatted The full mailing address.
     */
    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
    
    /**
     * Returns the full street address component, which may include house 
     * number, street name, PO BOX, and multi-line extended street address 
     * information.
     */
    public String getStreetAddress() {
        return streetAddress;
    }
    
    /**
     * Sets the full street address component, which may include house number, 
     * street name, PO BOX, and multi-line extended street address information.
     * 
     * @param streetAddress The full street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    /**
     * Returns the city or locality component.
     */
    public String getLocality() {
        return locality;
    }
    
    /**
     * Sets the city or locality component.
     * 
     * @param locality The city or locality.
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }
    
    /**
     * Returns the state or region component.
     */
    public String getRegion() {
        return region;
    }
    
    /**
     * Sets the state or region component.
     * 
     * @param region The state or region.
     */
    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * Returns the postal code or zipcode.
     */
    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * Sets the postal code or zipcode.
     * 
     * @param postalCode The postal code or zipcode.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    /**
     * Returns the country name component.
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Sets the country name component.
     * 
     * @param country The country.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
