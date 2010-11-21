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
package com.googlecode.janrain4j.util;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.googlecode.janrain4j.util.URLEncoderUtils;

public class URLEncoderUtilsTest {

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        assertEquals("Hello+World%21", URLEncoderUtils.encode("Hello World!"));
    }
    
    @Test
    public void testEncodeNull() throws UnsupportedEncodingException {
        assertEquals("", URLEncoderUtils.encode(null));
    }
    
    @Test
    public void testEncodeEmpty() throws UnsupportedEncodingException {
        assertEquals("", URLEncoderUtils.encode(""));
    }
    
    @Test
    public void testEncodeParameter() throws UnsupportedEncodingException {
        assertEquals("the+message=Hello+World%21", URLEncoderUtils.encodeParameter("the message", "Hello World!"));
    }
    
    @Test
    public void testEncodeParamtersWithSingleParameter() throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("the message", "Hello World!");
        assertEquals("the+message=Hello+World%21", URLEncoderUtils.encodeParameters(parameters));
    }
    
    @Test
    public void testEncodeParamtersWithMultipleParameter() throws UnsupportedEncodingException {
        Map<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("p1", "v1");
        parameters.put("p2", "v2");
        parameters.put("p3", "v3");
        assertEquals("p1=v1&p2=v2&p3=v3", URLEncoderUtils.encodeParameters(parameters));
    }
}
