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

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
@SuppressWarnings("serial")
abstract class AbstractPluralField implements Serializable {

    public static final String TYPE_WORK = "work";
    public static final String TYPE_HOME = "home";
    public static final String TYPE_OTHER = "other";
    
    protected String value = null;
    protected String type = null;
    protected boolean primary = false;
    
    AbstractPluralField() {
    }
    
    /**
     * Returns the value of this instance.
     */
    public String getValue() {
        return value;
    }
    
    void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Returns the type of this instance.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Returns true if the type of this instance is work. 
     */
    public boolean isWork() {
        return TYPE_WORK.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is home. 
     */
    public boolean isHome() {
        return TYPE_HOME.equalsIgnoreCase(type);
    }
    
    /**
     * Returns true if the type of this instance is other. 
     */
    public boolean isOther() {
        return TYPE_OTHER.equalsIgnoreCase(type);
    }
    
    void setType(String type) {
        this.type = type;
    }
    
    /**
     * Returns true if this instance is the primary or prefered value for this field.
     */
    public boolean isPrimary() {
        return primary;
    }
    
    void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
