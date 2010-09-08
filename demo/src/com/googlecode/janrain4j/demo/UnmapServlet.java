package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;

@SuppressWarnings("serial")
public class UnmapServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String identifier = req.getParameter("identifier");
        
        log.info("Parameter identifier = " + identifier);
        
        User user = (User) req.getSession().getAttribute("user");
        String primaryKey = String.valueOf(user.getId());
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
        
        engageService.unmap(identifier, primaryKey);
        
        String message = "Successfully unmapped identifier: " + identifier;
        
        resp.sendRedirect("signedin.jsp?message=" + message);
    }
}
