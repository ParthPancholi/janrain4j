package com.googlecode.janrain4j.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FlashScope {

    public static final String FLASH_SCOPE = "flash";
    
    HttpServletRequest request = null;
    
    public FlashScope(HttpServletRequest request) {
        this.request = request;
    }
    
    public Object getAttribute(String name) {
        return getFlashAttributes().get(name);
    }
    
    public void removeAttribute(String name) {
        getFlashAttributes().remove(name);
    }
    
    public void setAttribute(String name, Object o) {
        getFlashAttributes().put(name, o);
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> getFlashAttributes() {
        Map<String, Object> attributes = (Map) request.getSession().getAttribute(FLASH_SCOPE);
        if (attributes == null) {
            attributes = new HashMap<String, Object>();
            request.getSession().setAttribute(FLASH_SCOPE, attributes);
        }
        return attributes;
    }
}
