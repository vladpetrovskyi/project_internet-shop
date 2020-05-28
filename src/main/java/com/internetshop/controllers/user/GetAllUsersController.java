package com.internetshop.controllers.user;

import com.internetshop.lib.Injector;
import com.internetshop.model.User;
import com.internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAll();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users/allUsers.jsp").forward(req, resp);
    }
}
