package vlad.petrovskyi.internetshop.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.service.OrderService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;
import vlad.petrovskyi.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (shoppingCartService.getByUserId(userId).getProducts().isEmpty()) {
            String message = "Your shopping cart is empty!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/views/shoppingcart/viewShoppingCart.jsp")
                    .forward(req, resp);
        }
        Order order = orderService.completeOrder(
                shoppingCartService.getByUserId(userId).getProducts(),
                userService.get(userId));
        String messageStr = "Successful checkout! Your order number is #" + order.getId() + ".";
        req.setAttribute("message", messageStr);
        req.setAttribute("order", order);
        req.setAttribute("sum", orderService.sum(order.getProducts()));
        req.getRequestDispatcher("/WEB-INF/views/products/productsFromOrderUser.jsp")
                .forward(req, resp);
    }
}
