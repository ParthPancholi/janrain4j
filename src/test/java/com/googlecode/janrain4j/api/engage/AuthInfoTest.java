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

import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.AUTH_INFO_METHOD;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.EXTENDED_PARAM;
import static com.googlecode.janrain4j.api.engage.EngageServiceImpl.TOKEN_PARAM;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.googlecode.janrain4j.json.JSONObject;

public class AuthInfoTest extends EngageServiceImplTestCase {

    private String token = "my-token";
    
    @Test
    public void testAuthInfo() throws Exception {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        
        String response =
            "{" +
            "  \"profile\": {\n" +
            "    \"displayName\": \"brian\",\n" +
            "    \"preferredUsername\": \"brian\",\n" +
            "    \"url\": \"http:\\/\\/brian.myopenid.com\\/\",\n" +
            "    \"providerName\": \"Other\",\n" +
            "    \"identifier\": \"http:\\/\\/brian.myopenid.com\\/\"\n" +
            "  },\n" +
            "  \"stat\": \"ok\"\n" +
            "}";
        
        doReturn(new JSONObject(response)).when(service).apiCall(AUTH_INFO_METHOD, params);
        
        UserData userData = service.authInfo(token);
        
        assertEquals("expected", "actual");
        // TODO assertions
        
        verify(service).apiCall(AUTH_INFO_METHOD, params);
    }
    
    @Test(expected = EngageFailureException.class)
    public void testAuthInfoThrowsEngageFailureException() {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        
        doThrow(engageFailureException()).when(service).apiCall(AUTH_INFO_METHOD, params);
        
        service.authInfo(token);
        
        verify(service).apiCall(AUTH_INFO_METHOD, params);
    }
    
    @Test(expected = ErrorResponeException.class)
    public void testAuthInfoThrowsErrorResponeException() {
        // expected params in api call
        params.put(TOKEN_PARAM, token);
        params.put(EXTENDED_PARAM, Boolean.toString(false));
        
        doThrow(errorResponeException()).when(service).apiCall(AUTH_INFO_METHOD, params);
        
        service.authInfo(token);
        
        verify(service).apiCall(AUTH_INFO_METHOD, params);
    }
}
