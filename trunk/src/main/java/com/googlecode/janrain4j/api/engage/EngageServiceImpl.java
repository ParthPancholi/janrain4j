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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.googlecode.janrain4j.util.URLEncoderUtils;

/**
 * @author Marcel Overdijk
 * @since 1.0
 */
class EngageServiceImpl implements EngageService {

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
    
    Element apiCall(String method, Map<String, String> partialParams) {
        
        Map<String, String> params = new HashMap<String, String>();
        
        if (partialParams != null) {
            params.putAll(partialParams);
        }

        params.put("format", "xml");
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
            
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(connection.getInputStream());
            
            Element rsp = (Element) document.getFirstChild();
            if (!rsp.getAttribute("stat").equals("ok")) {
  
                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
  
                String code = xpath.evaluate("err/@code", rsp);
                String msg = xpath.evaluate("err/@msg", rsp);
      
                throw new ErrorResponeException(code, msg);
            }
  
            return rsp;
        }
        catch (IOException e) {
            throw new EngageFailureException("Unexpected IO error", e);
        }
        catch (ParserConfigurationException e) {
            throw new EngageFailureException("Unexpected XML error", e);
        }
        catch (SAXException e) {
            throw new EngageFailureException("Unexpected XML error", e);
        }
        catch (XPathExpressionException e) {
            throw new EngageFailureException("Unexpected XPath error", e);
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
