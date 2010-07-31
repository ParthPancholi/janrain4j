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

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @see <a href="http://rpxnow.com/docs">Janrain Engage Documentation</a>
 * @since 1.0
 */
public interface EngageService {

    /**
     * Get information about the user currently signing in to your web application.
     * 
     * @param token The token parameter received at your "token_url".
     * @return The user data Janrain Engage knows about the user logging into your website.
     * @see <a href="http://rpxnow.com/docs#api_auth_info">Janrain Engage API Documentation: auth_info</a>
     * @since 1.0
     */
    public UserData authInfo(String token);
    
    /**
     * Get information about the user currently signing in to your web application.
     * 
     * @param token The token parameter received at your "token_url".
     * @param extended 'true' or 'false'(default). Return the extended Simple Registration and HCard data in addition to the normalized Portable Contacts format.
     * @return The user data with Janrain Engage knows about the user logging into your website.
     * @see <a href="http://rpxnow.com/docs#api_auth_info">Janrain Engage API Documentation: auth_info</a>
     * @since 1.0
     */
    public UserData authInfo(String token, boolean extended);
    
    /**
     * Retrieve a list of contacts for an identifier in the <a href="http://portablecontacts.net/">Portable Contacts</a> format.
     * 
     * @return The <a href="http://portablecontacts.net/">Portable Contacts</a> data representing the address book contents.
     * @see <a href="http://rpxnow.com/docs#api_get_contacts">Janrain Engage API Documentation: get_contacts</a>
     * @since 1.0
     */
    public List<Contact> getContacts();
    
    /**
     * Obtain an up-to-date copy of a user's profile as previously returned by 
     * an {@link #authInfo(String)} API call.
     * 
     * @param identifier The identifier returned from a previous {@link #authInfo(String)} API call.
     * @return The user data Janrain Engage knows about the user.
     * @see <a href="http://rpxnow.com/docs/get_user_data">Janrain Engage API Documentation: get_user_data</a>
     * @since 1.0
     */
    public UserData getUserData(String identifier);
    
    /**
     * Obtain an up-to-date copy of a user's profile as previously returned by 
     * an {@link #authInfo(String)} API call.
     * 
     * @param @param identifier The identifier returned from a previous {@link #authInfo(String)} API call.
     * @param extended 'true' or 'false'(default). Return the extended Simple Registration and HCard data in addition to the normalized Portable Contacts format.
     * @return The user data Janrain Engage knows about the user.
     * @see <a href="http://rpxnow.com/docs/get_user_data">Janrain Engage API Documentation: get_user_data</a>
     * @since 1.0
     */
    public UserData getUserData(String identifier, boolean extended);
    
    /**
     * Set the status message for the account corresponding to an identifier.
     * 
     * @param identifier The identifier returned from the {@link #authInfo(String)} API call.
     * @param status The status message to set. No length restriction on the status is imposed by Janrain Engage, but some providers may truncate it implicitly (e.g., Twitter).
     * @see <a href="http://rpxnow.com/docs#api_set_status">Janrain Engage API Documentation: set_status</a>
     * @since 1.0
     */
    public void setStatus(String identifier, String status);
    
    /**
     * Map an OpenID identifier to a primary key. Future logins by this owner 
     * of this OpenID will return the mapped primaryKey in the {@link #authInfo(String)} 
     * API response, which you may use to sign the user in.
     * <p>
     * The map call is usually made right after a call to {@link #authInfo(String)}, 
     * when you already know who the user is because they are signed in to 
     * your website with their legacy (or existing) account.
     * </p>
     * 
     * @param identifier The identifier returned from the {@link #authInfo(String)} API call.
     * @param primaryKey The primary key from your users table, as a string.
     * @see <a href="http://rpxnow.com/docs#api_map">Janrain Engage API Documentation: map</a>
     * @since 1.0
     */
    public void map(String identifier, String primaryKey);
    
