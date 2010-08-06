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
package com.googlecode.janrain4j.internal.http

import static org.custommonkey.xmlunit.XMLAssert.*
import static org.custommonkey.xmlunit.XMLUnit.*
import static org.junit.Assert.*

import org.junit.Before;
import org.junit.Test

class HttpResponseImplTest {

    int responseCode = 200
    String plainCcontent = "Some plain content"
    String xmlContent = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok' />"
    
    @Test
    void testGetResponseCode() {
        HttpResponse response = new HttpResponseImpl(responseCode, plainCcontent.bytes)
        assertEquals responseCode, response.getResponseCode()
    }
    
    @Test
    void testGetContent() {
        HttpResponse response = new HttpResponseImpl(responseCode, plainCcontent.bytes)
        assertArrayEquals plainCcontent.bytes, response.getContent()
    }
    
    @Test
    void testGetContentAsDocumentWithXmlContent() {
        HttpResponse response = new HttpResponseImpl(responseCode, xmlContent.bytes)
        assertXMLEqual buildTestDocument(xmlContent), response.getContentAsDocument()
    }
    
    @Test(expected = HttpFailureException.class)
    void testGetContentAsDocumentWithPlainContent() {
        HttpResponse response = new HttpResponseImpl(responseCode, plainCcontent.bytes)
        def document = response.getContentAsDocument() // should fail as content is not a xml document
    }
}
