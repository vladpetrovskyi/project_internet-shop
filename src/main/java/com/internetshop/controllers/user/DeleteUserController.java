package com.internetshop.controllers.user;

import com.internetshop.lib.Injector;
import com.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String userId = req.getParameter("user_id");
        userService.delete(Long.valueOf(userId));

        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
