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

import java.io.Serializable;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
abstract class AbstractEngageResponse implements Serializable {

    private static final long serialVersionUID = -8862811237568844288L;
    
    private String jsonResponse = null;
    
    public AbstractEngageResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
    
    void setJSONResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
    
    /**
     * Returns the Janrain Engage response body as a JSON <code>String</code>.
     */
    public String getResponseAsJSON() {
        return jsonResponse;
    }
    
    /**
     * Returns the Janrain Engage response body as a <code>JSONObject</code>.
     * 
     * @throws EngageFailureException If any unknown error occurs while communicating with the Janrain Engage API.
     */
    public JSONObject getResponseAsJSONObject() throws EngageFailureException {
        try {
            return new JSONObject(jsonResponse);
        }
        catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
        }
    }
}