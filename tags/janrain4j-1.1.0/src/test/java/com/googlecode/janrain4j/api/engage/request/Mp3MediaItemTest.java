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
package com.googlecode.janrain4j.api.engage.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.janrain4j.json.JSONStringer;

public class Mp3MediaItemTest {

    private Mp3MediaItem mediaItem = null;
    
    JSONStringer json = null;
    
    private String src = "my-src";
    private String title = "my-title";
    private String artist = "my-artist";
    private String album = "my-album";
    
    @Before
    public void setUp() {
        json = new JSONStringer();
    }
    
    @Test
    public void testMp3MediaItem() throws Exception {
        mediaItem = new Mp3MediaItem(src);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"mp3\",\"src\":\"my-src\"}", json.toString());
    }
    
    @Test
    public void testMp3MediaItemWithTitle() throws Exception {
        mediaItem = new Mp3MediaItem(src);
        mediaItem.setTitle(title);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"mp3\",\"src\":\"my-src\",\"title\":\"my-title\"}", json.toString());
    }
    
    @Test
    public void testMp3MediaItemWithArtist() throws Exception {
        mediaItem = new Mp3MediaItem(src);
        mediaItem.setArtist(artist);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"mp3\",\"src\":\"my-src\",\"artist\":\"my-artist\"}", json.toString());
    }
    
    @Test
    public void testMp3MediaItemWithAlbum() throws Exception {
        mediaItem = new Mp3MediaItem(src);
        mediaItem.setAlbum(album);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"mp3\",\"src\":\"my-src\",\"album\":\"my-album\"}", json.toString());
    }
    
    @Test
    public void testMp3MediaItemWithAllFieldsPossible() throws Exception {
        mediaItem = new Mp3MediaItem(src);
        mediaItem.setTitle(title);
        mediaItem.setArtist(artist);
        mediaItem.setAlbum(album);
        mediaItem.writeJSON(json);
        assertEquals("{\"type\":\"mp3\",\"src\":\"my-src\",\"title\":\"my-title\",\"artist\":\"my-artist\",\"album\":\"my-album\"}", json.toString());
    }
}
