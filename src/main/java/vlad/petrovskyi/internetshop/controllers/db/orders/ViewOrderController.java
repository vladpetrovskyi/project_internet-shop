package vlad.petrovskyi.internetshop.controllers.db.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.service.OrderService;

public class ViewOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = orderService.get(Long.parseLong(req.getParameter("order_id")));
        req.setAttribute("order", order);
        req.setAttribute("sum", orderService.sum(order.getProducts()));
        req.getRequestDispatcher("/WEB-INF/views/products/productsFromOrder.jsp")
                .forward(req, resp);
    }
}
