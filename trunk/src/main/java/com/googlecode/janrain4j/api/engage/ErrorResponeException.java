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
package com.googlecode.janrain4j.api.engage;

/**
 * <code>ErrorResponeException</code> is thrown when the Janrain Engage API 
 * returns an error response.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class ErrorResponeException extends RuntimeException {

    private static final long serialVersionUID = 8537265678248536892L;
    
    public static final String SERVICE_TEMPORARY_UNAVAILABLE_ERROR = "-1";
    public static final String MISSING_PARAMETER_ERROR = "0";
    public static final String INVALID_PARAMETER_ERROR = "1";
    public static final String DATA_NOT_FOUND_ERROR = "2";
    public static final String AUTHENTICATION_ERROR = "3";
    public static final String FACEBOOK_ERROR = "4";
    public static final String MAPPING_EXISTS_ERROR = "5";
    public static final String INTERACTING_WITH_PREVIOUSLY_OPERATIONAL_PROVIDER_ERROR = "6";
    public static final String ENGAGE_ACCOUNT_UPGRADE_NEEDED_ERROR = "7";
    public static final String MISSING_THIRD_PARTY_CREDENTIALS_ERROR = "8";
    public static final String THIRD_PARTY_CREDENTIALS_REVOKED_ERROR = "9";
    public static final String APPLICATION_NOT_PROPERLY_CONFIGURED_ERROR = "10";
    public static final String FEATURE_NOT_SUPPORTED_ERROR = "11";
    public static final String GOOGLE_ERROR = "12";
    public static final String TWITTER_ERROR = "13";
    public static final String LINKED_IN_ERROR = "14";
    public static final String LIVE_ID_ERROR = "15";
    public static final String MY_SPACE_ERROR = "16";
    public static final String YAHOO_ERROR = "17";
    
    private int code;
    
    public ErrorResponeException(int code, String message) {
        super(message);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
