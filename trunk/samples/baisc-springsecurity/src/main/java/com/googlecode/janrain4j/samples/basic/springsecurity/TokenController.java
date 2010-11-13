package com.googlecode.janrain4j.samples.basic.springsecurity;

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
@RequestMapping("/token")
public class TokenController {

    @Autowired private EngageService engageService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String signIn(@RequestParam String token, Model model) {
        try {
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            model.addAttribute("userData", userDataResponse);
            model.addAttribute("plainResponse", userDataResponse.getResponseAsJSONObject().toString(2));
            return "user_data";
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
