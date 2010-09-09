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
import com.googlecode.janrain4j.api.engage.Profile;
import com.googlecode.janrain4j.api.engage.UserData;

@SuppressWarnings("serial")
public class MapServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String token = req.getParameter("token");
        
        log.info("Parameter token = " + token);
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        UserData userData = engageService.authInfo(token);
        
        Profile profile = userData.getProfile();
        String identifier = profile.getIdentifier();
        
        Long primaryKey = (Long) req.getSession().getAttribute("primaryKey");
        
        log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
        
        String error = "";
        String message = "";
        
        try {
            engageService.map(identifier, String.valueOf(primaryKey), false);
            message = "Successfully mapped identifier: " + identifier;
        }
        catch (ErrorResponeException e) {
            if (e.getCode() == ErrorResponeException.MAPPING_EXISTS_ERROR) {
                error = "Unable to map identifier: " + e.getMessage();
                log.info(error);
            }
            else {
                throw e;
            }
        }
        
        resp.sendRedirect("mappings.jsp?error=" + error + "&message=" + message);
    }
}
