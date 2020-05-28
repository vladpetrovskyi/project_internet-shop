package com.internetshop.controllers;

import com.internetshop.exceptions.AuthenticationException;
import com.internetshop.lib.Injector;
import com.internetshop.model.Role;
import com.internetshop.model.User;
import com.internetshop.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final AuthenticationService authService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/loginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user;
        try {
            user = authService.login(login, pass);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/users/loginPage.jsp").forward(req, resp);
            return;
        }

        if (user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(Role.RoleName.ADMIN))) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user");
        }
    }
}
