package com.googlecode.janrain4j.springframework.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

public class JanrainAuthenticationTokenTest {

    private JanrainAuthenticationToken token = null;
    
    private UserDataResponse userDataResponse = null;
    private Profile profile = null;
    private String identifier = "my-identifier";
    
    @Before
    public void setUp() {
        userDataResponse = mock(UserDataResponse.class);
        profile = mock(Profile.class);
        
        when(userDataResponse.getProfile()).thenReturn(profile);
        when(profile.getIdentifier()).thenReturn(identifier);
    }
    
    @Test
    public void testJanrainAuthenticationTokenRequest() {
        token = new JanrainAuthenticationToken(userDataResponse);
        assertEquals(identifier, token.getPrincipal());
        assertEquals(AuthorityUtils.NO_AUTHORITIES, token.getAuthorities());
        assertEquals(userDataResponse, token.getUserDataResponse());
        assertFalse(token.isAuthenticated());
    }
    
    @Test
    public void testJanrainAuthenticationTokenResponse() {
        JanrainUserDetails userDetails = new JanrainUserDetails(userDataResponse);
        token = new JanrainAuthenticationToken(userDetails, AuthorityUtils.NO_AUTHORITIES, userDataResponse);
        assertEquals(userDetails, token.getPrincipal());
        assertEquals(0, token.getAuthorities().size());
        assertEquals(userDataResponse, token.getUserDataResponse());
        assertTrue(token.isAuthenticated());
    }
    
    @Test
    public void testJanrainAuthenticationTokenResponseWithAuthorities() {
        JanrainUserDetails userDetails = new JanrainUserDetails(userDataResponse);
        Collection<GrantedAuthority> authorities = Arrays.asList((GrantedAuthority) new GrantedAuthorityImpl("ADMIN"), (GrantedAuthority) new GrantedAuthorityImpl("USER"));
        token = new JanrainAuthenticationToken(userDetails, authorities, userDataResponse);
        assertEquals(userDetails, token.getPrincipal());
        assertEquals(authorities, token.getAuthorities());
        assertEquals(2, token.getAuthorities().size());
        assertTrue(token.getAuthorities().containsAll(authorities));
        assertEquals(userDataResponse, token.getUserDataResponse());
        assertTrue(token.isAuthenticated());
    }
}
