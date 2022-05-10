package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.Auditing;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.entities.materialized.*;
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
 * Servlet implementation class GoToSalesReportPage
 */
@WebServlet("/GoToSalesReportPage")
public class GoToSalesReportPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @EJB(name = "it.polimi.db2.telcoservice.services/SalesReportPackagesService")
    private SalesReportPackagesService srpsService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SalesReportValidityPackagesService")
    private SalesReportValidityPackagesService srvpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SalesReportInsolventUsersService")
    private SalesReportInsolventUsersService sriuService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SalesReportSuspendedOrdersService")
    private SalesReportSuspendedOrdersService srsoService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SalesReportProductSalesService")
    private SalesReportProductSalesService srprodsService;
    @EJB(name = "it.polimi.db2.telcoservice.services/AuditingService")
    private AuditingService aService;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "/WEB-INF/sales-report.html";

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.isEmployee()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You must be an employee to access this page.");
            return;
        }

        List<SalesReportPackages> srServicePackages = srpsService.findAllSalesReports();
        List<SalesReportValidityPackages> srValidityPeriodServicePackages = srvpService.findAllSalesReports();
        List<SalesReportInsolventUsers> srInsolventUsers = sriuService.findAllInsolvent();
        List<SalesReportSuspendedOrders> srSuspendedOrders = srsoService.findAllSuspended();
        SalesReportProductSales srBestSellerProduct = srprodsService.findBestSeller();
        List<Auditing> srAuditingRecords = aService.findAllAuditings();

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

    public void destroy() {
    }
}
