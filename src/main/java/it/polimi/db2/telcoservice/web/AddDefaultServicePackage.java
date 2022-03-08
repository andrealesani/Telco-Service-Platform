package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.enumerations.ServiceType;

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
import java.util.HashSet;
import java.util.Set;

/*
*
*
*
*
*               FOR DEBUGGING PURPOSES
*
*
*
*
*
* */

@WebServlet(name = "AddGeeGee", value = "/add-default-service-package")
public class AddDefaultServicePackage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Set<Service> services = new HashSet<>();
        // Giusto per non creare 10000 services se ce ne sono già due usa quelli, altrimenti ne crea di nuovi
        if (entityManager.find(Service.class, 1) == null) {
            Service service = new Service(ServiceType.FIXED_INTERNET, 5, 5, 5, new BigDecimal(5), new BigDecimal(5), new BigDecimal(5));
            entityManager.persist(service);
            services.add(service);
        } else {
            services.add(entityManager.find(Service.class, 1));
        }
        if (entityManager.find(Service.class, 51) == null) {
            Service service = new Service(ServiceType.FIXED_INTERNET, 5, 5, 5, new BigDecimal(5), new BigDecimal(5), new BigDecimal(5));
            entityManager.persist(service);
            services.add(service);
        } else {
            services.add(entityManager.find(Service.class, 51));
        }

        Set<OptionalProduct> optionalProducts = new HashSet<>();
        // Giusto per non creare 10000 optional products se ce ne sono già due usa quelli, altrimenti ne crea di nuovi
        if (entityManager.find(OptionalProduct.class, 1) == null) {
            OptionalProduct optionalProduct = new OptionalProduct("optionalGee", new BigDecimal(20));
            entityManager.persist(optionalProduct);
            optionalProducts.add(optionalProduct);
        } else {
            optionalProducts.add(entityManager.find(OptionalProduct.class, 1));
        }
        if (entityManager.find(OptionalProduct.class, 51) == null) {
            OptionalProduct optionalProduct = new OptionalProduct("optionalGee", new BigDecimal(20));
            entityManager.persist(optionalProduct);
            optionalProducts.add(optionalProduct);
        } else {
            optionalProducts.add(entityManager.find(OptionalProduct.class, 51));
        }

        Set<ValidityPeriod> validityPeriods = new HashSet<>();
        // Giusto per non creare 10000 validity periods se ce ne sono già due usa quelli, altrimenti ne crea di nuovi
        if (entityManager.find(ValidityPeriod.class, 1) == null) {
            ValidityPeriod validityPeriod = new ValidityPeriod(12, new BigDecimal("9.90"));
            entityManager.persist(validityPeriod);
            validityPeriods.add(validityPeriod);
        } else {
            validityPeriods.add(entityManager.find(ValidityPeriod.class, 1));
        }
        if (entityManager.find(ValidityPeriod.class, 51) == null) {
            ValidityPeriod validityPeriod = new ValidityPeriod(24, new BigDecimal("6.90"));
            entityManager.persist(validityPeriod);
            validityPeriods.add(validityPeriod);
        } else {
            validityPeriods.add(entityManager.find(ValidityPeriod.class, 51));
        }

        ServicePackage servicePackage = new ServicePackage();
        servicePackage.setName("Gee");
        servicePackage.setServices(services);
        servicePackage.setOptionalProducts(optionalProducts);
        servicePackage.setValidityPeriods(validityPeriods);

        entityManager.getTransaction().begin();
        entityManager.persist(servicePackage);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + "Service package has been added to the database!" + "</h1>");
        out.println("</body></html>");
    }
}