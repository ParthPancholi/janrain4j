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

/**
 * <code>HttpFailureException</code> is thrown when any unknown error occurs.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class HttpFailureException extends RuntimeException {

    private static final long serialVersionUID = -825890099186185490L;

    public HttpFailureException(String message) {
        super(message);
    }
    
    public HttpFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
