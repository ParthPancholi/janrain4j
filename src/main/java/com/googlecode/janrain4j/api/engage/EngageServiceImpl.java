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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.janrain4j.json.JSONArray;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.json.JSONObject;
import com.googlecode.janrain4j.util.IOUtils;
import com.googlecode.janrain4j.util.URLEncoderUtils;

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
class EngageServiceImpl implements EngageService {

    public static final String ACTIVITY_METHOD = "activity";
    public static final String ALL_MAPPINGS_METHOD = "all_mappings";
    public static final String ANALYTICS_METHOD = "analytics_access";
    public static final String AUTH_INFO_METHOD = "auth_info";
    public static final String GET_CONTACTS_METHOD = "get_contacts";
    public static final String GET_USER_DATA_METHOD = "get_user_data";
    public static final String MAP_METHOD = "map";
    public static final String MAPPINGS_METHOD = "mappings";
    public static final String SET_AUTH_PROVIDERS_METHOD = "set_auth_providers";
    public static final String SET_STATUS_METHOD = "set_status";
    public static final String UNMAP_METHOD = "unmap";
    
    public static final String ACTIVITY_PARAM = "activity";
    public static final String ALL_IDENTIFIERS_PARAM = "all_identifiers";
    public static final String API_KEY_PARAM = "apiKey";
    public static final String END_PARAM = "end";
    public static final String EXTENDED_PARAM = "extended";
    public static final String FORMAT_PARAM = "format";
    public static final String IDENTIFIER_PARAM = "identifier";
    public static final String LOCATION_PARAM = "location";
    public static final String OVERWRITE_PARAM = "overwrite";
    public static final String PRIMARY_KEY_PARAM = "primaryKey";
    public static final String PROVIDERS_PARAM = "providers";
    public static final String START_PARAM = "start";
    public static final String STATUS_PARAM = "status";
    public static final String TOKEN_PARAM = "token";
    public static final String UNLINK_PARAM = "unlink";
    
    private EngageServiceConfig config;
    
    EngageServiceImpl(EngageServiceConfig config) {
        this.config = config;
    }
    
    public UserData authInfo(String token) {
        return authInfo(token, false);
    }
    
    public UserData authInfo(String token, boolean extended) {
        // TODO
        UserData profile = new UserData();
        return profile;
    }
    
    public List<Contact> getContacts() {
        // TODO
        List<Contact> contacts = new ArrayList<Contact>();
        return contacts;
    }

    public UserData getUserData(String identifier) {
        return getUserData(identifier, false);
    }

    public UserData getUserData(String identifier, boolean extended) {
        // TODO
        UserData profile = new UserData();
        return profile;
    }
    
    public void setStatus(String identifier, String status) {
        setStatus(identifier, status, null);
    }
    
    public void setStatus(String identifier, String status, String location) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(STATUS_PARAM, status);
        if (location != null && location.length() > 0) {
            params.put(LOCATION_PARAM, location);
        }
        apiCall(SET_STATUS_METHOD, params);
    }
    
    public void map(String identifier, String primaryKey) {
        map(identifier, primaryKey, true);
    }
    
    public void map(String identifier, String primaryKey, boolean overwrite) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(IDENTIFIER_PARAM, identifier);
        params.put(PRIMARY_KEY_PARAM, primaryKey);
        params.put(OVERWRITE_PARAM, Boolean.toString(overwrite));
        apiCall(MAP_METHOD, params);
    }

    public void unmap(String primaryKey) {
        unmap(primaryKey, false);
    }

    public void unmap(String primaryKey, boolean unlink) {
        unmap(null, true, primaryKey, unlink);
    }

    public void unmap(String identifier, String primaryKey) {
        unmap(identifier, primaryKey, false);
    }

    public void unmap(String identifier, String primaryKey, boolean unlink) {
        unmap(identifier, false, primaryKey, unlink);
    }
    
    protected void unmap(String identifier, boolean allIdentifiers, String primaryKey, boolean unlink) {
        Map<String, String> params = new HashMap<String, String>();
        if (!allIdentifiers) {
            params.put("identifier", identifier);
        }
        else {
            params.put("all_identifiers", Boolean.toString(allIdentifiers));
        }
        params.put("primaryKey", primaryKey);
        params.put("unlink", Boolean.toString(unlink));
        apiCall("unmap", params);
    }
    
    public List<String> mappings(String primaryKey) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("primaryKey", primaryKey);
        JSONObject rsp = apiCall(MAPPINGS_METHOD, params);
        JSONArray identifiers = rsp.optJSONArray("identifiers");
        List<String> mappings = new ArrayList<String>();
        for (int i = 0; i < identifiers.length(); i++) {
            mappings.add(identifiers.optString(i));
        }
        return mappings;
    }
    
    public List<Mapping> allMappings() {
        // TODO
        List<Mapping> mappings = new ArrayList<Mapping>();
        return mappings;
    }
    
    public void activity(String identifier, Activity activity) {
        // TODO
    }
    
    public URL analytics(Date start, Date end) {
        // TODO
        return null;
    }
    
    public void setAuthProviders(List<String> providers) {
        // TODO
    }
    
    JSONObject apiCall(String method, Map<String, String> partialParams) {
        
        Map<String, String> params = new HashMap<String, String>();
        
        if (partialParams != null) {
            params.putAll(partialParams);
        }

        params.put("format", "json");
        params.put("apiKey", config.getApiKey());
        
        String url = config.getApiUrl() + "/" + method;
        
        try {
            HttpURLConnection connection = getConnection(url);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(URLEncoderUtils.encodeParameters(params));
            writer.close();
            
            String contents = IOUtils.toString(connection.getInputStream());
            JSONObject rsp = new JSONObject(contents);
            
            String stat = rsp.getString("stat");
            if (!stat.equals("ok")) {
                if (stat.equals("fail")) {
                    JSONObject err = rsp.getJSONObject("err");
                    int code = err.getInt("code");
                    String msg = err.getString("msg");
                    throw new ErrorResponeException(code, msg);
                }
                else {
                    throw new EngageFailureException("Unexpected status in response: " + stat);
                }
            }
            
            return rsp;
        }
        catch (IOException e) {
            throw new EngageFailureException("Unexpected IO error", e);
        }
        catch (JSONException e) {
            throw new EngageFailureException("Unexpected JSON error", e);
        }
    }
    
    private HttpURLConnection getConnection(String url) throws IOException {
        
        HttpURLConnection connection = null;
        
        if (config.getProxyHost() != null && config.getProxyHost().length() > 0) {
            if (config.getProxyUsername() != null && config.getProxyUsername().length() > 0) {
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        if (getRequestorType().equals(RequestorType.PROXY)) {
                            return new PasswordAuthentication(config.getProxyUsername(), config.getProxyPassword().toCharArray());
                        }
                        else {
                            return null;
                        }
                    }
                });
            }
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(config.getProxyHost(), config.getProxyPort()));
            connection = (HttpURLConnection) new URL(url).openConnection(proxy);
        }
        else {
            connection = (HttpURLConnection) new URL(url).openConnection();
        }
        
        if (config.getConnectTimeout() > -1) {
            connection.setConnectTimeout(config.getConnectTimeout());
        }
        
        if (config.getReadTimeout() > -1) {
            connection.setReadTimeout(config.getReadTimeout());
        }
        
        return connection;
    }
}
