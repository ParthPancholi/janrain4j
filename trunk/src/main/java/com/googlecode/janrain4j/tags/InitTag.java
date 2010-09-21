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
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * Tag to initialize the RPXNOW library.
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class InitTag extends AbstractBaseTag {

    private String applicationID = null;
    private String xdReceiver = "/rpx_xdcomm.html";
    
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        
        StringWriter sw = new StringWriter();
        sw.append("<script type=\"text/javascript\">\n");
        sw.append("    RPXNOW.init({appId: '").append(getApplicationID()).append("', xdReceiver: '").append(getXdReceiver()).append("'});\n");
        sw.append("</script>");
        
        out.println(sw.toString());
    }
    
    public String getApplicationID() {
        return applicationID != null ? applicationID : getConfig().getApplicationID();
    }
    
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
    
    public String getXdReceiver() {
        return xdReceiver;
    }
    
    public void setXdReceiver(String xdReceiver) {
        this.xdReceiver = xdReceiver;
    }
}
