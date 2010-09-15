package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;

public class UnmapServlet extends HttpServlet {

    private static final long serialVersionUID = 2627056642632769263L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // create services
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        // get identifier to unmap from request
        String identifier = req.getParameter("identifier");
        log.info("Parameter identifier = " + identifier);
        
        // get signed in primary key
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        // unmap identifier from primary key
        log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
        engageService.unmap(identifier, String.valueOf(primaryKey));
        
        flashScope.setAttribute("message", "The identifier " + identifier + " is unmapped from your account.");
        
        resp.sendRedirect("account.jsp");
    }
}
