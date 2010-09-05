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
package com.googlecode.janrain4j.tags;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.util.URLEncoderUtils;

public class SignInLinkTest {

    private SignInLinkTag tag = null;
    
    @Before
    public void setUp() {
        tag = new SignInLinkTag();
    }
    
    @Test
    public void testSignInLink() throws UnsupportedEncodingException {
        // TODO
//        assertEquals("test", tag.doTag());
    }

//    <a class="rpxnow" onclick="return false;" href="https://janrain4j.rpxnow.com/openid/v2/signin?token_url=http%3A%2F%2Fmy-token-url.com"> Sign In </a>

}
