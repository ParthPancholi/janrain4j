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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Abstract {@link HttpResponse} which convenient helper methods. 
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public abstract class AbstractHttpResponse implements HttpResponse {

    protected int statusCode;
    protected byte[] body;
    
    public AbstractHttpResponse() {
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public byte[] getBody() {
        return body;
    }
    
    public void setBody(byte[] body) {
        this.body = body;
    }
    
    public Document getBodyAsDocument() throws HttpFailureException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(body));
        }
        catch (ParserConfigurationException e) {
            throw new HttpFailureException("Unexpected XML error", e);
        }
        catch (SAXException e) {
            throw new HttpFailureException("Unexpected XML error", e);
        }
        catch (IOException e) {
            throw new HttpFailureException("Unexpected IO error", e);
        }
    }
}
