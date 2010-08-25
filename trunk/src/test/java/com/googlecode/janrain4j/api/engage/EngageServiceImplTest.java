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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngageServiceConfig.Builder.class)
public class EngageServiceImplTest {

    private EngageServiceImpl service = null;
    
    private EngageServiceConfig config = null;
    
    private Map<String, String> params = null;
    
    private String apiKey = "my-api-key";
    private String identifier = "my-identifier";
    private String status = "Hello World!";
    
    private String successResponse = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok'/>";
    
    private String errorCode = "99";
    private String errorMessage = "Some error message";
    
    @Before
    public void setUp() {
        mockStatic(EngageServiceConfig.Builder.class);
        config = mock(EngageServiceConfig.class);
        when(EngageServiceConfig.Builder.withApiKey(apiKey)).thenReturn(config);
        
        service = spy(new EngageServiceImpl(config));
        
        params = new HashMap<String, String>();
    }
    
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
        
        doThrow(new EngageFailureException("Some error")).when(service).apiCall("set_status", params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall("set_status", params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testSetStatusThrowsErrorResponeException() {
        params.put("identifier", identifier);
        params.put("status", status);
        
        doThrow(new ErrorResponeException(errorCode, errorMessage)).when(service).apiCall("set_status", params);
        
        service.setStatus(identifier, status);
        
        verify(service).apiCall("set_status", params);
    }
    
    private Element buildElement(String fromXML) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(fromXML.getBytes()));
        return (Element) document.getFirstChild();
    }
}
