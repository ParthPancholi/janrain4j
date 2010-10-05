package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;

public class DeleteAccountServlet extends HttpServlet {

    private static final long serialVersionUID = -668549824844323413L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // get signed in primary key
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        if (primaryKey != null) {
        
            // create services
            DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
            EngageService engageService = EngageServiceFactory.getEngageService();
            
            try {
                // unmap all identifiers from the primary key
                log.info("Calling unmap for primary key [" + primaryKey + "]...");
                engageService.unmap(String.valueOf(primaryKey));
                
                // delete account from datastore
                log.info("Deleting account from datastore...");
                datastoreService.delete(KeyFactory.createKey("Account", primaryKey));
                
                // remove signed in account from session
                req.getSession().removeAttribute("primaryKey");
                req.getSession().removeAttribute("userData");
                
                flashScope.setAttribute("message", "Your account is deleted. Register again by signing in anytime.");
                
                resp.sendRedirect("index.jsp");
            }
            catch (EngageFailureException e) {
                log.error("Unable to unmap identifiers", e);
                flashScope.setAttribute("message", "An error occured while deleting your account. Please try again.");
                flashScope.setAttribute("level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to unmap identifiers", e);
                flashScope.setAttribute("message", "An error occured while deleting your account. Please try again.");
                flashScope.setAttribute("level", "error");
            }
        }
        
        resp.sendRedirect("account.jsp");
    }
}
