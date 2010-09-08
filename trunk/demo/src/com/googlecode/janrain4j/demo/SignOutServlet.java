package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SignOutServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("userData");
        
        String message = "Successfully signed out";
        
        resp.sendRedirect("index.jsp?message=" + message);
    }
}
