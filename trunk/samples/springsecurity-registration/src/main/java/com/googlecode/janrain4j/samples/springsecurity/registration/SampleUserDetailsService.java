package com.googlecode.janrain4j.samples.springsecurity.registration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;
import com.googlecode.janrain4j.springframework.security.JanrainAuthenticationToken;
import com.googlecode.janrain4j.springframework.security.JanrainUserDetails;

public class SampleUserDetailsService implements AuthenticationUserDetailsService {

    private final Map<String, JanrainUserDetails> users = new HashMap<String, JanrainUserDetails>();
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        
        JanrainUserDetails userDetails = null;
        
        if (token instanceof JanrainAuthenticationToken) {
            
            JanrainAuthenticationToken janrainAuthenticationToken = (JanrainAuthenticationToken) token;
            
            // get user data response
            UserDataResponse userDataResponse = janrainAuthenticationToken.getUserDataResponse();
            Profile profile = userDataResponse.getProfile();
            String identifier = profile.getIdentifier();
            
            userDetails = users.get(identifier);
            
            if (userDetails == null) {
                List authorities = Arrays.asList(Role.UNREGISTERED_USER);
                userDetails = new JanrainUserDetails(userDataResponse, authorities);
                users.put(identifier, userDetails);
            }
        }
        
        return userDetails;
    }
}
