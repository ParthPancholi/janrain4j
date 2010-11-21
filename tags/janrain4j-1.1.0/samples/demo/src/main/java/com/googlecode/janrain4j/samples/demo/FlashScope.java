package com.googlecode.janrain4j.samples.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FlashScope {

    protected static final String FLASH_SCOPE_ATTRIBUTE = "flashScope";
    
    private FlashScope() {
    }
    
    public static Object getAttribute(String name) {
        return getCurrent().get(name);
    }
    
    public static void removeAttribute(String name) {
        getCurrent().remove(name);
    }
    
    public static void setAttribute(String name, Object value) {
        getCurrent().put(name, value);
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getCurrent() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpSession session = request.getSession(); 
        Map<String, Object> flash = (Map<String, Object>) session.getAttribute(FLASH_SCOPE_ATTRIBUTE);
        if (flash == null) {
            flash = new HashMap<String, Object>();
            session.setAttribute(FLASH_SCOPE_ATTRIBUTE, flash);
        }
        return flash;
    }
}
