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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EngageServiceConfig.Builder.class)
public class EngageServiceImplTestCase {

    protected EngageServiceImpl service = null;
    
    protected EngageServiceConfig config = null;
    
    protected Map<String, String> params = null;
    
    protected String apiKey = "my-api-key";
    protected String identifier = "my-identifier";
    protected String primaryKey = "my-primary-key";
    
    protected String successResponse = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok'/>";
    
    protected String successResponseJson = "{ \"stat\": \"ok\" }";
    
    protected String errorCode = "99";
    protected String errorMessage = "Some error message";
    
    @Before
    public void setUp() {
        mockStatic(EngageServiceConfig.Builder.class);
        config = mock(EngageServiceConfig.class);
        when(EngageServiceConfig.Builder.withApiKey(apiKey)).thenReturn(config);
        
        service = spy(new EngageServiceImpl(config));
        
        params = new HashMap<String, String>();
    }
    
    protected Element buildElement(String fromXML) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(fromXML.getBytes()));
        return (Element) document.getFirstChild();
    }
    
    protected EngageFailureException engageFailureException() {
        return engageFailureException("Some error");
    }
    
    protected EngageFailureException engageFailureException(String message) {
        return new EngageFailureException(message);
    }
    
    protected ErrorResponeException errorResponeException() {
        return errorResponeException(errorCode, errorMessage);
    }
    
    protected ErrorResponeException errorResponeException(String code, String message) {
        return new ErrorResponeException(code, message);
    }
}
