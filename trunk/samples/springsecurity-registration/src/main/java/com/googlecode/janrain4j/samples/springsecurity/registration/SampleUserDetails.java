package com.googlecode.janrain4j.samples.springsecurity.registration;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.springframework.security.JanrainUserDetails;

@SuppressWarnings("serial")
public class SampleUserDetails extends JanrainUserDetails {

    private boolean newUser = false;
    
    public SampleUserDetails(UserDataResponse userDataResponse) {
        super(userDataResponse);
    }
    
    public SampleUserDetails(UserDataResponse userDataResponse, boolean newUser) {
        super(userDataResponse);
        this.newUser = newUser;
    }
    
    public boolean isNewUser() {
        return newUser;
    }
}
