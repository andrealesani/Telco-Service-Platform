package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;
import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.SubscriptionOrderService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/PrepareOrder")
public class PrepareOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
    private ServicePackageService spService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
    private ValidityPeriodService vpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
    private OptionalProductService opService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SubscriptionOrderService")
    private SubscriptionOrderService soService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int val_per_id = Integer.parseInt(request.getParameter("val_per_id"));
        int serv_pckg_id = Integer.parseInt(request.getParameter("serv_pckg_id"));

        ServicePackage servicePackage = spService.findServicePackageById(serv_pckg_id);
        ValidityPeriod validityPeriod = vpService.findValidityPeriodById(val_per_id);

        Set<OptionalProduct> optionalProducts = new HashSet<>();
        // for each possible optional product included in the package check if it has been checked and sent to the servlet
        for (int i = 0; i < servicePackage.getOptionalProducts().size(); i++) {
            try {
                optionalProducts.add(opService.findOptionalProductById(Integer.parseInt(request.getParameter("opt_prod_id" + i))));
            } catch (NumberFormatException ignored) {}
        }

        SubscriptionOrder order = soService.createOrder(servicePackage, validityPeriod, optionalProducts, new Timestamp(System.currentTimeMillis()));

        request.getSession().setAttribute("order", order);
        System.out.println("Order has been saved in session.");

        response.sendRedirect(getServletContext().getContextPath() + "/GoToConfirmationPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
