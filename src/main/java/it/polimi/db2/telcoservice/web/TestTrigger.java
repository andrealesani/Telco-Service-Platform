package it.polimi.db2.telcoservice.web;


import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportInsolventUsers;
import it.polimi.db2.telcoservice.enumerations.ServiceType;

import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.SupportedValuesAttribute;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name="/TestTrigger", value="/TestTrigger")
public class TestTrigger extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


//        OptionalProduct op = entityManager.find(OptionalProduct.class, 1);
//        OptionalProduct op1 = new OptionalProduct();
//        op1.setName("ehe");
//        op1.setMonthlyFee(new BigDecimal(5));
//        Set<OptionalProduct> opSet = new HashSet<>();
//        opSet.add(op);
//        opSet.add(op1 );
//        so.setOptionalProducts(opSet);

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + "Test completed! " + "</h1>");
        out.println("</body></html>");
    }
}