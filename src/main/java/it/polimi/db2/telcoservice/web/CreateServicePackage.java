package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.ServiceService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;
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

@WebServlet("/create-service-package")
public class CreateServicePackage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServiceService")
    private ServiceService sService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
    private ValidityPeriodService vpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
    private OptionalProductService opService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
    private ServicePackageService spService;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "/WEB-INF/creation-result.html";

        String name = request.getParameter("package-name");
        if (name.trim().isEmpty()) {
            response.sendError(400, "Service package must have a name.");
            return;
        }

        int numServices = sService.findNumServices();
        List<Integer> serviceIDs = new ArrayList<>();
        for (int i = 0; i < numServices; i++) {
            try {
                serviceIDs.add(Integer.parseInt(request.getParameter("service" + i)));
            } catch (NumberFormatException ignored) {
            }
        }
        if (serviceIDs.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 service.");
            return;
        }

        int numValPeriods = vpService.findNumValidityPeriods();
        List<Integer> validityPeriodIDs = new ArrayList<>();
        for (int i = 0; i < numValPeriods; i++) {
            try {
                validityPeriodIDs.add(Integer.parseInt(request.getParameter("validity-period" + i)));
            } catch (NumberFormatException ignored) {
            }
        }
        if (validityPeriodIDs.isEmpty()) {
            response.sendError(400, "Service package must be associated to at least 1 validity period.");
            return;
        }

        int numOptProducts = opService.findNumOptionalProducts();
        List<Integer> optionalProductIDs = new ArrayList<>();
        for (int i = 0; i < numOptProducts; i++) {
            try {
                optionalProductIDs.add(Integer.parseInt(request.getParameter("optional-product" + i)));
            } catch (NumberFormatException ignored) {
            }
        }

        spService.createServicePackage(name, serviceIDs, validityPeriodIDs, optionalProductIDs);

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("user", request.getSession().getAttribute("user"));
        ctx.setVariable("entity", "Service Package");

        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}