package vlad.petrovskyi.internetshop.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Role;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.OrderService;
import vlad.petrovskyi.internetshop.service.UserService;

public class ViewOrderController extends HttpServlet {

    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = orderService.get(Long.parseLong(req.getParameter("order_id")));
        req.setAttribute("order", order);
        req.setAttribute("sum", orderService.sum(order.getProducts()));
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));

        if (user.getRoles()
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
