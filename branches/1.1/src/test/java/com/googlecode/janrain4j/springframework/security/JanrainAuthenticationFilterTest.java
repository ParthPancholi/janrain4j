package com.googlecode.janrain4j.springframework.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

public class JanrainAuthenticationFilterTest {

    private JanrainAuthenticationFilter filter = null;
    
    private Authentication authentication = null;
    private AuthenticationManager authenticationManager = null;
    
    private EngageService engageService = null;
    
    private UserDataResponse userDataResponse = null;
    private Profile profile = null;
    private String identifier = "my-identifier";
    
    private MockHttpServletRequest request = null;
    private MockHttpServletResponse response = null;
    
    private String token = "token";
    
    @Before
    public void setUp() {
        authentication = mock(Authentication.class);
        authenticationManager = mock(AuthenticationManager.class);
        
        engageService = mock(EngageService.class);
        userDataResponse = mock(UserDataResponse.class);
        profile = mock(Profile.class);
        
        when(userDataResponse.getProfile()).thenReturn(profile);
        when(profile.getIdentifier()).thenReturn(identifier);
        
        filter = new JanrainAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setEngageService(engageService);
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    
    @Test
    public void testAttemptAuthenticationWithExtendedIsTrue() throws Exception {
        filter.setExtended(true);
        
        request.addParameter("token", token);
        when(engageService.authInfo(token, true)).thenReturn(userDataResponse);
        when(authenticationManager.authenticate((JanrainAuthenticationToken) any())).thenReturn(authentication);
        
        assertEquals(authentication, filter.attemptAuthentication(request, response));
    }
    
    @Test
    public void testAttemptAuthenticationWithExtendedIsFalse() throws Exception {
        filter.setExtended(false);
        
        request.addParameter("token", token);
        when(engageService.authInfo(token, false)).thenReturn(userDataResponse);
        when(authenticationManager.authenticate((JanrainAuthenticationToken) any())).thenReturn(authentication);
        
        assertEquals(authentication, filter.attemptAuthentication(request, response));
    }
    
    @Test
    public void testAttemptAuthenticationWithoutToken() throws Exception {
        assertNull(filter.attemptAuthentication(request, response));
        
        verify(authenticationManager, never()).authenticate((JanrainAuthenticationToken) any());
        verify(engageService, never()).authInfo(anyString(), anyBoolean());
    }
    
    @Test(expected = AuthenticationServiceException.class)
    public void testAttemptAuthenticationWithEngageFailureException() throws Exception {
        request.addParameter("token", token);
        when(engageService.authInfo(token, false)).thenThrow(new EngageFailureException("Some error", "Some json"));
        filter.attemptAuthentication(request, response);
    }
    
    @Test(expected = AuthenticationServiceException.class)
    public void testAttemptAuthenticationWithErrorResponeException() throws Exception {
        request.addParameter("token", token);
        when(engageService.authInfo(token, false)).thenThrow(new ErrorResponeException(0, "Some error", "Some json"));
        filter.attemptAuthentication(request, response);
    }
}
