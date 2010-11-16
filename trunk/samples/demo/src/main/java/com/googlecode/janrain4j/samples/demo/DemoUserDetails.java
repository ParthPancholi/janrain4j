package com.googlecode.janrain4j.samples.demo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

@SuppressWarnings("serial")
public class DemoUserDetails extends User {

    private Long primaryKey = null;
    private UserDataResponse userDataResponse = null;
    
    public DemoUserDetails(Long primaryKey, UserDataResponse userDataResponse) {
        super(userDataResponse.getProfile().getIdentifier(), "unused", true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
        this.primaryKey = primaryKey;
        this.userDataResponse = userDataResponse;
    }
    
    public Long getPrimaryKey() {
        return primaryKey;
    }
    
    public UserDataResponse getUserDataResponse() {
        return userDataResponse;
    }
}
