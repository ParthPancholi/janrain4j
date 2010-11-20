package com.googlecode.janrain4j.samples.springsecurity.registration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class SampleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    private String defaultTargetUrl = null;
    private String registrationUrl = null;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = null;
        if (authentication.getAuthorities().contains(Role.REGISTERED_USER)) {
            targetUrl = defaultTargetUrl;
        }
        else {
            targetUrl = registrationUrl;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    public void setDefaultTargetUrl(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }
    
    public void setRegistrationUrl(String registrationUrl) {
        this.registrationUrl = registrationUrl;
    }
}
