package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class GoToBuyServicePage
 */
@WebServlet("/PrepareOrder")
public class PrepareOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int serv_pckg_id = Integer.parseInt(request.getParameter("serv_pckg_id"));
        int val_per_id = Integer.parseInt(request.getParameter("val_per_id"));
        int opt_prod_id = -1;
        try {
            opt_prod_id = Integer.parseInt(request.getParameter("opt_prod_id"));
        } catch (NumberFormatException ignored) {
        }

        ServicePackageService servicePackageService = new ServicePackageService();
        ValidityPeriodService validityPeriodService = new ValidityPeriodService();
        OptionalProductService optionalProductService = new OptionalProductService();

        ServicePackage servicePackage = servicePackageService.findServicePackageById(serv_pckg_id);
        ValidityPeriod validityPeriod = validityPeriodService.findValidityPeriodById(val_per_id);
        Set<OptionalProduct> optionalProducts = new HashSet<>();
        if (opt_prod_id != -1) {
            optionalProducts.add(optionalProductService.findOptionalProductById(opt_prod_id));
        }
        SubscriptionOrder order = new SubscriptionOrder(servicePackage, validityPeriod, optionalProducts, (User) request.getSession().getAttribute("user"));
        request.getSession().setAttribute("order", order);
        //try {
        //    getServletContext().getRequestDispatcher("/GoToConfirmationPage").forward(request, response);
        //} catch (ServletException e) {
        //    response.sendError(500);
        //    e.printStackTrace();
        //}
        response.sendRedirect(getServletContext().getContextPath() + "/GoToConfirmationPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
