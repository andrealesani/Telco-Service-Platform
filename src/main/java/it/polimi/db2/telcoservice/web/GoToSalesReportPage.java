package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.Auditing;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.entities.materialized.*;
import it.polimi.db2.telcoservice.services.*;
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
        String path = "/WEB-INF/sales-report.html";

        User user = (User) request.getSession().getAttribute("user");

        List<SalesReportPackages> srServicePackages;
        SalesReportPackagesService salesReportPackagesService = new SalesReportPackagesService();
        srServicePackages = salesReportPackagesService.findAllSalesReports();

        List<SalesReportValidityPackages> srValidityPeriodServicePackages;
        SalesReportValidityPackagesService salesReportValidityPackagesService = new SalesReportValidityPackagesService();
        srValidityPeriodServicePackages = salesReportValidityPackagesService.findAllSalesReports();

        List<SalesReportInsolventUsers> srInsolventUsers;
        SalesReportInsolventUsersService salesReportInsolventUsersService = new SalesReportInsolventUsersService();
        srInsolventUsers = salesReportInsolventUsersService.findAllInsolvent();

        List<SalesReportSuspendedOrders> srSuspendedOrders;
        SalesReportSuspendedOrdersService salesReporSuspendedOrdersService = new SalesReportSuspendedOrdersService();
        srSuspendedOrders = salesReporSuspendedOrdersService.findAllSuspended();

        List<Auditing> srAuditingRecords;
        AuditingService auditingService = new AuditingService();
        srAuditingRecords = auditingService.findAllAuditings();

        SalesReportProductSalesService salesReportProductSalesService = new SalesReportProductSalesService();
        SalesReportProductSales srBestSellerProduct = salesReportProductSalesService.findBestSeller();

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("srServicePackages", srServicePackages);
        ctx.setVariable("srValidityPeriodServicePackages", srValidityPeriodServicePackages);
        ctx.setVariable("srInsolventUsers", srInsolventUsers);
        ctx.setVariable("srSuspendedOrders", srSuspendedOrders);
        ctx.setVariable("srBestSellerProduct", srBestSellerProduct);
        ctx.setVariable("srAuditingRecords", srAuditingRecords);
        ctx.setVariable("user", user);

        templateEngine.process(path, ctx, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
