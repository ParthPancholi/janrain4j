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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract {@link HttpClient} which convenient helper methods. 
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public abstract class AbstractHttpClient implements HttpClient {

    /**
     * The default buffer size to use for {@link #toByteArray(InputStream)}.
     */
    protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    
    protected HttpClientConfig config;
    
    /**
     * @param config The <code>HttpClientConfig</code>.
     */
    protected AbstractHttpClient(HttpClientConfig config) {
        this.config = config;
    }

    /**
     * Sets the <code>HttpClientConfig</code>.
     * @param config The <code>HttpClientConfig</code>.
     */
    protected void setConfig(HttpClientConfig config) {
        this.config = config;
    }
    
    /**
     * Encodes the given value to UTF-8.
     * 
     * @param value The value to encode.
     * @return The encoded value.
     * @throws UnsupportedEncodingException If any error occurs while encoding the value.
     * @see #encodeParameter(String, String)
     * @see #encodeParamters(Map)
     */
    protected String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }
    
    /**
     * Encodes to given key to:
     * <p>
     * <code><encoded key>=<encoded value></code>
     * </p>
     * 
     * @param key The key to encode.
     * @param value The value to encode
     * @return The encoded parameter.
     * @throws UnsupportedEncodingException If any error occurs while encoding the parameter.
     * @see #encode(String)
     * @see #encodeParamters(Map)
     */
    protected String encodeParameter(String key, String value) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append(encode(key));
        sb.append("=");
        sb.append(encode(value));
        return sb.toString();
    }
    
    /**
     * Encodes to given parameters to:
     * <p>
     * <code><encoded key1>=<encoded value1>&<encoded key2>=<encoded value2>&...</code>
     * </p>
     * 
     * @param parameters The parameters to encode.
     * @return The encoded parameters.
     * @throws UnsupportedEncodingException If any error occurs while encoding the parameters.
     * @see #encode(String)
     * @see #encodeParameter(String, String)
     */
    protected String encodeParamters(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        for (Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator(); it.hasNext();) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            Map.Entry<String, String> e = (Map.Entry<String, String>) it.next();
            sb.append(encodeParameter(e.getKey(), e.getValue()));
        }
        return sb.toString();
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
