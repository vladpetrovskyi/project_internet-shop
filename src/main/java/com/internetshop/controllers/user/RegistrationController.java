package com.internetshop.controllers.user;

import com.internetshop.lib.Injector;
import com.internetshop.model.Role;
import com.internetshop.model.User;
import com.internetshop.service.ShoppingCartService;
import com.internetshop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private static final String URL = "/WEB-INF/views/users/registration.jsp";
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(URL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("pass_Repeat");
        String nameSurname = req.getParameter("name_Surname");

        if (userService.getByLogin(login).isPresent()) {
            req.setAttribute("message", "User with this login already exists!");
            req.getRequestDispatcher(URL).forward(req, resp);
        }

        if (pass.equals(passRepeat)) {
            User user = new User(nameSurname, login, pass);
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            Long id = user.getId();
            shoppingCartService.create(id);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("message", "Passwords do not match!");
            req.getRequestDispatcher(URL).forward(req, resp);
        }
    }
}
