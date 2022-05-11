package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.services.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2.telcoservice.services/UserService")
    private UserService uService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("This servlet only supports POST requests.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // If there is another user already logged in, then invalidate the whole session and start a new one.
        // If the user was not logged in (and does login now) the order in session is kept
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().invalidate();
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing login parameters.");
            return;
        }

        User user;
        try {
            user = uService.checkCredentials(username, password);
        } catch (CredentialsException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Couldn't validate credentials.");
            e.printStackTrace();
            return;
        }

        String path;
        if (user == null) {
            path = getServletContext().getContextPath() + "/GoToLoginPage?message=error";
        } else {
            request.getSession().setAttribute("user", user);
            if (request.getSession().getAttribute("order") == null) {
                path = getServletContext().getContextPath() + "/GoToHomePage";
                System.out.println("No order was present at login.");
            } else {
                path = getServletContext().getContextPath() + "/GoToConfirmationPage";
                System.out.println("The user had already created an order at login.");
            }
        }

        response.sendRedirect(path);
    }
}


