package com.googlecode.janrain4j.demo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.janrain4j.conf.ConfigHolder;

@SuppressWarnings("serial")
public class TokenServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
        
        System.out.println("tokenservlet :: config = " + ConfigHolder.getConfig());
        System.out.println("tokenservlet :: config.domain = " + ConfigHolder.getConfig().getApplicationDomain());
    }
}
