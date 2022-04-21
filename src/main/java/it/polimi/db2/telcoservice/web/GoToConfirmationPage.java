package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GoToConfirmationPage
 */
@WebServlet("/GoToConfirmationPage")
public class GoToConfirmationPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "/WEB-INF/confirmation.html";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // user might not be logged in, but it's not a problem. So we ignore
        // the exception and just don't set any user inside the context
        try {
            ctx.setVariable("user", entityManager.find(User.class, ((User) request.getSession().getAttribute("user")).getId()));
        } catch (Exception ignored) {}

        ctx.setVariable("order", entityManager.find(SubscriptionOrder.class, ((SubscriptionOrder) request.getSession().getAttribute("order")).getId()));
        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
