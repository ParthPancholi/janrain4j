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
package com.googlecode.janrain4j.http;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.googlecode.janrain4j.conf.Config;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpClientImpl.class)
public class HttpClientImplTest {

    private HttpClientImpl client = null;
    
    private Config config = null;
    
    private Map<String, String> params = null;
    
    private URL url = null;
    private HttpURLConnection connection = null;
    private OutputStream outputStream = null;
    private OutputStreamWriter outputStreamWriter = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    
    private String theUrl = "http://my-url.com";
    
    @Before
    public void setUp() {
        config = mock(Config.class);
        client = new HttpClientImpl(config);
        url = mock(URL.class);
        connection = mock(HttpURLConnection.class);
        outputStream = mock(OutputStream.class);
        outputStreamWriter = mock(OutputStreamWriter.class);
        inputStream = mock(InputStream.class);
        inputStreamReader = mock(InputStreamReader.class);
        params = new HashMap<String, String>();
    }
    
    @Test
    public void testPost() throws Exception {
        whenNew(URL.class).withArguments(theUrl).thenReturn(url);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getOutputStream()).thenReturn(outputStream);
        whenNew(OutputStreamWriter.class).withArguments(outputStream).thenReturn(outputStreamWriter);
        when(connection.getInputStream()).thenReturn(inputStream);
        whenNew(InputStreamReader.class).withArguments(inputStream).thenReturn(inputStreamReader);
        when(inputStreamReader.read(new char[HttpClientImpl.DEFAULT_BUFFER_SIZE])).thenReturn(-1);
        
        client.post(theUrl, params);
        
        verifyNew(URL.class).withArguments(theUrl);
        verify(url).openConnection();
        verify(connection).setDoOutput(true);
        verify(connection).setRequestMethod("POST");
        verify(connection).connect();
        verify(connection).getOutputStream();
        verify(connection).getResponseCode();
        verify(connection).getInputStream();
    }
    
    // TODO remove
    @Test
    public void testURL() throws Exception {
        URL url = mock(URL.class);
        URLConnection urlConnectionMock = mock(URLConnection.class);

        System.out.println("url = " + url.getClass());
        System.out.println("urlConnectionMock = " + urlConnectionMock.getClass());
        
        when(url.openConnection()).thenReturn(urlConnectionMock);

        assertSame(url.openConnection(), urlConnectionMock);

        verify(url).openConnection();
    }
}
