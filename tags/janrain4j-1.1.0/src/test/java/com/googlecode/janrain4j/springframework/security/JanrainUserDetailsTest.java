package com.googlecode.janrain4j.springframework.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

public class JanrainUserDetailsTest {

    private JanrainUserDetails userDetails = null;
    
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
    public void testJanrainUserDetails() {
        userDetails = new JanrainUserDetails(userDataResponse);
        assertEquals(identifier, userDetails.getUsername());
        assertEquals("unused", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(0, userDetails.getAuthorities().size());
        assertEquals(userDataResponse, userDetails.getUserDataResponse());
    }
    
    @Test
    public void testJanrainUserDetailsWithAuthorities() {
        Collection<GrantedAuthority> authorities = Arrays.asList((GrantedAuthority) new GrantedAuthorityImpl("ADMIN"), (GrantedAuthority) new GrantedAuthorityImpl("USER"));
        userDetails = new JanrainUserDetails(userDataResponse, authorities);
        assertEquals(identifier, userDetails.getUsername());
        assertEquals("unused", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().containsAll(authorities));
        assertEquals(userDataResponse, userDetails.getUserDataResponse());
    }
}
