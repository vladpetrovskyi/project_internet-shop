package com.internetshop.controllers.orders;

import com.internetshop.lib.Injector;
import com.internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        orderService.delete(Long.parseLong(req.getParameter("order_id")));
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
