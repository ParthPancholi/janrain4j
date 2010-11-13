package com.googlecode.janrain4j.samples.basic.springframework;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.json.JSONException;

@Controller
public class TokenController {

    @Autowired private EngageService engageService;
    
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String signIn(@RequestParam String token, Model model, HttpSession session) {
        try {
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            session.setAttribute("userData", userDataResponse);
            session.setAttribute("plainResponse", userDataResponse.getResponseAsJSONObject().toString(2));
            return "redirect:/user_data";
        }
        catch (EngageFailureException e) {
            model.addAttribute("error", e);
            return "error";
        }
        catch (ErrorResponeException e) {
            model.addAttribute("error", e);
            return "error";
        }
        catch (JSONException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }
}
