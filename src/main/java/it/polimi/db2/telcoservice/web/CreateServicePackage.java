package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServiceService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;

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

@WebServlet("/create-service-package")
public class CreateServicePackage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;

    public void init() {
        message = "The service package has been added to the database!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String name = request.getParameter("package-name");
        if (name.trim().isEmpty()) {
            response.sendError(400, "Service package must have a name.");
            return;
        }

        ServiceService ss = new ServiceService();
        int numServices = ss.findNumServices();
        List<Integer> serviceIds = new ArrayList<>();
        for (int i = 0; i < numServices; i++) {
            try {
                serviceIds.add(Integer.parseInt(request.getParameter("service" + i)));
            } catch (NumberFormatException ignored) {}
        }
        if (serviceIds.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 service.");
            return;
        }

        ValidityPeriodService vps = new ValidityPeriodService();
        int numValPeriods = vps.findNumValidityPeriods();
        List<Integer> valPeriodIds = new ArrayList<>();
        for (int i = 0; i < numValPeriods; i++) {
            try {
                valPeriodIds.add(Integer.parseInt(request.getParameter("validity-period" + i)));
            } catch (NumberFormatException ignored) {}
        }
        if (valPeriodIds.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 validity period.");
            return;
        }

        OptionalProductService ops = new OptionalProductService();
        int numOptProducts = ops.findNumOptionalProducts();
        List<Integer> optProdIds = new ArrayList<>();
        for (int i = 0; i < numOptProducts; i++) {
            try {
                optProdIds.add(Integer.parseInt(request.getParameter("optional-product" + i)));
            } catch (NumberFormatException ignored) {}
        }

        Set<Service> services = new HashSet<>();
        for (int id : serviceIds) {
            services.add(entityManager.find(Service.class, id));
        }
        Set<ValidityPeriod> validityPeriods = new HashSet<>();
        for (int id : valPeriodIds) {
            validityPeriods.add(entityManager.find(ValidityPeriod.class, id));
        }
        Set<OptionalProduct> optionalProducts = new HashSet<>();
        for (int id : optProdIds) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}