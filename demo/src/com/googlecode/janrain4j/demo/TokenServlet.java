package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        log.info("Calling auth_info...");
        
        UserData userData = engageService.authInfo(token);
        Profile profile = userData.getProfile();
        String identifier = profile.getIdentifier();
        
        String message = "";
        
        User user = null;
        
        if (StringUtils.isBlank(profile.getPrimaryKey())) {
            log.info("Primary key not in profile, creating new user...");
            user = new User();
            try {
                pm.makePersistent(user);
                String primaryKey = Long.toString(user.getId());
                log.info("Calling map for identifier [" + identifier + "], primary key [" + primaryKey + "]...");
                engageService.map(identifier, primaryKey);
                message = "Thanks for registering!";
            }
            finally {
                pm.close();
            }
        }
        else {
            String primaryKey = profile.getPrimaryKey();
            log.info("Primary key [" + primaryKey + "] in profile, retrieving user from datastore...");
            user = pm.getObjectById(User.class, Long.parseLong(primaryKey));
            message = "Welcome back! ";
        }
        
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("userData", userData);
        
        resp.sendRedirect("signedin.jsp?message=" + message);
    }
}
