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
package com.googlecode.janrain4j.api.engage.response;

import com.googlecode.janrain4j.api.engage.EngageService;

/**
 * The <code>AuthInfoResponse</code> contains all the information Janrain Engage 
 * knows about the user signing into your website.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 * @see EngageService#authInfo(String)
 * @see EngageService#authInfo(String, boolean)
 */
public class AuthInfoResponse extends AbstractUserDataResponse {

    private static final long serialVersionUID = 5459304221045113525L;
    
    public AuthInfoResponse(String jsonResponse) {
        super(jsonResponse);
    }
}
