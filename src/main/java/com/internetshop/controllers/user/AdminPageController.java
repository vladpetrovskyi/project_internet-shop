package com.internetshop.controllers.user;

import com.internetshop.lib.Injector;
import com.internetshop.model.User;
import com.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));
        req.setAttribute("user_name", user.getName());
        req.getRequestDispatcher("WEB-INF/views/adminPage.jsp").forward(req, resp);
    }
}
