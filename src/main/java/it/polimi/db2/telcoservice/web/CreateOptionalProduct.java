package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CreateOptionalProduct", value = "/create-optional-product")
public class CreateOptionalProduct extends HttpServlet {
    private String message;

    public void init() {
        message = "The optional product has been added to the database!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        BigDecimal monthlyFee = new BigDecimal(request.getParameter("monthly-fee"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        OptionalProduct optionalProduct = new OptionalProduct(name, monthlyFee);

        entityManager.getTransaction().begin();
        entityManager.persist(optionalProduct);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
}