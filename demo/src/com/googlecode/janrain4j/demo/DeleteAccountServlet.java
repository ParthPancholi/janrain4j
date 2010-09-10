package com.googlecode.janrain4j.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;

@SuppressWarnings("serial")
public class DeleteAccountServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        Map<String, Object> flash = new HashMap<String, Object>();
        
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        String message = "";
        
        if (primaryKey != null) {
        
            DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
            EngageService engageService = EngageServiceFactory.getEngageService();
            
            log.info("Calling unmap for primary key [" + primaryKey + "]...");
            
            engageService.unmap(String.valueOf(primaryKey));
            
            log.info("Deleting account from datastore...");
            
            datastoreService.delete(KeyFactory.createKey("Account", primaryKey));
            
            req.getSession().removeAttribute("primaryKey");
            req.getSession().removeAttribute("userData");
            
            message = "Successfully deleted account";
        }
        
        resp.sendRedirect("index.jsp?message=" + message);
    }
}
