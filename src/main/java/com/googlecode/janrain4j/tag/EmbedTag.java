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
package com.googlecode.janrain4j.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.googlecode.janrain4j.util.URLEncoderUtils;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class EmbedTag extends SimpleTagSupport {

    private String embedUrl = null;
    private String tokenUrl = null;
    private String defaultProvider = null;
    private String flags = null;
    private String languagePreference = null;
    private int width = 400;
    private int height = 240;
    
    @Override
    public void doTag() throws JspException, IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("token_url", tokenUrl);
        if (defaultProvider != null && defaultProvider.length() > 0) {
            params.put("default_provider", defaultProvider);
        }
        if (flags != null && flags.length() > 0) {
            params.put("flags", flags);
        }
        if (languagePreference != null && languagePreference.length() > 0) {
            params.put("language_preference", languagePreference);
        }
        String encodedParams = URLEncoderUtils.encodeParameters(params);

        StringBuffer sb = new StringBuffer();
        sb.append("<iframe ");
        sb.append("src=\"").append(embedUrl).append("?").append(encodedParams).append("\" ");
        sb.append("scrolling=\"no\" ");
        sb.append("frameBorder=\"no\" ");
        sb.append("allowtransparency=\"true\" ");
        sb.append("style=\"width: ").append(width).append("px; height: ").append(height).append("px;\"");
        sb.append("></iframe>");
        
        JspWriter out = this.getJspContext().getOut();
        out.println(sb.toString());
    }
    
    public String getEmbedUrl() {
        return embedUrl;
    }
    
    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }
    
    public String getTokenUrl() {
        return tokenUrl;
    }
    
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
    
    public String getDefaultProvider() {
        return defaultProvider;
    }
    
    public void setDefaultProvider(String defaultProvider) {
        this.defaultProvider = defaultProvider;
    }
    
    public String getFlags() {
        return flags;
    }
    
    public void setFlags(String flags) {
        this.flags = flags;
    }
    
    public String getLanguagePreference() {
        return languagePreference;
    }
    
    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
}
