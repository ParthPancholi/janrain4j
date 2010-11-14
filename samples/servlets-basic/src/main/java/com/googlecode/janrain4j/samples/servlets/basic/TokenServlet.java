package com.googlecode.janrain4j.samples.servlets.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.json.JSONException;

@SuppressWarnings("serial")
public class TokenServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String token = req.getParameter("token");
        
        EngageService engageService = EngageServiceFactory.getEngageService();
        
        try {
            UserDataResponse userDataResponse = engageService.authInfo(token, true);
            HttpSession session = req.getSession();
            session.setAttribute("userData", userDataResponse);
            session.setAttribute("plainResponse", userDataResponse.getResponseAsJSONObject().toString(2));
            // getServletContext().getRequestDispatcher("/user_data.jsp").forward(req, resp);
            resp.sendRedirect("/user_data.jsp");
        }
        catch (EngageFailureException e) {
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        catch (ErrorResponeException e) {
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        catch (JSONException e) {
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
