package com.googlecode.janrain4j.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        Map<String, Object> flash = new HashMap<String, Object>();
        
        String identifier = req.getParameter("identifier");
        
        log.info("Parameter identifier = " + identifier);
        
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
        
        engageService.unmap(identifier, String.valueOf(primaryKey));
        
        String message = "Successfully unmapped identifier: " + identifier;
        
        resp.sendRedirect("account.jsp?message=" + message);
    }
}
