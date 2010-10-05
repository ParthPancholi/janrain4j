package com.googlecode.janrain4j.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.request.ActionLink;
import com.googlecode.janrain4j.api.engage.request.Activity;
import com.googlecode.janrain4j.api.engage.request.ImageMediaItem;
import com.googlecode.janrain4j.api.engage.request.MediaItem;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

public class ActivityServlet extends HttpServlet {

    private static final long serialVersionUID = -7389262088593993019L;
    
    private Log log = LogFactory.getLog(this.getClass());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // create flash scope
        FlashScope flashScope = new FlashScope(req);
        
        // get user generated content from request
        String userGeneratedContent = req.getParameter("userGeneratedContent");
        log.info("Parameter userGeneratedContent = " + userGeneratedContent);
        
        // get signed in identifier
        UserDataResponse userDataResponse = (UserDataResponse) req.getSession().getAttribute("userData");
        String identifier = userDataResponse.getProfile().getIdentifier();
        
        // create services
        EngageService engageService = EngageServiceFactory.getEngageService();
            
        // activity
        Activity activity = new Activity("http://janrain4j.appspot.com/", "signed in to the Jarain4j Demo Application!");
        if (StringUtils.isNotBlank(userGeneratedContent)) {
            activity.setUserGeneratedContent(userGeneratedContent);
        }
        activity.setTitle("Janrain4j");
        activity.setDescription("Janrain4j is an open-source Java library for the Janrain API.");
        
        // action links
        List<ActionLink> actionLinks = new ArrayList<ActionLink>();
        actionLinks.add(new ActionLink("Try out the Janrain4j Demo Application", "http://janrain4j.appspot.com/"));
        actionLinks.add(new ActionLink("Learn more about Janrain4j", "http://janrain4j.googlecode.com/"));
        activity.setActionLinks(actionLinks);
        
        // media
        List<MediaItem> media = new ArrayList<MediaItem>();
        media.add(new ImageMediaItem("http://janrain4j.appspot.com/images/janrain-engage-signin.png", "http://janrain4j.appspot.com/"));
        activity.setMedia(media);
        
        try {
            // update activity
            log.info("Calling activity for identifier [" + identifier + "]...");
            engageService.activity(identifier, activity);
            flashScope.setAttribute("message", "Your activity is updated.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to update activity", e);
            flashScope.setAttribute("message", "An error occured while updating your activity. Please try again.");
            flashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to update activity", e);
            flashScope.setAttribute("message", "An error occured while updating your activity. Please try again.");
            flashScope.setAttribute("level", "error");
        }
        
        resp.sendRedirect("social_publishing.jsp");
    }
}
