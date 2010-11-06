package com.googlecode.janrain4j.samples.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FlashScope {

    protected static final String FLASH_SCOPE_ATTRIBUTE = "flashScope";
    
    private FlashScope() {
    }
    
    public static Object getAttribute(HttpServletRequest request, String name) {
        return getCurrent(request).get(name);
    }
    
    public static void removeAttribute(HttpServletRequest request, String name) {
        getCurrent(request).remove(name);
    }
    
    public static void setAttribute(HttpServletRequest request, String name, Object value) {
        getCurrent(request).put(name, value);
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getCurrent(HttpServletRequest request) {
        HttpSession session = request.getSession(); 
        Map<String, Object> flash = (Map<String, Object>) session.getAttribute(FLASH_SCOPE_ATTRIBUTE);
        if (flash == null) {
            flash = new HashMap<String, Object>();
            session.setAttribute(FLASH_SCOPE_ATTRIBUTE, flash);
        }
        return flash;
    }
}
