package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CreateServicePackage", value = "/create-service-package")
public class CreateServicePackage extends HttpServlet {
    private String message;

    public void init() {
        message = "The service package has been added to the database!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        List<Integer> servicesIds = new ArrayList<>();
        int i = 0;
        while (true) {
            try {
                servicesIds.add(Integer.parseInt(request.getParameter("service_id" + i)));
                i++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        List<Integer> valPerIds = new ArrayList<>();
        i = 0;
        while (true) {
            try {
                valPerIds.add(Integer.parseInt(request.getParameter("val_per_id" + i)));
                i++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        List<Integer> opt_prod_ids = new ArrayList<>();
        i = 0;
        while (true) {
            try {
                opt_prod_ids.add(Integer.parseInt(request.getParameter("opt_prod_id" + i)));
                i++;
            } catch (NumberFormatException e) {
                break;
            }
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Set<Service> services = new HashSet<>();
        for (int id : servicesIds) {
            services.add(entityManager.find(Service.class, id));
        }
        Set<ValidityPeriod> validityPeriods = new HashSet<>();
        for (int id : valPerIds) {
            validityPeriods.add(entityManager.find(ValidityPeriod.class, id));
        }
        Set<OptionalProduct> optionalProducts = new HashSet<>();
        for (int id : opt_prod_ids) {
            optionalProducts.add(entityManager.find(OptionalProduct.class, id));
        }

        ServicePackage servicePackage = new ServicePackage(name, services, validityPeriods, optionalProducts);

        entityManager.getTransaction().begin();
        entityManager.persist(servicePackage);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
}