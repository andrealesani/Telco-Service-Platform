package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportPackages;
import it.polimi.db2.telcoservice.services.SalesReportPackagesService;
import it.polimi.db2.telcoservice.services.ServicePackageService;
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
@WebServlet("/GoToSalesReportPage")
public class GoToSalesReportPage extends HttpServlet {
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
        List<SalesReportPackages> srServicePackages;
        SalesReportPackagesService salesReportPackagesService = new SalesReportPackagesService();
        srServicePackages = salesReportPackagesService.findAllSalesReports();
        String path = "/WEB-INF/sales-report.html";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("srServicePackages", srServicePackages);

        templateEngine.process(path, ctx, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}