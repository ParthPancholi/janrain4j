package com.googlecode.janrain4j.demo;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Janrain4j_demoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
