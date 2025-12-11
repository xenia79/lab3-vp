package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

//@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet  extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final ChefService chefService;

    public ChefDetailsServlet(SpringTemplateEngine springTemplateEngine, ChefService chefService) {
        this.springTemplateEngine = springTemplateEngine;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        Chef chef = chefService.findById(Long.valueOf(req.getParameter("chefId")));
        WebContext context = new WebContext(webExchange);

        context.setVariable("chefId", req.getParameter("chefId"));
        context.setVariable("chefBio", chef.getBio());
        context.setVariable("dishes", chef.getDishes());
        context.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chefId = Long.valueOf(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        chefService.addDishToChef(chefId, dishId);
        resp.sendRedirect("/chefDetails?chefId=" + chefId);

    }
}
