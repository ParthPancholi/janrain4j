/* Copyright 2011 Sergey Tsymbler
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
package com.googlecode.janrain4j.api.engage.response;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.json.JSONObject;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ApplicationResponseTest {

    private String json = null;
    private ApplicationResponse response = null;

    @Test
    public void testApplicationResponse() throws Exception {

        json =
                "{\n" +
                        "    \"adminUrl\":\"https://rpxnow.com/relying_parties/testapp\",\n" +
                        "    \"apiKey\":\"90aa768629aa84628c7d31c436330cfd321bcf1c\",\n" +
                        "    \"appId\":\"kpeaaghpfehajgcnoenl\",\n" +
                        "    \"realm\":\"testapp.rpxnow.com\",\n" +
                        "    \"stat\":\"ok\",\n" +
                        "    \"inviteUrl\":\"https://rpxnow.com/invite?code=bdde2043d3540a5e82f4b19635f52ca84169f902\"\n" +
                        "}";

        response = new ApplicationResponse(json);

        assertEquals(json, response.getResponseAsJSON());
        assertEquals(new JSONObject(json).toString(), response.getResponseAsJSONObject().toString());

        assertEquals("kpeaaghpfehajgcnoenl", response.getAppId());
        assertEquals("90aa768629aa84628c7d31c436330cfd321bcf1c", response.getApiKey());
        assertEquals("testapp.rpxnow.com", response.getDomain());
        assertEquals("https://rpxnow.com/relying_parties/testapp", response.getAdminUrl());
        assertEquals("https://rpxnow.com/invite?code=bdde2043d3540a5e82f4b19635f52ca84169f902", response.getInviteUrl());
    }

    @Test(expected = EngageFailureException.class)
    public void testApplicationResponseWithInvalidJSON() throws Exception {

        json =
                "{\n" +
                        "    \"adminUrl\":\"https://rpxnow.com/relying_parties/testapp\",\n" +
                        "    \"apiKey\":\"90aa768629aa84628c7d31c436330cfd321bcf1c\",\n" +
                        "    \"appId\":\"kpeaaghpfehajgcnoenl\",\n" +
                        "    \"realm\":\"testapp.rpxnow.com\",\n" +
                        "    \"stat\":\"ok\",\n" +
                        "}";

        response = new ApplicationResponse(json);
    }

    @Test
    public void testSerializable() throws Exception {

        json =
                "{\n" +
                        "    \"adminUrl\":\"https://rpxnow.com/relying_parties/testapp\",\n" +
                        "    \"apiKey\":\"90aa768629aa84628c7d31c436330cfd321bcf1c\",\n" +
                        "    \"appId\":\"kpeaaghpfehajgcnoenl\",\n" +
                        "    \"realm\":\"testapp.rpxnow.com\",\n" +
                        "    \"stat\":\"ok\",\n" +
                        "    \"inviteUrl\":\"https://rpxnow.com/invite?code=bdde2043d3540a5e82f4b19635f52ca84169f902\"\n" +
                        "}";

        response = new ApplicationResponse(json);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(response);
        oos.close();

        assertTrue(out.toByteArray().length > 0);
        assertNotNull(response.getResponseAsJSONObject());
    }
}
