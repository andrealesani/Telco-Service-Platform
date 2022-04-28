package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;
import it.polimi.db2.telcoservice.services.SubscriptionOrderService;

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

@WebServlet("/RetryOrder")
public class RetryOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2.telcoservice.services/SubscriptionOrderService")
    private SubscriptionOrderService soService;

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        int order_id = Integer.parseInt(request.getParameter("order_id"));

        SubscriptionOrder order = soService.findSubscriptionOrderById(order_id);

        request.getSession().setAttribute("order", order);
        System.out.println("Order has been saved in session.");

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
