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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ActivityTest {

    private Activity activity = null;
    
    private List<ActionLink> actionLinks = null;
    private List<Media> media = null;
    private List<Property> properties = null;
    
    private String url = "http://my-url.com";
    private String action = "my-action";
    private String userGeneratedContent = "my-user-generated-content";
    private String title = "my-title";
    private String description = "my-description";
    private String actionLink1Text = "my-action-link-1-text";
    private String actionLink1Href = "my-action-link-1-href";
    private String actionLink2Text = "my-action-link-2-text";
    private String actionLink2Href = "my-action-link-2-href";
    private String actionLink3Text = "my-action-link-3-text";
    private String actionLink3Href = "my-action-link-3-href";
    private String image1Src = "my-image-1-src";
    private String image1Href = "my-image-1-href";
    private String image2Src = "my-image-2-src";
    private String image2Href = "my-image-2-href";
    private String image3Src = "my-image-3-src";
    private String image3Href = "my-image-3-href";
    private String myLinkProperty1 = "my-link-property-1";
    private String myLinkProperty1Text = "my-link-property-1-text";
    private String myLinkProperty1Href = "my-link-property-1-href";
    private String myLinkProperty2 = "my-link-property-2";
    private String myLinkProperty2Text = "my-link-property-2-text";
    private String myLinkProperty2Href = "my-link-property-2-href";
    private String myLinkProperty3 = "my-link-property-3";
    private String myLinkProperty3Text = "my-link-property-3-text";
    private String myLinkProperty3Href = "my-link-property-3-href";
    private String myStringProperty1 = "my-string-property-1";
    private String myStringProperty2 = "my-string-property-2";
    private String myStringProperty3 = "my-string-property-3";
    private String myStringProperty1Value = "my-string-property-1-value";
    private String myStringProperty2Value = "my-string-property-2-value";
    private String myStringProperty3Value = "my-string-property-3-value";
    
    @Before
    public void setUp() throws Exception {
        activity = new Activity(new URL(url), action);
        actionLinks = new ArrayList<ActionLink>();
        media = new ArrayList<Media>();
        properties = new ArrayList<Property>();
    }
    
    @Test
    public void testActivity() throws Exception {
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithUserGeneratedContent() throws Exception {
        activity.setUserGeneratedContent(userGeneratedContent);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"user_generated_content\":\"my-user-generated-content\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithTitle() throws Exception {
        activity.setTitle(title);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"title\":\"my-title\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithDescription() throws Exception {
        activity.setDescription(description);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"description\":\"my-description\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithMultipleActionLinks() throws Exception {
        actionLinks.add(new ActionLink(actionLink1Text, actionLink1Href));
        actionLinks.add(new ActionLink(actionLink2Text, actionLink2Href));
        actionLinks.add(new ActionLink(actionLink3Text, actionLink3Href));
        activity.setActionLinks(actionLinks);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"action_links\":[{\"text\":\"my-action-link-1-text\",\"href\":\"my-action-link-1-href\"},{\"text\":\"my-action-link-2-text\",\"href\":\"my-action-link-2-href\"},{\"text\":\"my-action-link-3-text\",\"href\":\"my-action-link-3-href\"}]}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithSingleActionLink() throws Exception {
        actionLinks.add(new ActionLink(actionLink1Text, actionLink1Href));
        activity.setActionLinks(actionLinks);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"action_links\":[{\"text\":\"my-action-link-1-text\",\"href\":\"my-action-link-1-href\"}]}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithNoActionLinks() throws Exception {
        activity.setActionLinks(actionLinks);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithMultipleMedia() throws Exception {
        media.add(new Image(image1Src, image1Href));
        media.add(new Image(image2Src, image2Href));
        media.add(new Image(image3Src, image3Href));
        activity.setMedia(media);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"media\":[{\"type\":\"image\",\"src\":\"my-image-1-src\",\"href\":\"my-image-1-href\"},{\"type\":\"image\",\"src\":\"my-image-2-src\",\"href\":\"my-image-2-href\"},{\"type\":\"image\",\"src\":\"my-image-3-src\",\"href\":\"my-image-3-href\"}]}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithSingleMedia() throws Exception {
        media.add(new Image(image1Src, image1Href));
        activity.setMedia(media);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"media\":[{\"type\":\"image\",\"src\":\"my-image-1-src\",\"href\":\"my-image-1-href\"}]}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithNoMedia() throws Exception {
        activity.setMedia(media);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithMultipleProperties() throws Exception {
        properties.add(new Property(myLinkProperty1, myLinkProperty1Text, myLinkProperty1Href));
        properties.add(new Property(myLinkProperty2, myLinkProperty2Text, myLinkProperty2Href));
        properties.add(new Property(myLinkProperty3, myLinkProperty3Text, myLinkProperty3Href));
        properties.add(new Property(myStringProperty1, myStringProperty1Value));
        properties.add(new Property(myStringProperty2, myStringProperty2Value));
        properties.add(new Property(myStringProperty3, myStringProperty3Value));
        activity.setProperties(properties);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"properties\":{\"my-link-property-1\":{\"text\":\"my-link-property-1-text\",\"href\":\"my-link-property-1-href\"},\"my-link-property-2\":{\"text\":\"my-link-property-2-text\",\"href\":\"my-link-property-2-href\"},\"my-link-property-3\":{\"text\":\"my-link-property-3-text\",\"href\":\"my-link-property-3-href\"},\"my-string-property-1\":\"my-string-property-1-value\",\"my-string-property-2\":\"my-string-property-2-value\",\"my-string-property-3\":\"my-string-property-3-value\"}}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithSingleLinkProperty() throws Exception {
        properties.add(new Property(myLinkProperty1, myLinkProperty1Text, myLinkProperty1Href));
        activity.setProperties(properties);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"properties\":{\"my-link-property-1\":{\"text\":\"my-link-property-1-text\",\"href\":\"my-link-property-1-href\"}}}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithSingleStringProperty() throws Exception {
        properties.add(new Property(myStringProperty1, myStringProperty1Value));
        activity.setProperties(properties);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"properties\":{\"my-string-property-1\":\"my-string-property-1-value\"}}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithNoProperties() throws Exception {
        activity.setProperties(properties);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\"}", activity.toJSON());
    }
    
    @Test
    public void testActivityWithFullSetOfData() throws Exception {
        activity.setUserGeneratedContent(userGeneratedContent);
        activity.setTitle(title);
        activity.setDescription(description);
        actionLinks.add(new ActionLink(actionLink1Text, actionLink1Href));
        actionLinks.add(new ActionLink(actionLink2Text, actionLink2Href));
        actionLinks.add(new ActionLink(actionLink3Text, actionLink3Href));
        activity.setActionLinks(actionLinks);
        media.add(new Image(image1Src, image1Href));
        media.add(new Image(image2Src, image2Href));
        media.add(new Image(image3Src, image3Href));
        activity.setMedia(media);
        properties.add(new Property(myLinkProperty1, myLinkProperty1Text, myLinkProperty1Href));
        properties.add(new Property(myLinkProperty2, myLinkProperty2Text, myLinkProperty2Href));
        properties.add(new Property(myLinkProperty3, myLinkProperty3Text, myLinkProperty3Href));
        properties.add(new Property(myStringProperty1, myStringProperty1Value));
        properties.add(new Property(myStringProperty2, myStringProperty2Value));
        properties.add(new Property(myStringProperty3, myStringProperty3Value));
        activity.setProperties(properties);
        assertEquals("{\"url\":\"http://my-url.com\",\"action\":\"my-action\",\"user_generated_content\":\"my-user-generated-content\",\"title\":\"my-title\",\"description\":\"my-description\",\"action_links\":[{\"text\":\"my-action-link-1-text\",\"href\":\"my-action-link-1-href\"},{\"text\":\"my-action-link-2-text\",\"href\":\"my-action-link-2-href\"},{\"text\":\"my-action-link-3-text\",\"href\":\"my-action-link-3-href\"}],\"media\":[{\"type\":\"image\",\"src\":\"my-image-1-src\",\"href\":\"my-image-1-href\"},{\"type\":\"image\",\"src\":\"my-image-2-src\",\"href\":\"my-image-2-href\"},{\"type\":\"image\",\"src\":\"my-image-3-src\",\"href\":\"my-image-3-href\"}],\"properties\":{\"my-link-property-1\":{\"text\":\"my-link-property-1-text\",\"href\":\"my-link-property-1-href\"},\"my-link-property-2\":{\"text\":\"my-link-property-2-text\",\"href\":\"my-link-property-2-href\"},\"my-link-property-3\":{\"text\":\"my-link-property-3-text\",\"href\":\"my-link-property-3-href\"},\"my-string-property-1\":\"my-string-property-1-value\",\"my-string-property-2\":\"my-string-property-2-value\",\"my-string-property-3\":\"my-string-property-3-value\"}}", activity.toJSON());
    }
}
