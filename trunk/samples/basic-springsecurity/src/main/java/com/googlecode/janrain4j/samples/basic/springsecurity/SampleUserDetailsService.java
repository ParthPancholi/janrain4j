package com.googlecode.janrain4j.samples.basic.springsecurity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SampleUserDetailsService implements AuthenticationUserDetailsService {

    private Log log = LogFactory.getLog(this.getClass());
    
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        // TODO
        log.debug("[*** MARCEL ***] loadUserDetails called...");
        return null;
    }
}
