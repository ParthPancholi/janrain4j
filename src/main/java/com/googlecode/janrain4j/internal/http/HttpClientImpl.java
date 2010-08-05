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
package com.googlecode.janrain4j.internal.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

/**
 * {@link HttpClient} implementation that uses the Java standard 
 * {@link HttpURLConnection}.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class HttpClientImpl extends AbstractHttpClient {

    public HttpClientImpl(HttpClientConfig config) {
        super(config);
    }
    
    public HttpResponse post(String url, Map<String, String> parameters) throws HttpFailureException {
        try {
            HttpURLConnection connection = getConnection(url);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(encodeParamters(parameters));
            writer.close();
            
            int repsonseCode = connection.getResponseCode();
            byte[] content = toByteArray(connection.getInputStream());
            
            return new HttpResponseImpl(repsonseCode, content);
        }
        catch (IOException e) {
            throw new HttpFailureException("Unexpected IO error", e);
        }
    }
    
    protected HttpURLConnection getConnection(String url) throws IOException {
        return getConnection(new URL(url));
    }
    
    protected HttpURLConnection getConnection(URL url) throws IOException {
        
        HttpURLConnection connection = null;
        
        if (config.getProxyHost() != null && config.getProxyHost().length() > 0) {
            if (config.getProxyUsername() != null && config.getProxyUsername().length() > 0) {
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
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
            connection = (HttpURLConnection) url.openConnection(proxy);
        }
        else {
            connection = (HttpURLConnection) url.openConnection();
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
