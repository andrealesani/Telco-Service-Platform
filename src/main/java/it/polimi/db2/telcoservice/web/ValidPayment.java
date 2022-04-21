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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/ValidPayment")
public class ValidPayment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("order-id"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        SubscriptionOrder order = entityManager.find(SubscriptionOrder.class, orderId);
        order.setStartDateTs(new Timestamp(System.currentTimeMillis()));
        order.setValid(true);
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
        response.getWriter().println("Subscription activated");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
