package com.googlecode.janrain4j.samples.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/account")
public class AccountController {

    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired private DatastoreService datastoreService;
    @Autowired private EngageService engageService;
    
    @RequestMapping(value = "/show")
    public String show(HttpServletRequest request, HttpSession session, Model model) {
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        try {
            // get mapped identifiers
            MappingsResponse mappingsResponse = engageService.mappings(String.valueOf(primaryKey));
            List<String> mappings = mappingsResponse.getMappings();
            model.addAttribute("mappings", mappings);
        }
        catch (EngageFailureException e) {
            log.error("Unable to get mappings", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your mappings. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to get mappings", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your mappings. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpSession session) {
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        if (primaryKey != null) {
            try {
                // unmap all identifiers from the primary key
                log.info("Calling unmap for primary key [" + primaryKey + "]...");
                engageService.unmap(String.valueOf(primaryKey));
                
                // delete account from datastore
                log.info("Deleting account from datastore...");
                datastoreService.delete(KeyFactory.createKey("Account", primaryKey));
                
                // remove signed in account from session
                session.removeAttribute("primaryKey");
                session.removeAttribute("userData");
                session.removeAttribute("plainResponse");
                session.removeAttribute("setStatusSupported");
                session.removeAttribute("activitySupported");
                
                FlashScope.setAttribute(request, "message", "Your account is deleted. Register again by signing in anytime.");
                
                return "redirect:/";
            }
            catch (EngageFailureException e) {
                log.error("Unable to delete account", e);
                FlashScope.setAttribute(request, "message", "An error occured while deleting your account. Please try again.");
                FlashScope.setAttribute(request, "level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to delete account", e);
                FlashScope.setAttribute(request, "message", "An error occured while deleting your account. Please try again.");
                FlashScope.setAttribute(request, "level", "error");
            }
        }
        
        return "redirect:/account/show";
    }
    
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public String mapIdentifier(HttpServletRequest request, HttpSession session, @RequestParam String token) {
        
        log.info("Parameter token = " + token);
        
        try {
            // get user data from janrain
            log.info("Calling auth_info...");
            UserDataResponse userDataResponse = engageService.authInfo(token);
            
            log.info("auth_info json response:\n" + userDataResponse.getResponseAsJSONObject().toString(2));
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();
            
            // get signed in primary key
            Long primaryKey = (Long) session.getAttribute("primaryKey");
            
            // map identifier to primary key
            log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            try {
                engageService.map(identifier, String.valueOf(primaryKey), false);
                FlashScope.setAttribute(request, "message", "The identifier " + identifier + " is now mapped to your account.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to map identifier", e);
                FlashScope.setAttribute(request, "message", "An error occured while adding the identifier to your account. Please try again.");
                FlashScope.setAttribute(request, "level", "error");
            }
            catch (ErrorResponeException e) {
                if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                    log.info("Identifier [" + identifier + "] is already mapped to another acoount");
                    FlashScope.setAttribute(request, "message", "The identifier " + identifier + " is already mapped to another account.");
                    FlashScope.setAttribute(request, "level", "error");
                }
                else {
                    log.error("Unable to map identifier", e);
                    FlashScope.setAttribute(request, "message", "An error occured while adding the identifier to your account. Please try again.");
                    FlashScope.setAttribute(request, "level", "error");
                }
            }
        }
        catch (EngageFailureException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (JSONException e) {
            log.error("Unable to map identifier", e);
            FlashScope.setAttribute(request, "message", "An error occured while retrieving your user profile. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "redirect:/account/show";
    }
    
    @RequestMapping(value = "/unmap")
    public String unmapIdentifier(HttpServletRequest request, HttpSession session, @RequestParam String identifier) {
        
        log.info("Parameter identifier = " + identifier);
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        try {
            // unmap identifier from primary key
            log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            engageService.unmap(identifier, String.valueOf(primaryKey));
            FlashScope.setAttribute(request, "message", "The identifier " + identifier + " is unmapped from your account.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to unmap identifier", e);
            FlashScope.setAttribute(request, "message", "An error occured while unmapping identifier. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to unmap identifier", e);
            FlashScope.setAttribute(request, "message", "An error occured while unmapping identifier. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "redirect:/account/show";
    }
}
