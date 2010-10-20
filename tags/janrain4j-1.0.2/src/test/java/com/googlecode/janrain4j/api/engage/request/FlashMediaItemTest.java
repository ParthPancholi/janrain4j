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

public class FlashMediaItemTest {

    private FlashMediaItem mediaItem = null;
    
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
    public void testFlashMediaItem() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\"}", json.toString());
    }
    
    @Test
    public void testFlashMediaItemWithWidth() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.setWidth(width);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"width\":80}", json.toString());
    }
    
    @Test
    public void testFlashMediaItemWithHeight() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.setHeight(height);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"height\":60}", json.toString());
    }
    
    @Test
    public void testFlashMediaItemWithExpandedWith() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.setExpandedWidth(expandedWidth);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"expanded_width\":160}", json.toString());
    }
    
    @Test
    public void testFlashMediaItemWithExpandedHeight() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.setExpandedHeight(expandedHeight);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"expanded_height\":120}", json.toString());
    }
    
    @Test
    public void testFlashMediaItemWithAllFieldsPossible() throws Exception {
        mediaItem = new FlashMediaItem(swfsrc, imgsrc);
        mediaItem.setWidth(width);
        mediaItem.setHeight(height);
        mediaItem.setExpandedWidth(expandedWidth);
        mediaItem.setExpandedHeight(expandedHeight);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"flash\",\"swfsrc\":\"my-swfsrc\",\"imgsrc\":\"my-imgsrc\",\"width\":80,\"height\":60,\"expanded_width\":160,\"expanded_height\":120}", json.toString());
    }
}
