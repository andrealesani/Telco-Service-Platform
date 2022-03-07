package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("This servlet only supports POST requests");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");  // the username is the email the user registered with
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        UserService us = new UserService();
        User u = null;
        try {
            u = us.checkCredentials(username, password);
        } catch (CredentialsException e) {
            e.printStackTrace();
        }

        String path = getServletContext().getContextPath();
        if (u == null) {
            path = getServletContext().getContextPath() + "/index.html";
        } else {
            request.getSession().setAttribute("user", u);
            //String target = (u.getRole().equals("admin")) ? "/home-admin" : "/home-user";
            String target = "/GoToHomePage";
            path = path + target;
        }
        response.sendRedirect(path);
    }

    public void destroy() {}
}


