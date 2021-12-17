package it.polimi.db2.telcoservice;

import it.polimi.db2.telcoservice.entities.User;

import java.io.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "AddGeeGee", value = "/add-gee-gee")
public class AddGeeGee extends HttpServlet {
    private String message;

    public void init() {
        message = "GeeGee has been added to the database!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        User user = new User();
        user.setUsername("Gee");
        user.setPassword("Gee");
        user.setEmail("Gee");
        user.setIs_flagged(false);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}