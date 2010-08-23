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
package com.googlecode.janrain4j.api.engage

import static org.custommonkey.xmlunit.XMLUnit.*
import static com.googlecode.janrain4j.api.engage.EngageServiceConfig.Builder.*
import static org.junit.Assert.*

import com.googlecode.janrain4j.internal.http.HttpClient 
import com.googlecode.janrain4j.internal.http.HttpFailureException 
import com.googlecode.janrain4j.internal.http.HttpResponseImpl 
import org.junit.Before 
import org.junit.Test

class EngageServiceImplTest {

    EngageServiceImpl service = null
    EngageServiceConfig config = null
    
    String apiKey = "my-api-key"
    String identier = "my-identifier"
    String status = "Hello World!"
    String successResponse = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='ok'/>"
    String errorCode = "99"
    String errorMessage = "Some error message"
    String errorResponse = "<?xml version='1.0' encoding='UTF-8'?><rsp stat='fail'><err msg='${errorMessage}' code='${errorCode}'/></rsp>"
    
    @Before
    void setUp() {
        config = withApiKey(apiKey)
    }
    
    // TODO authInfo
    
    // TODO getContacts
    
    // TODO getUserData
    
    @Test
    void testSetStatus() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters -> 
            new HttpResponseImpl(200, successResponse.bytes)
        }] as HttpClient // mock http client
        service.setStatus(identier, status)
    }
    
    @Test(expected = EngageFailureException.class)
    void testSetStatusThrowsEngageFailureException() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters -> 
            throw new HttpFailureException("Some error")
        }] as HttpClient // mock http client
        service.setStatus(identier, status)
    }
    
    @Test(expected = ErrorResponeException.class)
    void testSetStatusThrowsErrorResponeException() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters -> 
            new HttpResponseImpl(200, errorResponse.bytes)
        }] as HttpClient // mock http client
        try {
            service.setStatus(identier, status)
        }
        catch (ErrorResponeException e) {
            assertEquals errorCode, e.getCode()
            assertEquals errorMessage, e.getMessage()
            throw e
        }
    }

    // TODO map
    
    // TODO unmap
    
    // TODO mappings
    
    @Test
    void testMappings() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters ->
            response = 
             """<?xml version='1.0' encoding='UTF-8'?>
                <rsp stat='ok'>
                  <identifiers>
                    <identifier>
                      http://brian.myopenid.com/
                    </identifier>
                    <identifier>
                      http://brianellin.com/
                    </identifier>
                  </identifiers>
                </rsp>"""
            new HttpResponseImpl(200, response.bytes)
        }] as HttpClient // mock http client
        def expected = ["http://brian.myopenid.com/", "http://brianellin.com/"]
        def mappings = service.mappings(identier)
        assertEquals expected, mappings
    }
    
    @Test(expected = EngageFailureException.class)
    void testMappingsThrowsEngageFailureException() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters -> 
            throw new HttpFailureException("Some error")
        }] as HttpClient // mock http client
        service.mappings(identier)
    }
    
    @Test(expected = ErrorResponeException.class)
    void testMappingsThrowsErrorResponeException() {
        service = new EngageServiceImpl(withDefaults())
        service.httpClient = [post: { url, parameters -> 
            new HttpResponseImpl(200, errorResponse.bytes)
        }] as HttpClient // mock http client
        try {
            service.mappings(identier)
        }
        catch (ErrorResponeException e) {
            assertEquals errorCode, e.getCode()
            assertEquals errorMessage, e.getMessage()
            throw e
        }
    }
    
    // TODO allMappings
    
    // TODO activity
    
    // TODO analytics
    
    // TODO setAuthProviders
}
