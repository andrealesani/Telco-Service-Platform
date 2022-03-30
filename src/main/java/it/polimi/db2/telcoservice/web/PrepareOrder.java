package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;

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

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        int serv_pckg_id = Integer.parseInt(request.getParameter("serv_pckg_id"));
        int val_per_id = Integer.parseInt(request.getParameter("val_per_id"));
        List<Integer> opt_prod_ids = new ArrayList<>();
        int i = 0;
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

        ServicePackage servicePackage = entityManager.find(ServicePackage.class, serv_pckg_id);
        ValidityPeriod validityPeriod = entityManager.find(ValidityPeriod.class, val_per_id);
        Set<OptionalProduct> optionalProducts = new HashSet<>();
        for (int id : opt_prod_ids) {
            optionalProducts.add(entityManager.find(OptionalProduct.class, id));
        }
        User user = (User) request.getSession().getAttribute("user");
        user = entityManager.find(User.class, user.getId());

        SubscriptionOrder order = new SubscriptionOrder(servicePackage, validityPeriod, optionalProducts, user, new Timestamp(System.currentTimeMillis()));

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

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
