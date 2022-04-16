package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;
import it.polimi.db2.telcoservice.services.OptionalProductService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import it.polimi.db2.telcoservice.services.ServiceService;
import it.polimi.db2.telcoservice.services.ValidityPeriodService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

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

    public void init(){
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    };

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "/WEB-INF/employee-home.html";

        List<Service> services;
        ServiceService serviceService = new ServiceService();
        services = serviceService.findAllServices();

        List<ValidityPeriod> validityPeriods;
        ValidityPeriodService validityPeriodService = new ValidityPeriodService();
        validityPeriods = validityPeriodService.findAllValidityPeriods();

        List<OptionalProduct> optionalProducts;
        OptionalProductService optionalProductService = new OptionalProductService();
        optionalProducts = optionalProductService.findAllOptionalProducts();

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("services", services);
        ctx.setVariable("validityPeriods", validityPeriods);
        ctx.setVariable("optionalProducts", optionalProducts);

        templateEngine.process(path, ctx, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
