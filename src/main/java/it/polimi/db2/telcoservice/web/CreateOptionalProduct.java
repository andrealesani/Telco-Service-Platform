package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.services.OptionalProductService;
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
import java.math.BigDecimal;

@WebServlet(name = "CreateOptionalProduct", value = "/create-optional-product")
public class CreateOptionalProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "/WEB-INF/creation-result.html";

        String name = request.getParameter("product-name");
        if (name.trim().isEmpty()) {
            response.sendError(400, "Optional product must have a name.");
            return;
        }

        BigDecimal monthlyFee = new BigDecimal(request.getParameter("monthly-fee"));
        if (monthlyFee.longValue() < 0.0) {
            response.sendError(400, "Optional product cannot have a negative monthly fee.");
            return;
        }

        opService.createOptionalProduct(name, monthlyFee);

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("user", request.getSession().getAttribute("user"));
        ctx.setVariable("entity", "Optional Product");

        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}