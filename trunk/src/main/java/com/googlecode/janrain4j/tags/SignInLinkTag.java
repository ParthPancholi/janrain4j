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
package com.googlecode.janrain4j.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.googlecode.janrain4j.util.URLEncoderUtils;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class SignInLinkTag extends AbstractBaseTag {

    private String applicationDomain = null;
    private String tokenUrl = null;
    private String text = " Sign In ";
    
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        
        StringBuffer sb = new StringBuffer();
        sb.append("<a class=\"rpxnow\" onclick=\"return false;\" ");
        sb.append("href=\"").append(getApplicationDomain()).append("openid/v2/signin?token_url=").append(URLEncoderUtils.encode(getTokenUrl())).append("\"");
        sb.append(">").append(text).append("</a>");
        
        out.println(sb.toString());
    }
    
    public String getApplicationDomain() {
        return applicationDomain != null ? applicationDomain : getConfig().getApplicationDomain();
    }
    
    public void setApplicationDomain(String applicationDomain) {
        this.applicationDomain = applicationDomain;
    }
    
    public String getTokenUrl() {
        return tokenUrl != null ? tokenUrl : getConfig().getTokenUrl();
    }
    
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
