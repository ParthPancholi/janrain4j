package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Name;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.json.JSONException;

public class TokenServlet extends HttpServlet {

    private static final long serialVersionUID = -3866603902836322536L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // create services
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        // get janrain token from request
        String token = req.getParameter("token");
        log.info("Parameter token = " + token);
        
        try {
            // get user data from janrain
            log.info("Calling auth_info...");
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            
            log.info("auth_info json response:\n" + userDataResponse.getResponseAsJSON());
            
            Profile profile = userDataResponse.getProfile();
            Name name = profile.getName();
            String identifier = profile.getIdentifier();
            
            String formattedNameOrIdentifier = identifier;
            if (name != null && StringUtils.isNotBlank(name.getFormatted())) {
                formattedNameOrIdentifier = name.getFormatted();
            }
            
            // check if janrain profile contains a primary key (existing account)
            Long primaryKey = null;
            try {
                primaryKey = Long.parseLong(profile.getPrimaryKey());
            }
            catch (NumberFormatException ignore) {
            }
            
            Entity account = null;
            
            // check if account exists in datastore
            if (primaryKey != null) {
                log.info("Primary key [" + primaryKey + "] in profile, retrieving account from datastore...");
                try {
                    account = datastoreService.get(KeyFactory.createKey("Account", primaryKey));
                    flashScope.setAttribute("message", "Welcome back " + formattedNameOrIdentifier + "!");
                }
                catch (EntityNotFoundException e) {
                    log.info("Account not found in datastore for primary key [" + primaryKey + "]");
                }
            }
            
            // if account doesn't exists (or primary key was not in profile), create new account
            if (account == null) {
                log.info("Creating new account...");
                
                // create new account and persist in datastore
                account = new Entity("Account");
                datastoreService.put(account);
                
                // get primary key after creating new account
                primaryKey = account.getKey().getId();
                
                try {
                    // map identifier to primary key
                    log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
                    engageService.map(identifier, String.valueOf(primaryKey));
                }
                catch (Exception ignore) {
                    log.error("Unable to map identifier", ignore);
                }
                
                flashScope.setAttribute("message", "Thanks for registering " + formattedNameOrIdentifier + "!");
            }
            
            req.getSession().setAttribute("primaryKey", primaryKey);
            req.getSession().setAttribute("profile", userDataResponse.getProfile());
            req.getSession().setAttribute("accessCredentials", userDataResponse.getAccessCredentials());
            req.getSession().setAttribute("mergedPoco", userDataResponse.getMergedPoco());
            req.getSession().setAttribute("friends", userDataResponse.getFriends());
            req.getSession().setAttribute("jsonResponse", userDataResponse.getResponseAsJSONObject().toString(2));
            
            resp.sendRedirect("user_data.jsp");
        }
        catch (EngageFailureException e) {
            log.error("Unable to get auth info", e);
            flashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            flashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to get auth info", e);
            flashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            flashScope.setAttribute("level", "error");
        }
        catch (JSONException e) {
            log.error("Unable to get auth info", e);
            flashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            flashScope.setAttribute("level", "error");
        }
        
        resp.sendRedirect("index.jsp");
    }
}
