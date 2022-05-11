package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class GoToHomePage
 */
@WebServlet("/GoToHomePage")
public class GoToHomePage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
    private ServicePackageService spService;
    @EJB(name = "it.polimi.db2.telcoservice.services/UserService")
    private UserService uService;


    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "/WEB-INF/home.html";

        User user = (User) request.getSession().getAttribute("user");

        List<SubscriptionOrder> soList;
        if (user != null) {
            soList = uService.getUserOrders(user.getId());
        } else {
            soList = new ArrayList<>();
        }

        List<ServicePackage> servicePackages = spService.findAllServicePackages();

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("servicePackages", servicePackages);
        ctx.setVariable("orders", soList);

        // refresh user to refresh 'valid' flag
        if (user != null) {
            ctx.setVariable("user", uService.findUserById(user.getId()));
        }

        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
