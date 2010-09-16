package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.response.AuthInfoResponse;

public class SetStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 6651944145561358246L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // get status message from request
        String message = req.getParameter("message");
        log.info("Parameter message = " + message);
        
        // get signed in identifier
        AuthInfoResponse authInfoResponse = (AuthInfoResponse) req.getSession().getAttribute("userData");
        String identifier = authInfoResponse.getProfile().getIdentifier();
        
        if (StringUtils.isNotBlank(message)) {
            
            // create services
            EngageService engageService = EngageServiceFactory.getEngageService();
            
            // set status
            log.info("Calling set_status for identifier [" + identifier + "]...");
            engageService.setStatus(identifier, message);
            
            flashScope.setAttribute("message", "Your status is updated.");
        }
        else {
            log.info("Skipping set_status as parameter message is empty");
            flashScope.setAttribute("level", "error");
            flashScope.setAttribute("message", "No status message was entered. Status is not updated.");
        }
        
        resp.sendRedirect("social_publishing.jsp");
    }
}
