package com.googlecode.janrain4j.springframework.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

public class JanrainAuthenticationProviderTest {

    private JanrainAuthenticationProvider provider = null;
    
    private JanrainAuthenticationToken authentication = null;
    private UserDataResponse userDataResponse = null;
    private Profile profile = null;
    private String identifier = "my-identifier";
    
    @Before
    public void setUp() {
        provider = new JanrainAuthenticationProvider();
        authentication = mock(JanrainAuthenticationToken.class);
        
        userDataResponse = mock(UserDataResponse.class);
        profile = mock(Profile.class);
        
        when(userDataResponse.getProfile()).thenReturn(profile);
        when(profile.getIdentifier()).thenReturn(identifier);
        when(authentication.getUserDataResponse()).thenReturn(userDataResponse);
    }
    
    @Test
    public void testAuthenticate() {
        Authentication result = provider.authenticate(authentication);
        JanrainAuthenticationToken janrainAuthenticationToken = (JanrainAuthenticationToken) result;
        
        assertEquals(userDataResponse, janrainAuthenticationToken.getUserDataResponse());
    }
    
    @Test
    public void testSupportsAuthentication() {
        assertFalse(provider.supports(Authentication.class));
    }
    
    @Test
    public void testSupportsJanrainAuthenticationToken() {
        assertTrue(provider.supports(JanrainAuthenticationToken.class));
    }
}
