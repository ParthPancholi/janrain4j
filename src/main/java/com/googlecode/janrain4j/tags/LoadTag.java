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
 * Tag to load the Janrain Engage javascript (https://rpxnow.com/js/lib/rpx.js).
 * 
 * @author Marcel Overdijk
 * @since 1.0
 */
public class LoadTag extends AbstractBaseTag {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        
        StringWriter sw = new StringWriter();
        sw.append("<script type=\"text/javascript\">\n");
        sw.append("    var rpxJsHost = ((\"https:\" == document.location.protocol) ? \"https://\" : \"http://static.\");\n");
        sw.append("    document.write(unescape(\"%3Cscript src='\" + rpxJsHost + \"rpxnow.com/js/lib/rpx.js' type='text/javascript'%3E%3C/script%3E\"));\n");
        sw.append("</script>");
        
        out.println(sw.toString());
    }
}
