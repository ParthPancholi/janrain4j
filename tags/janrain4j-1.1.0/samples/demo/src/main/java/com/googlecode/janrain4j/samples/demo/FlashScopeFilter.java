package com.googlecode.janrain4j.samples.demo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class FlashScopeFilter extends OncePerRequestFilter {

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Map<String, Object> flash = (Map<String, Object>) session.getAttribute(FlashScope.FLASH_SCOPE_ATTRIBUTE);
            if (flash != null) {
                request.setAttribute(FlashScope.FLASH_SCOPE_ATTRIBUTE, flash);
                session.removeAttribute(FlashScope.FLASH_SCOPE_ATTRIBUTE);
            }
        }
        filterChain.doFilter(request, response);
    }
}
