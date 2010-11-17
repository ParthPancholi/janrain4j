package com.googlecode.janrain4j.samples.demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.MappingsResponse;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.json.JSONException;
import com.googlecode.janrain4j.springframework.security.JanrainAuthenticationToken;

@Controller
@RequestMapping("/account")
public class AccountController {

    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired private DatastoreService datastoreService;
    @Autowired private EngageService engageService;
    
    @RequestMapping(value = "/show")
    public String show(Model model) {
        
        // get signed in primary key
        Long primaryKey = getPrimaryKey();
        
        try {
            // get mapped identifiers
            MappingsResponse mappingsResponse = engageService.mappings(String.valueOf(primaryKey));
            List<String> mappings = mappingsResponse.getMappings();
            model.addAttribute("mappings", mappings);
        }
        catch (EngageFailureException e) {
            log.error("Unable to get mappings", e);
            FlashScope.setAttribute("message", "An error occured while retrieving your mappings. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to get mappings", e);
            FlashScope.setAttribute("message", "An error occured while retrieving your mappings. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public String map(@RequestParam String token) {
        
        log.info("Parameter token = " + token);
        
        try {
            // get user data from janrain
            log.info("Calling auth_info...");
            UserDataResponse userDataResponse = engageService.authInfo(token);
            
            log.info("auth_info json response:\n" + userDataResponse.getResponseAsJSONObject().toString(2));
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();
            
            // get signed in primary key
            Long primaryKey = getPrimaryKey();
            
            // map identifier to primary key
            log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            try {
                engageService.map(identifier, String.valueOf(primaryKey), false);
                FlashScope.setAttribute("message", "The identifier " + identifier + " is now mapped to your account.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to map identifier", e);
                FlashScope.setAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                FlashScope.setAttribute("level", "error");
            }
            catch (ErrorResponeException e) {
                if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                    log.info("Identifier [" + identifier + "] is already mapped to another acoount");
                    FlashScope.setAttribute("message", "The identifier " + identifier + " is already mapped to another account.");
                    FlashScope.setAttribute("level", "error");
                }
                else {
                    log.error("Unable to map identifier", e);
                    FlashScope.setAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                    FlashScope.setAttribute("level", "error");
                }
            }
        }
        catch (EngageFailureException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        catch (JSONException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        
        return "redirect:/account/show";
    }
    
    @RequestMapping(value = "/unmap")
    public String unmap(@RequestParam String identifier) {
        
        log.info("Parameter identifier = " + identifier);
        
        // get signed in primary key
        Long primaryKey = getPrimaryKey();
        
        try {
            // unmap identifier from primary key
            log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            engageService.unmap(identifier, String.valueOf(primaryKey));
            FlashScope.setAttribute("message", "The identifier " + identifier + " is unmapped from your account.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to unmap identifier", e);
            FlashScope.setAttribute("message", "An error occured while unmapping identifier. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to unmap identifier", e);
            FlashScope.setAttribute("message", "An error occured while unmapping identifier. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        
        return "redirect:/account/show";
    }
    
    @RequestMapping(value = "/delete")
    public String delete() {
        
        // get signed in primary key
        Long primaryKey = getPrimaryKey();
        
        if (primaryKey != null) {
            try {
                // unmap all identifiers from the primary key
                log.info("Calling unmap for primary key [" + primaryKey + "]...");
                engageService.unmap(String.valueOf(primaryKey));
                
                // delete account from datastore
                log.info("Deleting account from datastore...");
                datastoreService.delete(KeyFactory.createKey("Account", primaryKey));
                
                // remove signed in account from session
                SecurityContextHolder.getContext().setAuthentication(null);
                
                FlashScope.setAttribute("message", "Your account is deleted. Register again by signing in anytime.");
                
                return "redirect:/";
            }
            catch (EngageFailureException e) {
                log.error("Unable to delete account", e);
                FlashScope.setAttribute("message", "An error occured while deleting your account. Please try again.");
                FlashScope.setAttribute("level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to delete account", e);
                FlashScope.setAttribute("message", "An error occured while deleting your account. Please try again.");
                FlashScope.setAttribute("level", "error");
            }
        }
        
        return "redirect:/account/show";
    }
    
    private Long getPrimaryKey() {
        JanrainAuthenticationToken token = (JanrainAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        DemoUserDetails userDetails = (DemoUserDetails) token.getPrincipal();
        return userDetails.getPrimaryKey();
    }
}
