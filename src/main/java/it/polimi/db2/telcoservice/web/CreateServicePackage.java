package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.services.*;

import javax.ejb.EJB;
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
    @EJB(name = "it.polimi.db2.telcoservice.services/ServiceService")
    private ServiceService sService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
    private ValidityPeriodService vpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
    private OptionalProductService opService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
    private ServicePackageService spService;

    public void init() {
        message = "The service package has been added to the database!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("package-name");
        if (name.trim().isEmpty()) {
            response.sendError(400, "Service package must have a name.");
            return;
        }

        int numServices = sService.findNumServices();
        Set<Service> services = new HashSet<>();
        for (int i = 0; i < numServices; i++) {
            try {
                int serviceId = Integer.parseInt(request.getParameter("service" + i));
                services.add(sService.findServiceById(serviceId));
            } catch (NumberFormatException ignored) {}
        }
        if (services.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 service.");
            return;
        }

        int numValPeriods = vpService.findNumValidityPeriods();
        Set<ValidityPeriod> validityPeriods = new HashSet<>();
        for (int i = 0; i < numValPeriods; i++) {
            try {
                int valPeriodId = Integer.parseInt(request.getParameter("validity-period" + i));
                validityPeriods.add(vpService.findValidityPeriodById(valPeriodId));
            } catch (NumberFormatException ignored) {}
        }
        if (validityPeriods.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 validity period.");
            return;
        }

        int numOptProducts = opService.findNumOptionalProducts();
        Set<OptionalProduct> optionalProducts = new HashSet<>();
        for (int i = 0; i < numOptProducts; i++) {
            try {
                int optProductId = Integer.parseInt(request.getParameter("optional-product" + i));
                optionalProducts.add(opService.findOptionalProductById(optProductId));
            } catch (NumberFormatException ignored) {}
        }

        spService.createServicePackage(name, services, validityPeriods, optionalProducts);

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy(){
    }
}