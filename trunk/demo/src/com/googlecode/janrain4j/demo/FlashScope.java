package com.googlecode.janrain4j.demo;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FlashScope {

    public static final String FLASH_SCOPE = "flashScope";
    
    HttpServletRequest request = null;
    
    public FlashScope(HttpServletRequest request) {
        this.request = request;
    }

    public Enumeration<String> getAttributeNames() {
        Map<String, Object> flashAttributes = (Map) request.getAttribute(FLASH_SCOPE);
        return Collections.enumeration(flashAttributes.keySet());
        // check if flashAttributes is null
    }
    
    public Object getAttribute(String name) {
        Map<String, Object> flashAttributes = (Map) request.getAttribute(FLASH_SCOPE);
        return flashAttributes.get(name);
        // check if flashAttributes is null
    }
    
    public void removeAttribute(String name) {
        Map<String, Object> flashAttributes = (Map) request.getAttribute(FLASH_SCOPE);
        flashAttributes.remove(name);
        // todo remove also from session, check if flashAttributes is null
    }
    
    public void setAttribute(String name, Object o) {
        Map<String, Object> flashAttributes = (Map) request.getAttribute(FLASH_SCOPE);
        flashAttributes.put(name, o);
        // todo set also in session, check if flashAttributes is null
    }
}
