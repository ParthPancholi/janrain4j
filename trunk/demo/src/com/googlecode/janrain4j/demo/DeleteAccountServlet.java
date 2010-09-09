package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;

@SuppressWarnings("serial")
public class DeleteAccountServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        log.info("Calling unmap for primary key [" + primaryKey + "]...");
        
        engageService.unmap(String.valueOf(primaryKey));
        
        log.info("Deleting account from datastore...");
        
        Account e = pm.getObjectById(Account.class, primaryKey);
        pm.deletePersistent(e);
        
        req.getSession().removeAttribute("primaryKey");
        req.getSession().removeAttribute("userData");
        
        String message = "Successfully deleted account";
        
        resp.sendRedirect("index.jsp?message=" + message);
    }
}
