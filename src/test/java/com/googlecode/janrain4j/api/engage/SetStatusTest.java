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
package com.googlecode.janrain4j.api.engage;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class SetStatusTest extends EngageServiceImplTestCase {

    private String status = "Hello World!";
    
    @Test
    public void testSetStatus() throws ParserConfigurationException, SAXException, IOException {
        params.put("identifier", identifier);
        params.put("status", status);
        
        doReturn(buildElement(successResponse)).when(service).apiCall("set_status", params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall("set_status", params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testSetStatusThrowsEngageFailureException() {
        params.put("identifier", identifier);
        params.put("status", status);
        
        doThrow(engageFailureException()).when(service).apiCall("set_status", params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall("set_status", params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testSetStatusThrowsErrorResponeException() {
        params.put("identifier", identifier);
        params.put("status", status);
        
        doThrow(errorResponeException()).when(service).apiCall("set_status", params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall("set_status", params);
    }
}
