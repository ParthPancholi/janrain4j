package com.googlecode.janrain4j.samples.springsecurity.registration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    REGISTERED_USER,
    UNREGISTERED_USER;
    
    @Override
    public String getAuthority() {
        return toString();
    }
}
