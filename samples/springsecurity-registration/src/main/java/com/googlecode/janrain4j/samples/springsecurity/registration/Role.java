package com.googlecode.janrain4j.samples.springsecurity.registration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    REGISTERED;
    
    @Override
    public String getAuthority() {
        return toString();
    }
}
