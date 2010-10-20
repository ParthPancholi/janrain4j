package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SignOutServlet extends HttpServlet {
    
    private static final long serialVersionUID = -2386164453030372120L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // get signed in primary key
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        if (primaryKey != null) {
            log.info("Signing out account with primary key: " + primaryKey);
            
            // remove signed in account from session
            req.getSession().removeAttribute("primaryKey");
            req.getSession().removeAttribute("userData");
            
            flashScope.setAttribute("message", "You are signed out now. Sign in again anytime.");
        }
        
        resp.sendRedirect("index.jsp");
    }
}
