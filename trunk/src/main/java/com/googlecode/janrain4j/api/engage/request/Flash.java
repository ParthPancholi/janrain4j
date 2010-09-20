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
package com.googlecode.janrain4j.api.engage.request;

/**
 * Flash attachment to be posted to the user's activity stream.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see Activity
 * @see <a href="http://developers.facebook.com/docs/guides/attachments">Media object format and rules</a>
 */
public abstract class Flash extends Media {

    private String swfsrc = null;
    private String imgsrc = null;
    private Integer width = null;
    private Integer height = null;
    private Integer expandedWidth = null;
    private Integer expandedHeight = null;
    
    /**
     * Create a new <code>Flash</code> media attachment.
     * 
     * @param swfsrc The swfsrc.
     * @param imgsrc The imgsrc.
     */
    public Flash(String swfsrc, String imgsrc) {
        this.swfsrc = swfsrc;
        this.imgsrc = imgsrc;
    }
    
    /**
     * Create a new <code>Flash</code> media attachment.
     * 
     * @param swfsrc The swfsrc.
     * @param imgsrc The imgsrc.
     * @param width The width.
     * @param height The height
     */
    public Flash(String swfsrc, String imgsrc, Integer width, Integer height) {
        this(swfsrc, imgsrc);
        this.width = width;
        this.height = height;
    }
    
    /**
     * Create a new <code>Flash</code> media attachment.
     * 
     * @param swfsrc The swfsrc.
     * @param imgsrc The imgsrc.
     * @param width The width.
     * @param height The height
     * @param expandedWidth The expanded width
     * @param expandedHeight The expanded height
     */
    public Flash(String swfsrc, String imgsrc, Integer width, Integer height, Integer expandedWidth, Integer expandedHeight) {
        this(swfsrc, imgsrc, width, height);
        this.expandedWidth = expandedWidth;
        this.expandedHeight = expandedHeight;
    }
    
    /**
     * Returns the swfsrc of the flash attachment.
     */
    public String getSwfsrc() {
        return swfsrc;
    }
    
    /**
     * Sets the swfsrc of the flash attachment.
     * 
     * @param swfsrc The swfsrc.
     */
    public void setSwfsrc(String swfsrc) {
        this.swfsrc = swfsrc;
    }
    
    /**
     * Returns the imgsrc of the flash attachment.
     */
    public String getImgsrc() {
        return imgsrc;
    }
    
    /**
     * Sets the imgsrc of the flash attachment.
     * 
     * @param imgsrc The imgsrc.
     */
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
    
    /**
     * Retuns the width of the flash attachment.
     */
    public Integer getWidth() {
        return width;
    }
    
    /**
     * Sets the width of the flash attachment.
     * 
     * @param width The width (in pixels).
     */
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    /**
     * Returns the height of the flash attachment.
     */
    public Integer getHeight() {
        return height;
    }
    
    /**
     * Sets the height of the flash attachment.
     * 
     * @param height The height (in pixels).
     */
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    /**
     * Returns the width to resize to when the user clicks on the flash object.
     */
    public Integer getExpandedWidth() {
        return expandedWidth;
    }
    
    /**
     * Sets the width to resize to when the user clicks on the flash object.
     * 
     * @param expandedWidth The expanded width (in pixels).
     */
    public void setExpandedWidth(Integer expandedWidth) {
        this.expandedWidth = expandedWidth;
    }
    
    /**
     * Returns the height to resize to when the user clicks on the flash object.
     */
    public Integer getExpandedHeight() {
        return expandedHeight;
    }
    
    /**
     * Sets the height to resize to when the user clicks on the flash object.
     * 
     * @param expandedHeight The expanded height (in pixels).
     */
    public void setExpandedHeight(Integer expandedHeight) {
        this.expandedHeight = expandedHeight;
    }
}
