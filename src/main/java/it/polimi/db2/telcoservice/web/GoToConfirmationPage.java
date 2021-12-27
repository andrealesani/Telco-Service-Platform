package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.services.ServicePackageService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GoToBuyServicePage
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
        String servPckgId = request.getParameter("service-package");
        int id = 0;
        try {
            id = Integer.parseInt(servPckgId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The specified service package ID is not valid");
        }
        ServicePackageService servicePackageService = new ServicePackageService();
        ServicePackage servicePackage = servicePackageService.findServicePackageById(id);
        String path = "/WEB-INF/buy-service.html";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        //ctx.setVariable("servicePackage", servicePackage);
        //response.getWriter().println("validityPeriods is empty: " + servicePackages.get(0).getValidityPeriods().isEmpty());
        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
