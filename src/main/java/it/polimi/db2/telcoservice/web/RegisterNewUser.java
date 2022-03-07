package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;
import it.polimi.db2.telcoservice.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register-new-user", value = "/register-new-user")
public class RegisterNewUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("This servlet only supports POST requests");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");  // the username is the email the user registered with
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        UserService us = new UserService();
        User u = null;
        try {
            us.registerNewUser(username, password, email);
        } catch (CredentialsException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Couldn't verify credentials");
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User already exists");
        }

        response.sendRedirect(getServletContext().getContextPath() + "/GoToLoginPage?registered=true");
    }
}


