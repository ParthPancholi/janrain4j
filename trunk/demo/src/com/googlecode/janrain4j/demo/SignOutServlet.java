package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class SignOutServlet extends HttpServlet {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        String message = "";
        
        if (primaryKey != null) {
            log.info("Signing out account with primary key: " + primaryKey);
            req.getSession().removeAttribute("primaryKey");
            req.getSession().removeAttribute("userData");
            message = "Successfully signed out";
        }
        
        resp.sendRedirect("index.jsp?message=" + message);
    }
}
