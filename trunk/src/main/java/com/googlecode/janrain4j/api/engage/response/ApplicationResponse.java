/* Copyright 2011 Sergey Tsymbler
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

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;

/**
 * The <code>ApplicationResponse</code> contains information about Janrain Engage application
 *
 * @since 1.2
 */

public class ApplicationResponse extends AbstractEngageResponse {

    private String appId;
    private String apiKey;
    private String domain;
    private String adminUrl;
    private String inviteUrl;

    public ApplicationResponse(String json) throws EngageFailureException {
        super(json);
        try {
            JSONObject rsp = getResponseAsJSONObject();

            appId = rsp.getString("appId");
            apiKey = rsp.getString("apiKey");
            domain = rsp.getString("realm");
            adminUrl = rsp.getString("adminUrl");
            inviteUrl = rsp.getString("inviteUrl");
        } catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", json, e);
        }
    }

    /**
     * Returns 20-character application id for the newly created Engage application.
     *
     * @return  Application's Id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Returns application’s api key used  by all other partner api methods
     * for additional application configuration.
     *
     * @return  Application's api key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Returns fully qualified domain for the newly created Engage application.
     *
     * @return  Application's domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Returns application’s administration URL. All administrators that had access
     * to the parent app will have access to the new application.
     *
     * @return  Application's admin URL
     */
    public String getAdminUrl() {
        return adminUrl;
    }

    /**
     * Returns  application’s invite URL. This URL can be used to invite new users to the application.
     *
     * @return  Application's invite URL
     */
    public String getInviteUrl() {
        return inviteUrl;
    }

}
