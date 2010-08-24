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

import static org.custommonkey.xmlunit.XMLAssert.*;
import static org.custommonkey.xmlunit.XMLUnit.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class HttpResponseImplTest {

    HttpResponseImpl response = null;
    
    int responseCode = 200;
    String plainContent = "Some plain content";
    String xmlContent = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok' />";
    
    @Test
    public void testGetResponseCode() {
        response = new HttpResponseImpl(responseCode, plainContent.getBytes());
        assertEquals(responseCode, response.getResponseCode());
    }
    
    @Test
    public void testGetContent() {
        response = new HttpResponseImpl(responseCode, plainContent.getBytes());
        assertArrayEquals(plainContent.getBytes(), response.getContent());
    }
    
    @Test
    public void testGetContentAsDocument() throws IOException, SAXException {
        response = new HttpResponseImpl(responseCode, xmlContent.getBytes());
        assertXMLEqual(buildTestDocument(xmlContent), response.getContentAsDocument());
    }
    
    @Test(expected = HttpFailureException.class)
    public void testGetContentAsDocumentWithPlainContent() {
        response = new HttpResponseImpl(responseCode, plainContent.getBytes()); 
        response.getContentAsDocument(); // should fail as content is not a xml document
    }
}