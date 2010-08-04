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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class HttpClientImpl extends AbstractHttpClient {

    /**
     * The default buffer size to use for {@link #toByteArray(InputStream)}.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    
    public HttpClientImpl() {
    }
    
    public HttpClientImpl(HttpClientConfig config) {
        super(config);
    }
    
    public HttpResponse post(String url, Map<String, String> parameters) throws HttpFailureException {
        try {
            StringBuffer sb = new StringBuffer();
            for (Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator(); it.hasNext();) {
                if (sb.length() > 0)
                    sb.append("&");
    
                Map.Entry<String, String> e = (Map.Entry<String, String>) it.next();
                sb.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
            }
            String data = sb.toString();
            
            HttpURLConnection connection = getConnection(url);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.close();
            
            byte[] body = toByteArray(connection.getInputStream());
            
            // TODO refactor to use HttpResponse interface?
            return new HttpResponseImpl(connection.getResponseCode(), body);
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
        
        if (useProxy()) {
            connection = (HttpURLConnection) url.openConnection();
        }
        else {
            if (useProxyAuthentication()) {
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
        
        if (config.getConnectTimeout() > 0) {
            connection.setConnectTimeout(config.getConnectTimeout());
        }
        
        if (config.getReadTimeout() > 0) {
            connection.setReadTimeout(config.getReadTimeout());
        }
        
        return connection;
    }
    
    /**
     * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input The <code>InputStream</code> to read from.
     * @return The requested byte array
     * @throws IOException If an I/O error occurs.
     * @see <a href="http://commons.apache.org/io/api-release/org/apache/commons/io/IOUtils.html#toByteArray(java.io.InputStream)">Apache Commons IO</a>
     */
    protected byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
