package com.googlecode.janrain4j.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.janrain4j.api.engage.Address;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.Name;
import com.googlecode.janrain4j.api.engage.Profile;
import com.googlecode.janrain4j.api.engage.UserData;

@SuppressWarnings("serial")
public class TokenServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String token = req.getParameter("token");
        
        System.out.println("token = " + token);
        
        EngageService engageService = EngageServiceFactory.getInstance();
        
        UserData userData = engageService.authInfo(token);
        Profile profile = userData.getProfile();
        Name name = profile.getName();
        Address address = profile.getAddress();
        
        if ("Twitter".equals(profile.getProviderName())) {
            System.out.println("Sending status update to Twitter...");
            engageService.setStatus(profile.getIdentifier(), "Testing janrain4j set_status call...");
        }
        
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("identifier = " + profile.getIdentifier());
        out.println("provider name = " + profile.getProviderName());
        out.println("primary key = " + profile.getPrimaryKey());
        out.println("display name = " + profile.getDisplayName());
        out.println("preferred username = " + profile.getPreferredUsername());
        out.println("formatted name = " + name.getFormatted());
        out.println("family name = " + name.getFamilyName());
        out.println("given name = " + name.getGivenName());
        out.println("middle name = " + name.getMiddleName());
        out.println("honorific prefix = " + name.getHonorificPrefix());
        out.println("honorific suffix = " + name.getHonorificSuffix());
        out.println("gender = " + profile.getGender());
        out.println("birthday = " + profile.getBirthday());
        out.println("utc offset = " + profile.getUtcOffset());
        out.println("email = " + profile.getEmail());
        out.println("verified email = " + profile.getVerifiedEmail());
        out.println("url = " + profile.getUrl());
        out.println("phone number = " + profile.getPhoneNumber());
        out.println("photo = " + profile.getPhoto());
        out.println("formatted address = " + address.getFormatted());
        out.println("street address = " + address.getStreetAddress());
        out.println("locality = " + address.getLocality());
        out.println("region = " + address.getRegion());
        out.println("postal code = " + address.getPostalCode());
        out.println("country = " + address.getCountry());
        out.println("limited data = " + profile.hasLimitedData());
    }
}
