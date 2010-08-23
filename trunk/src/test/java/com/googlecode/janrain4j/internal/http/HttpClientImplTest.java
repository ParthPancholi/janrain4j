/* Copyright 2010 Marcel Overdijk

import java.net.HttpURLConnection;

import java.net.HttpURLConnection;

import java.sql.Connection;
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HttpClientImplTest {

    HttpClientImpl client = null;
    HttpClientConfig config = null;
    
    @Before
    public void setUp() throws MalformedURLException {
        config = mock(HttpClientConfig.class);
        client = new HttpClientImpl(config);
    }
    
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        assertEquals("Hello+World%21", client.encode("Hello World!"));
    }
    
    @Test
    public void testEncodeParameter() throws UnsupportedEncodingException {
        assertEquals("the+message=Hello+World%21", client.encodeParameter("the message", "Hello World!"));
    }
    
    @Test
    public void testEncodeParamtersWithSingleParameter() throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("the message", "Hello World!");
        assertEquals("the+message=Hello+World%21", client.encodeParamters(parameters));
    }
    
    @Test
    public void testEncodeParamtersWithMultipleParameter() throws UnsupportedEncodingException {
        Map<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("p1", "v1");
        parameters.put("p2", "v2");
        parameters.put("p3", "v3");
        assertEquals("p1=v1&p2=v2&p3=v3", client.encodeParamters(parameters));
    }
    
    @Test
    public void testToByteArray() throws IOException {
        String input = "Hello World!";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        assertArrayEquals(input.getBytes(), client.toByteArray(is));
    }
}