    /**
     * Map an OpenID identifier to a primary key. Future logins by this owner 
     * of this OpenID will return the mapped primaryKey in the {@link #authInfo(String)} 
     * API response, which you may use to sign the user in.
     * <p>
     * The map call is usually made right after a call to {@link #authInfo(String)}, 
     * when you already know who the user is because they are signed in to 
     * your website with their legacy (or existing) account.
     * </p>
     * 
     * @param identifier The identifier returned from the {@link #authInfo(String)} API call.
     * @param primaryKey The primary key from your users table, as a string.
     * @param overwrite 'true'(default) or 'false'. If 'false', only create the mapping if one does not already exist for the specified identifier.
     * @see <a href="http://rpxnow.com/docs#api_map">Janrain Engage API Documentation: map</a>
     * @since 1.0
     */
    public void map(String identifier, String primaryKey, boolean overwrite);
    
    /**
     * Remove (unmap) all OpenID identifiers from a primary key.
     * 
     * @param primaryKey The primary key from your users table, as a string.
     * @see <a href="http://rpxnow.com/docs#api_unmap">Janrain Engage API Documentation: unmap</a>
     * @since 1.0
     */
    public void unmap(String primaryKey);
    
    /**
     * Remove (unmap) all OpenID identifiers from a primary key, and optionally 
     * unlink your application from the user's account with the provider.
     * 
     * @param primaryKey The primary key from your users table, as a string.
     * @param unlink 'true' or 'false'(default). If 'true', unlink your application from the user's account with the provider.
     * @see <a href="http://rpxnow.com/docs#api_unmap">Janrain Engage API Documentation: unmap</a>
     * @since 1.0
     */
    public void unmap(String primaryKey, boolean unlink);
    
    /**
     * Remove (unmap) an OpenID identifier from a primary key.
     * 
     * @param identifier The identifier returned from the auth_info API call.
     * @param primaryKey The primary key from your users table, as a string.
     * @see <a href="http://rpxnow.com/docs#api_unmap">Janrain Engage API Documentation: unmap</a>
     * @since 1.0
     */
    public void unmap(String identifier, String primaryKey);
    
    /**
     * Remove (unmap) an OpenID identifier from a primary key, and optionally 
     * unlink your application from the user's account with the provider.
     * 
     * @param identifier The identifier returned from the auth_info API call.
     * @param primaryKey The primary key from your users table, as a string.
     * @param unlink 'true' or 'false'(default). If 'true', unlink your application from the user's account with the provider.
     * @see <a href="http://rpxnow.com/docs#api_unmap">Janrain Engage API Documentation: unmap</a>
     * @since 1.0
     */
    public void unmap(String identifier, String primaryKey, boolean unlink);
    
    /**
     * Get all stored mappings for a particular primary key.
     * 
     * @param primaryKey The primary key from your users table, as a string.
     * @return All stored mappings for a particular primary key.
     * @see <a href="http://rpxnow.com/docs#api_mappings">Janrain Engage API Documentation: mappings</a>
     * @since 1.0
     */
    public List<String> mappings(String primaryKey);
    
    /**
     * Get all stored mappings for the application.
     * 
     * @return All stored mappings for the application.
     * @see <a href="http://rpxnow.com/docs#api_all_mappings">Janrain Engage API Documentation: all_mappings</a>
     * @since 1.0
     */
    public List<Mapping> allMappings();
    
    /**
     * Post an activity update to the user's activity stream.
     * 
     * @param identifier The identifier returned from the {@link #authInfo(String)} API call.
     * @param activity The activity structure.
     * @see <a href="http://rpxnow.com/docs#api_activity">Janrain Engage API Documentation: activity</a>
     * @since 1.0
     */
    public void activity(String identifier, Activity activity);
    
    /**
     * Get statistics for your application in a zip file.
     * 
     * @param start The start date.
     * @param end The end date.
     * @return A URL to access the analytics file.
     * @see <a href="http://rpxnow.com/docs#api_analytics">Janrain Engage API Documentation: analytics</a>
     * @since 1.0
     */
    public URL analytics(Date start, Date end);
    
    /**
     * Set the providers that will be displayed in the <a href="http://rpxnow.com/docs#sign-in_interface">Sign-in</a> Interface.
     * 
     * @param providers A list of provider names.
     * @see <a href="http://rpxnow.com/docs#api_set_auth_providers">Janrain Engage API Documentation: set_auth_providers</a>
     * @since 1.0
     */
    public void setAuthProviders(List<String> providers);
}
