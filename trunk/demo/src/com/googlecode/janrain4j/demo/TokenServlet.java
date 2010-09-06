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
        
        UserData userData = engageService.authInfo(token);
        Profile profile = userData.getProfile();
        
        User user = null;
        
        if (StringUtils.isBlank(profile.getPrimaryKey())) {
            log.info("Primary key not in profile, creating new user...");
            user = new User();
            try {
                pm.makePersistent(user);
                engageService.map(profile.getIdentifier(), Long.toString(user.getId()));
            }
            finally {
                pm.close();
            }
        }
        else {
            log.info("Primary key in profile, retrieving user from datastore...");
            user = pm.getObjectById(User.class, Long.parseLong(profile.getPrimaryKey()));
        }
        
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("userData", userData);
        
        resp.sendRedirect("signedin.jsp");
    }
}
