package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.Profile;
import com.googlecode.janrain4j.api.engage.UserData;

@SuppressWarnings("serial")
public class TokenServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String token = req.getParameter("token");
        
        log.info("Parameter token = " + token);
        
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        log.info("Calling auth_info...");
        
        UserData userData = engageService.authInfo(token);
        Profile profile = userData.getProfile();
        String identifier = profile.getIdentifier();
        
        String message = "";
        
        Entity account = null;
        
        Long primaryKey = null;
        try {
            primaryKey = Long.parseLong(profile.getPrimaryKey());
        }
        catch (NumberFormatException ignore) {
        }
        
        if (primaryKey != null) {
            log.info("Primary key [" + primaryKey + "] in profile, retrieving account from datastore...");
            try {
                account = datastoreService.get(KeyFactory.createKey("Account", primaryKey));
                message = "Welcome back! ";
            }
            catch (EntityNotFoundException e) {
                log.info("Account not found in datastore for primary key [" + primaryKey + "]");
            }
        }
        
        if (account == null) {
            log.info("Creating new account...");
            account = new Entity("Account");
            datastoreService.put(account);
            log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            primaryKey = account.getKey().getId();
            engageService.map(identifier, String.valueOf(primaryKey));
            message = "Thanks for registering!";
        }
        
        req.getSession().setAttribute("primaryKey", primaryKey);
        req.getSession().setAttribute("userData", userData);
        
        resp.sendRedirect("user_data.jsp?message=" + message);
    }
}
