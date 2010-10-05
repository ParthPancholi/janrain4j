package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

public class MapServlet extends HttpServlet {

    private static final long serialVersionUID = 1282116640537385957L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // create services
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        // get janrain token from request
        String token = req.getParameter("token");
        log.info("Parameter token = " + token);
        
        
        
        try {
            // get user data from janrain
            log.info("Calling auth_info...");
            UserDataResponse userDataResponse = engageService.authInfo(token);
            
            log.info("auth_info json response:\n" + userDataResponse.getResponseAsJSON());
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();
            
            // get signed in primary key
            Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
            
            // map identifier to primary key
            log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
            try {
                engageService.map(identifier, String.valueOf(primaryKey), false);
                flashScope.setAttribute("message", "The identifier " + identifier + " is now mapped to your account.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to map identifier", e);
                flashScope.setAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                flashScope.setAttribute("level", "error");
            }
            catch (ErrorResponeException e) {
                if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                    log.info("Identifier [" + identifier + "] is already mapped to another acoount");
                    flashScope.setAttribute("level", "error");
                    flashScope.setAttribute("message", "The identifier " + identifier + " is already mapped to another account.");
                }
                else {
                    log.error("Unable to map identifier", e);
                    flashScope.setAttribute("message", "An error occured while adding the identifier to your account. Please try again.");
                    flashScope.setAttribute("level", "error");
                }
            }
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
        
        resp.sendRedirect("account.jsp");
    }
}
