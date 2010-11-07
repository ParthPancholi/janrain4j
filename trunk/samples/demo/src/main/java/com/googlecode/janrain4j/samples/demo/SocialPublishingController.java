package com.googlecode.janrain4j.samples.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.request.ActionLink;
import com.googlecode.janrain4j.api.engage.request.Activity;
import com.googlecode.janrain4j.api.engage.request.ImageMediaItem;
import com.googlecode.janrain4j.api.engage.request.MediaItem;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

@Controller
@RequestMapping("/social_publishing")
public class SocialPublishingController {

    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired private EngageService engageService;
    
    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public String setStatus(HttpServletRequest request, @RequestParam String message, HttpSession session, Model model) {
        
        log.info("Parameter message = " + message);
        
        // get signed in identifier
        UserDataResponse userDataResponse = (UserDataResponse) session.getAttribute("userData");
        String identifier = userDataResponse.getProfile().getIdentifier();
        
        if (StringUtils.isNotBlank(message)) {
            try {
                // set status
                log.info("Calling set_status for identifier [" + identifier + "]...");
                engageService.setStatus(identifier, message);
                FlashScope.setAttribute(request, "message", "Your status is updated.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to set status", e);
                FlashScope.setAttribute(request, "message", "An error occured while setting your status. Please try again.");
                FlashScope.setAttribute(request, "level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to set status", e);
                FlashScope.setAttribute(request, "message", "An error occured while setting your status. Please try again.");
                FlashScope.setAttribute(request, "level", "error");
            }
        }
        else {
            log.info("Skipping set_status as parameter message is empty");
            FlashScope.setAttribute(request, "message", "No status message was entered. Status is not updated.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "redirect:/social_publishing";
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String activity(HttpServletRequest request, @RequestParam String userGeneratedContent, HttpSession session, Model model) {
        
        log.info("Parameter userGeneratedContent = " + userGeneratedContent);
        
        // get signed in identifier
        UserDataResponse userDataResponse = (UserDataResponse) session.getAttribute("userData");
        String identifier = userDataResponse.getProfile().getIdentifier();
        
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
            FlashScope.setAttribute(request, "message", "Your activity is updated.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to update activity", e);
            FlashScope.setAttribute(request, "message", "An error occured while updating your activity. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to update activity", e);
            FlashScope.setAttribute(request, "message", "An error occured while updating your activity. Please try again.");
            FlashScope.setAttribute(request, "level", "error");
        }
        
        return "redirect:/social_publishing";
    }
}
