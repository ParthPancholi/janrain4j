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
package com.googlecode.janrain4j.api.engage.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.json.JSONArray;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * The <code>AllMappingsResponse</code> contains all mappings stored for the 
 * application.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see EngageService#allMappings()
 */
public class AllMappingsResponse extends EngageResponse {

    private static final long serialVersionUID = 7817240961441974055L;
    
    private Map<String, List<String>> allMappings = null;
    
    public AllMappingsResponse(String jsonResponse) {
        // TODO do not return map, but collection of Mapping instances
        super(jsonResponse);
        JSONObject rsp = getResponseAsJSONObject();
        JSONObject rspMappings = rsp.optJSONObject("mappings");
        allMappings = new HashMap<String, List<String>>();
        if (rspMappings != null) {
            for (Iterator<String> iterator = rspMappings.keys(); iterator.hasNext();) {
                String primaryKey = iterator.next();
                try {
                    JSONArray rspIdentifiers = rspMappings.getJSONArray(primaryKey);
                    List<String> identifiers = new ArrayList<String>();
                    for (int i = 0; i < rspIdentifiers.length(); i++) {
                        identifiers.add(rspIdentifiers.getString(i));
                    }
                    allMappings.put(primaryKey, identifiers);
                }
                catch (JSONException e) {
                    throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
                } 
            }
        }
    }
    
    /**
     * Returns all stored mappings for the application.
     */
    public Map<String, List<String>> getAllMappings() {
        return allMappings;
    }
}
