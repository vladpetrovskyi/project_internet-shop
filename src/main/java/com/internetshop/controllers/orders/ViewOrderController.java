package com.internetshop.controllers.orders;

import com.internetshop.lib.Injector;
import com.internetshop.model.Order;
import com.internetshop.model.Role;
import com.internetshop.service.OrderService;
import com.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewOrderController extends HttpServlet {

    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = orderService.get(Long.parseLong(req.getParameter("order_id")));
        req.setAttribute("order", order);
        req.setAttribute("sum", orderService.sum(order.getProducts()));

        if (userService.get((Long) req.getSession().getAttribute(USER_ID))
                .getRoles()
                .stream()
                .anyMatch(r -> r.getRoleName().equals(Role.RoleName.ADMIN))) {
            req.getRequestDispatcher("/WEB-INF/views/products/productsFromOrderAdmin.jsp")
                    .forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/products/productsFromOrderUser.jsp")
                    .forward(req, resp);
        }
    }
}
