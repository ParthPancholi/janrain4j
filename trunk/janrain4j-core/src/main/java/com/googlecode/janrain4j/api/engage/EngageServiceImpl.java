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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
class EngageServiceImpl implements EngageService {

    EngageServiceConfig config = null;
    
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
        Map<String, String> params = new HashMap<String, String>();
        params.put("identifier", identifier);
        params.put("status", status);
        apiCall("set_status", params);
    }
    
    public void map(String identifier, String primaryKey) {
        map(identifier, primaryKey, true);
    }
    
    public void map(String identifier, String primaryKey, boolean overwrite) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("identifier", identifier);
        params.put("primaryKey", primaryKey);
        params.put("overwrite", Boolean.toString(overwrite));
        apiCall("map", params);
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
        params.put("identifier", identifier);
        params.put("all_identifiers", Boolean.toString(allIdentifiers));
        params.put("primaryKey", primaryKey);
        params.put("unlink", Boolean.toString(unlink));
        apiCall("unmap", params);
    }
    
    public List<String> mappings(String primaryKey) {
        // TODO
        List<String> mappings = new ArrayList<String>();
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
    
    private Element apiCall(String method, Map<String, String> partialQuery) {
        
        Map<String, String> query = new HashMap<String, String>();
        
        if (partialQuery != null) {
            query.putAll(partialQuery);
        }

        query.put("format", "xml");
        query.put("apiKey", config.getApiKey());
        
        try {
            StringBuffer sb = new StringBuffer();
            for (Iterator<Map.Entry<String, String>> it = query.entrySet().iterator(); it.hasNext();) {
                if (sb.length() > 0)
                    sb.append("&");

                Map.Entry<String, String> e = (Map.Entry<String, String>)it.next();
                sb.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
            }
            String data = sb.toString();
            
            URL url = new URL(config.getApiUrl() + "/" + method);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();
            
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(data);
            out.close();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(conn.getInputStream());
            
            Element rsp = (Element) doc.getFirstChild();
            if (!rsp.getAttribute("stat").equals("ok")) {
                throw new RuntimeException("Unexpected API error");
            }
            return rsp;
        }
        catch (UnsupportedEncodingException e) {
            // TODO
        }
        catch (MalformedURLException e) {
            // TODO
        }
        catch (IOException e) {
            // TODO
        }
        catch (ParserConfigurationException e) {
            // TODO
        }
        catch (SAXException e) {
            // TODO
        }
        
        return null;
    }
}
