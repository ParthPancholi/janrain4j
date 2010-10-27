package com.googlecode.janrain4j.samples.basic.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;

@SuppressWarnings("serial")
public class TokenServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String token = req.getParameter("token");
        
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        try {
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            req.getSession().setAttribute("userData", userDataResponse);
            getServletContext().getRequestDispatcher("/user_data.jsp").forward(req, resp);
        }
        catch (EngageFailureException e) {
            req.getSession().setAttribute("userData", null);
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        catch (ErrorResponeException e) {
            req.getSession().setAttribute("userData", null);
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
