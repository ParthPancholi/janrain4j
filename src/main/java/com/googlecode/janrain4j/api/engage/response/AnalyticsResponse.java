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

import java.net.MalformedURLException;
import java.net.URL;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * The <code>AnalyticsResponse</code> contains statistics for your application.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see EngageService#analytics(java.util.Date, java.util.Date)
 */
public class AnalyticsResponse extends EngageResponse {

    private static final long serialVersionUID = -7109134903889713304L;
    
    private URL url = null;
    
    public AnalyticsResponse(String jsonResponse) {
        super(jsonResponse);
        JSONObject rsp = getResponseAsJSONObject();
        try {
            String rspUrl = rsp.getString("url");
            try {
                url = new URL(rspUrl);
            }
            catch (MalformedURLException e) {
                throw new EngageFailureException("Malformed url in response: " + rspUrl, jsonResponse, e);
            }
        }
        catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
        }
    }
    
    /**
     * Returns the URL of the zip file containing the statistics for your application.
     */
    public URL getUrl() {
        return url;
    }
}
