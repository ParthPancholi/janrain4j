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

/**
 * Represents an activity update to be posted to the user's activity stream.  
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class Activity {

    private URL url;
    private String action;
    private String userGeneratedContent;
    private String title;
    private String description;
    // TODO private ? actionLinks
    // TODO private ? media
    // TODO private ? properties
    
    public Activity(URL url, String action) {
        this.url = url;
        this.action = action;
    }
    
    /**
     * Returns the url of the resource being mentioned in the activity update
     */
    public URL getUrl() {
        return url;
    }
    
    /**
     * Sets the url of the resource being mentioned in the activity update
     * @param url The url.
     */
    public void setUrl(URL url) {
        this.url = url;
    }
    
    /**
     * Returns the string describing what the user did.
     */
    public String getAction() {
        return action;
    }
    
    /**
     * Sets the string describing what the user did, written in the third person. 
     * Examples:
     * <ul>
     *   <li>"wrote a restaurant review"</li>
     *   <li>"posted a comment"</li>
     *   <li>"took a quiz"</li>
     * </ul>
     * @param action The string describing what the user did.
     */
    public void setAction(String action) {
        this.action = action;
    }
    
    /**
     * Returns the string containing the user-supplied content.
     */
    public String getUserGeneratedContent() {
        return userGeneratedContent;
    }
    
    /**
     * Sets the string containing the user-supplied content, such as a comment 
     * or the first paragraph of an article that the user wrote. Note that some 
     * providers (Twitter in particular) may truncate this value.
     * @param userGeneratedContent The string containing the user-supplied content.
     */
    public void setUserGeneratedContent(String userGeneratedContent) {
        this.userGeneratedContent = userGeneratedContent;
    }
    
    /**
     * Returns the title of the resource being mentioned in the activity update.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title of the resource being mentioned in the activity update.
     * @param title The title of the resource being mentioned in the activity update.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns the description of the resource mentioned in the activity update.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of the resource mentioned in the activity update.
     * @param description The description of the resource mentioned in the activity update.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
