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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.checkCredentials(username, password);
        } catch (CredentialsException e) {
            e.printStackTrace();
        }

        String path;
        if (user == null) {
            path = getServletContext().getContextPath() + "/GoToLoginPage";
        } else {
            request.getSession().setAttribute("user", user);
            if (request.getSession().getAttribute("order") == null) {
                path = getServletContext().getContextPath() + "/GoToHomePage";
                System.out.println("no order was present");
            } else {
                path = getServletContext().getContextPath() + "/GoToConfirmationPage";
                System.out.println("the user had already created an order");
            }
        }
        response.sendRedirect(path);
    }
}


