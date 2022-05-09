package it.polimi.db2.telcoservice.web;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.services.SubscriptionOrderService;
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
import java.util.List;

@WebServlet("/GoToSchedulePage")
public class GoToSchedulePage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @EJB(name = "it.polimi.db2.telcoservice.services/UserService")
    private UserService uService;
    @EJB(name = "it.polimi.db2.telcoservice.services/SubscriptionOrderService")
    private SubscriptionOrderService soService;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "/WEB-INF/activation-schedule.html";

        User user;
        try {
            user = uService.findUserById(((User) request.getSession().getAttribute("user")).getId());
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User must be logged-in to access this page.");
            ex.printStackTrace();
            return;
        }

        List<SubscriptionOrder> soList = soService.findOrdersByUser(user.getId());

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        ctx.setVariable("user", user);
        ctx.setVariable("orders", soList);

        templateEngine.process(path, ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}