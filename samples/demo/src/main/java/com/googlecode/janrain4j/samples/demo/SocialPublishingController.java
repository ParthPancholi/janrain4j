package com.googlecode.janrain4j.samples.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.springframework.security.JanrainAuthenticationToken;

@Controller
@RequestMapping("/social_publishing")
public class SocialPublishingController {

    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired private EngageService engageService;
    
    @RequestMapping
    public String index(Model model) {
        
        String providerName = getProfile().getProviderName();
        
        model.addAttribute("supportsSetStatus", engageService.supportsSetStatus(providerName));
        model.addAttribute("supportsActivity", engageService.supportsActivity(providerName));
        
        return "social_publishing";
    }
    
    @RequestMapping(value = "/set_status", method = RequestMethod.POST)
    public String setStatus(@RequestParam String message) {
        
        log.info("Parameter message = " + message);
        
        // get signed in identifier
        String identifier = getProfile().getIdentifier();
        
        if (StringUtils.isNotBlank(message)) {
            try {
                // set status
                log.info("Calling set_status for identifier [" + identifier + "]...");
                engageService.setStatus(identifier, message);
                FlashScope.setAttribute("message", "Your status is updated.");
            }
            catch (EngageFailureException e) {
                log.error("Unable to set status", e);
                FlashScope.setAttribute("message", "An error occured while setting your status. Please try again.");
                FlashScope.setAttribute("level", "error");
            }
            catch (ErrorResponeException e) {
                log.error("Unable to set status", e);
                FlashScope.setAttribute("message", "An error occured while setting your status. Please try again.");
                FlashScope.setAttribute("level", "error");
            }
        }
        else {
            log.info("Skipping set_status as parameter message is empty");
            FlashScope.setAttribute("message", "No status message was entered. Status is not updated.");
            FlashScope.setAttribute("level", "error");
        }
        
        return "redirect:/social_publishing";
    }
    
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String activity(@RequestParam String userGeneratedContent) {
        
        log.info("Parameter userGeneratedContent = " + userGeneratedContent);
        
        // get signed in identifier
        String identifier = getProfile().getIdentifier();
        
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
            FlashScope.setAttribute("message", "Your activity is updated.");
        }
        catch (EngageFailureException e) {
            log.error("Unable to update activity", e);
            FlashScope.setAttribute("message", "An error occured while updating your activity. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        catch (ErrorResponeException e) {
            log.error("Unable to update activity", e);
            FlashScope.setAttribute("message", "An error occured while updating your activity. Please try again.");
            FlashScope.setAttribute("level", "error");
        }
        
        return "redirect:/social_publishing";
    }
    
    private Profile getProfile() {
        JanrainAuthenticationToken token = (JanrainAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDataResponse userDataResponse = token.getUserDataResponse();
        return userDataResponse.getProfile();
    }
}
