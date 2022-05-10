package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.SubscriptionOrderService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/PrepareOrder")
public class PrepareOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
    private ServicePackageService spService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SubscriptionOrderService")
    private SubscriptionOrderService soService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int validityPeriodID = Integer.parseInt(request.getParameter("val_per_id"));
        int servicePackageID = Integer.parseInt(request.getParameter("serv_pckg_id"));
        String startDateString = request.getParameter("start-date");

        Timestamp startDateTs;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(startDateString);
            startDateTs = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Provided start date " + startDateString + " is not valid");
            return;
        }

        // for each possible optional product included in the package check if it has been checked and sent to the servlet
        List<Integer> optionalProductIDs = new ArrayList<>();
        int numOfOptionalProducts = spService.findServicePackageById(servicePackageID).getOptionalProducts().size();
        for (int i = 0; i < numOfOptionalProducts; i++) {
            try {
                optionalProductIDs.add(Integer.parseInt(request.getParameter("opt_prod_id" + i)));
            } catch (NumberFormatException ignored) {
            }
        }

        SubscriptionOrder order = soService.createOrder(servicePackageID, validityPeriodID, optionalProductIDs, new Timestamp(System.currentTimeMillis()), startDateTs);

        request.getSession().setAttribute("order", order);
        System.out.println("Order has been saved in session.");

        response.sendRedirect(getServletContext().getContextPath() + "/GoToConfirmationPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
