package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/InvalidPayment")
public class InvalidPayment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("order-id"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        SubscriptionOrder order = entityManager.find(SubscriptionOrder.class, orderId);
        order.setValid(false);
        order.setUser(entityManager.find(User.class, ((User) request.getSession().getAttribute("user")).getId()));

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        request.getSession().removeAttribute("order");

        //try {
        //    getServletContext().getRequestDispatcher("/GoToConfirmationPage").forward(request, response);
        //} catch (ServletException e) {
        //    response.sendError(500);
        //    e.printStackTrace();
        //}
        response.getWriter().println("Your payment was rejected");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
