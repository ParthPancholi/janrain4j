package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.Profile;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

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
        
        log.info("Calling auth_info...");
        
        // get user data from janrain
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
        catch (ErrorResponeException e) {
            if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                flashScope.setAttribute("level", "error");
                flashScope.setAttribute("message", "The identifier " + identifier + " is already mapped to another account.");
                log.info("Identifier [" + identifier + "] is already mapped to another acoount");
            }
            else {
                throw e;
            }
        }
        
        resp.sendRedirect("account.jsp");
    }
}
