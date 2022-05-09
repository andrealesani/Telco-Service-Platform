package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.services.*;
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
import java.util.List;

/**
 * Servlet implementation class GoToEmployeePage
 */
@WebServlet("/GoToEmployeeHomePage")
public class GoToEmployeeHomePage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServiceService")
    private ServiceService sService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
    private ValidityPeriodService vpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
    private OptionalProductService opService;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    ;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "/WEB-INF/employee-home.html";

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isEmployee()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You must be an employee to access this page.");
            return;
        }

        List<Service> services = sService.findAllServices();

        List<ValidityPeriod> validityPeriods = vpService.findAllValidityPeriods();

        List<OptionalProduct> optionalProducts = opService.findAllOptionalProducts();

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("services", services);
        ctx.setVariable("validityPeriods", validityPeriods);
        ctx.setVariable("optionalProducts", optionalProducts);
        ctx.setVariable("user", user);

        templateEngine.process(path, ctx, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}
