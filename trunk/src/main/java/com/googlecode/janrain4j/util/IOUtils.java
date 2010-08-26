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
package com.googlecode.janrain4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Utility class that provides convenient IO related methods. 
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    
    /**
     * Get the contents of an <code>InputStream</code> as a String
     * using the default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input The <code>InputStream</code> to read from.
     * @return The requested String
     * @throws NullPointerException If the input is null.
     * @throws IOException If an IO error occurs.
     */
    public static String toString(InputStream input) throws IOException {
        StringBuilder contents = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(input);
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        int n = 0;
        while (-1 != (n = reader.read(buffer))) {
            contents.append(buffer, 0, n);
        }
        return contents.toString();
    }
}
