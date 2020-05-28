package com.internetshop.controllers.orders;

import com.internetshop.lib.Injector;
import com.internetshop.service.OrderService;
import com.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserOrdersController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orders", orderService.getUserOrders(
                userService.get((Long) req.getSession().getAttribute(USER_ID))));
        req.getRequestDispatcher("/WEB-INF/views/orders/userOrders.jsp").forward(req, resp);
    }
}
