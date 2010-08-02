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

import java.util.List;

/**
 * Represents a stored mapping.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class Mapping {

    private String primaryKey;
    private List<String> identifiers;
    
    Mapping() {
    }
    
    Mapping(String primaryKey, List<String> identifiers) {
        this.primaryKey = primaryKey;
        this.identifiers = identifiers;
    }
    
    /**
     * Returns the primary key.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }
    
    void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Returns the list of identifiers assigned to the primary key.
     */
    public List<String> getIdentifiers() {
        return identifiers;
    }
    
    void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }
}
