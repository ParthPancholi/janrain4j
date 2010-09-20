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
package com.googlecode.janrain4j.api.engage.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.json.JSONStringer;

public class FlashTest {

    private Flash flash = null;
    
    JSONStringer json = null;
    
    private String swfsrc = "my-swfsrc";
    private String imgsrc = "my-imgsrc";
    private Integer width = 80;
    private Integer height = 60;
    private Integer expandedWidth = 160;
    private Integer expandedHeight = 120;
    
    @Before
    public void setUp() {
        json = new JSONStringer();
    }
    
    @Test
    public void testFlash() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\"}", json.toString());
    }
    
    @Test
    public void testFlashWithWidth() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.setWidth(width);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"width\":80}", json.toString());
    }
    
    @Test
    public void testFlashWithHeight() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.setHeight(height);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"height\":60}", json.toString());
    }
    
    @Test
    public void testFlashWithExpandedWith() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.setExpandedWidth(expandedWidth);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"expanded_width\":160}", json.toString());
    }
    
    @Test
    public void testFlashWithExpandedHeight() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.setExpandedHeight(expandedHeight);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"expanded_height\":120}", json.toString());
    }
    
    @Test
    public void testFlashWithFullSetOfData() throws Exception {
        flash = new Flash(swfsrc, imgsrc);
        flash.setWidth(width);
        flash.setHeight(height);
        flash.setExpandedWidth(expandedWidth);
        flash.setExpandedHeight(expandedHeight);
        flash.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"width\":80,\"height\":60,\"expanded_width\":160,\"expanded_height\":120}", json.toString());
    }
}
