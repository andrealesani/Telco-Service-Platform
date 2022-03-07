package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.enumerations.ServiceType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "AddGeeGee", value = "/add-default-service-package")
public class AddDefaultServicePackage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Set<Service> services = new HashSet<>();
        // Giusto per non creare 10000 services se ce n'è già uno usa quello, se no lo crea nuovo
        if (entityManager.find(Service.class, 1) == null) {
            Service service = new Service(ServiceType.FIXED_INTERNET, 5, 5, 5, new BigDecimal(5), new BigDecimal(5), new BigDecimal(5));
            entityManager.persist(service);
            services.add(service);
        } else {
            services.add(entityManager.find(Service.class, 1));
        }
        ServicePackage servicePackage = new ServicePackage();
        servicePackage.setName("Gee");
        servicePackage.setServices(services);

        entityManager.getTransaction().begin();
        entityManager.persist(servicePackage);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();

        PrintWriter out = response.getWriter();
        out.println("<html><link href=\"css/style.css\" rel=\"stylesheet\"><body>");
        out.println("<h1>" + "Service package has been added to the database!" + "</h1>");
        out.println("</body></html>");
    }
}