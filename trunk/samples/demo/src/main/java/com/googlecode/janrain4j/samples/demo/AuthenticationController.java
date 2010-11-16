package com.googlecode.janrain4j.samples.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Name;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.json.JSONException;

// @Controller
public class AuthenticationController {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired private DatastoreService datastoreService;
    @Autowired private EngageService engageService;
    
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, HttpSession session, @RequestParam String token) {
        try {
            // get user data from janrain
            log.info("Calling auth_info...");
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            String plainResponse = userDataResponse.getResponseAsJSONObject().toString(2);
            
            log.info("auth_info json response:\n" + plainResponse);
            
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();
            String providerName = profile.getProviderName();
            Name name = profile.getName();
            
            // set formatted name or identifier
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
                    FlashScope.setAttribute(request, "message", "Welcome back " + formattedNameOrIdentifier + "!");
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
                
                FlashScope.setAttribute(request, "message", "Thanks for registering " + formattedNameOrIdentifier + "!");
            }
            
            session.setAttribute("primaryKey", primaryKey);
            session.setAttribute("userData", userDataResponse);
            session.setAttribute("plainResponse", plainResponse);
            session.setAttribute("setStatusSupported", engageService.supportsSetStatus(providerName));
            session.setAttribute("activitySupported", engageService.supportsActivity(providerName));
            
            return "redirect:/user_data";
        }
        catch (EngageFailureException e) {
            log.error("Unable to get auth info", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to get auth info", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (JSONException e) {
            log.error("Unable to get auth info", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "index";
    }
}
