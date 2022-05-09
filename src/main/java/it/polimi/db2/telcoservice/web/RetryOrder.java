package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.services.SubscriptionOrderService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RetryOrder")
public class RetryOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2.telcoservice.services/SubscriptionOrderService")
    private SubscriptionOrderService soService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int order_id = Integer.parseInt(request.getParameter("order_id"));

        SubscriptionOrder order = soService.findSubscriptionOrderById(order_id);

        request.getSession().setAttribute("order", order);
        System.out.println("Order has been saved in session.");

        response.sendRedirect(getServletContext().getContextPath() + "/GoToConfirmationPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
