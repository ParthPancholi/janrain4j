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
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        User user = (User) req.getSession().getAttribute("user");
        String primaryKey = String.valueOf(user.getId());
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        log.info("Calling unmap for primary key [" + primaryKey + "]...");
        
        engageService.unmap(primaryKey);
        
        log.info("Deleting user from datastore...");
        
        User e = pm.getObjectById(User.class, user.getId());
        pm.deletePersistent(e);
        
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("userData");
        
        String message = "Successfully deleted account";
        
        resp.sendRedirect("index.jsp?message=" + message);
    }
}
