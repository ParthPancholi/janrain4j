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

package com.googlecode.janrain4j.api.engage;

import com.googlecode.janrain4j.conf.Config;
import com.googlecode.janrain4j.conf.ConfigHolder;
import com.googlecode.janrain4j.http.HttpClientFactory;
import com.googlecode.janrain4j.http.HttpFailureException;
import com.googlecode.janrain4j.http.HttpResponse;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

abstract class AbstractService {

    public static final String JSON = "json";

    public static final String API_KEY_PARAM = "apiKey";
    public static final String FORMAT_PARAM = "format";

    private Log log = LogFactory.getLog(this.getClass());
    protected Config config = null;

    public AbstractService() {
    }

    public AbstractService(Config config) {
        this.config = config;
    }

    protected Config getConfig() {
        return config == null ? ConfigHolder.getConfig() : config;
    }

    protected String apiCall(String method, Map<String, String> partialParams) throws EngageFailureException, ErrorResponeException {

        Map<String, String> params = new HashMap<String, String>();

        if (partialParams != null) {
            params.putAll(partialParams);
        }

        params.put(FORMAT_PARAM, JSON);

        String url = getBaseUrl() + method;

        if (log.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Janrain Engage request: ").append(method).append("\n");
            sb.append("url: ").append(url).append("\n");
            sb.append("parameters: [\n");
            for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                String value = params.get(key);
                sb.append("  ").append(key).append(": ").append(value).append("\n");
            }
            sb.append("]");
            log.debug(sb.toString());
        }

        String jsonResponse = null;

        try {
            HttpResponse response = HttpClientFactory.getHttpClient(getConfig()).post(url, params);
            jsonResponse = response.getContent();
            JSONObject rsp = new JSONObject(jsonResponse);

            if (log.isDebugEnabled()) {
                log.debug("Janrain Engage response:\n" + jsonResponse);
            }

            String stat = rsp.getString("stat");
            if (!stat.equals("ok")) {
                if (stat.equals("fail")) {
                    JSONObject err = rsp.getJSONObject("err");
                    int code = err.getInt("code");
                    String msg = err.getString("msg");
                    throw new ErrorResponeException(code, msg, jsonResponse);
                } else {
                    throw new EngageFailureException("Unexpected status in response: " + stat, jsonResponse);
                }
            }

            return jsonResponse;
        } catch (HttpFailureException e) {
            throw new EngageFailureException("Unexpected HTTP error", jsonResponse, e);
        } catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", jsonResponse, e);
        }
    }

    protected abstract String getBaseUrl();
}
