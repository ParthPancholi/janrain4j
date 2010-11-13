package com.googlecode.janrain4j.samples.basic.springframework;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignOutController {

    @RequestMapping("/sign_out")
    public String signOut(HttpSession session) {
        session.removeAttribute("userData");
        session.removeAttribute("plainResponse");
        return "redirect:/";
    }
}
