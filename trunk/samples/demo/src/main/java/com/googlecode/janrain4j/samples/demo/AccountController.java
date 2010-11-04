package com.googlecode.janrain4j.samples.demo;

import java.util.List;

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
import com.google.appengine.api.utils.SystemProperty;
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
    public String show(HttpSession session, Model model) {
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        try {
            // get mapped identifiers
            MappingsResponse mappingsResponse = engageService.mappings(String.valueOf(primaryKey));
            List<String> mappings = mappingsResponse.getMappings();
            model.addAttribute("mappings", mappings);
            
            // determine token url
            String tokenUrl = "http://janrain4j.appspot.com/account/map";
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
                // overwrite token url in development
                tokenUrl = "http://localhost:8888/account/map";
            }
            model.addAttribute("tokenUrl", tokenUrl);
        }
        catch (EngageFailureException e) {
            log.error("Unable to get mappings", e);
            model.addAttribute("message", "An error occured while retrieving your mappings. Please try again.");
            model.addAttribute("message.level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to get mappings", e);
            model.addAttribute("message", "An error occured while retrieving your mappings. Please try again.");
            model.addAttribute("message.level", "error");
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/delete")
    public String delete(HttpSession session, Model model) {
        
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
                
                session.invalidate();
                
                // remove signed in account from session
                session.invalidate();
                
                model.addAttribute("message", "Your account is deleted. Register again by signing in anytime.");
                
                // TODO ?
                return "redirect:/";
            }
            catch (EngageFailureException e) {
                log.error("Unable to delete account", e);
                model.addAttribute("message", "An error occured while deleting your account. Please try again.");
                model.addAttribute("message.level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to delete account", e);
                model.addAttribute("message", "An error occured while deleting your account. Please try again.");
                model.addAttribute("message.level", "error");
            }
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public String mapIdentifier(@RequestParam String token, HttpSession session, Model model) {
        
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
                model.addAttribute("message", "The identifier " + identifier + " is now mapped to your account.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to map identifier", e);
                model.addAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                model.addAttribute("message.level", "error");
            }
            catch (ErrorResponeException e) {
                if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                    log.info("Identifier [" + identifier + "] is already mapped to another acoount");
                    model.addAttribute("message", "The identifier " + identifier + " is already mapped to another account.");
                    model.addAttribute("message.level", "error");
                }
                else {
                    log.error("Unable to map identifier", e);
                    model.addAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                    model.addAttribute("message.level", "error");
                }
            }
        }
        catch (EngageFailureException e) {
            log.error("Unable to map identifier", e);
            model.addAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            model.addAttribute("message.level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to map identifier", e);
            model.addAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            model.addAttribute("message.level", "error");
        }
        catch (JSONException e) {
            log.error("Unable to map identifier", e);
            model.addAttribute("message", "An error occured while retrieving your user profile. Please try again.");
            model.addAttribute("message.level", "error");
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/unmap")
    public String unmapIdentifier(@RequestParam String identifier, HttpSession session, Model model) {
        
        log.info("Parameter identifier = " + identifier);
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        try {
            // unmap identifier from primary key
            log.info("Calling unmap for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            engageService.unmap(identifier, String.valueOf(primaryKey));
            model.addAttribute("message", "The identifier " + identifier + " is unmapped from your account.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to unmap identifier", e);
            model.addAttribute("message", "An error occured while unmapping identifier. Please try again.");
            model.addAttribute("message.level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to unmap identifier", e);
            model.addAttribute("message", "An error occured while unmapping identifier. Please try again.");
            model.addAttribute("message.level", "error");
        }
        
        return "account";
    }
    
    @RequestMapping(value = "/sign_out")
    public String signOut(HttpSession session, Model model) {
        
        // get signed in primary key
        Long primaryKey = (Long) session.getAttribute("primaryKey");
        
        if (primaryKey != null) {
            log.info("Signing out account with primary key: " + primaryKey);
            
            // remove signed in account from session
            session.invalidate();
            
            model.addAttribute("message", "You are signed out now. Sign in again anytime.");
        }
        
        return "index";
    }
}
