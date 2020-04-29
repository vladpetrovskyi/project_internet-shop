package vlad.petrovskyi.internetshop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.OrderService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;
import vlad.petrovskyi.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = orderService.completeOrder(
                shoppingCartService.getByUserId(USER_ID).getProducts(),
                userService.get(USER_ID)).getId();
        String messageStr = "Successful checkout! Your order number is #" + orderId + ".";
        req.setAttribute("message", messageStr);
        req.getRequestDispatcher("/WEB-INF/views/users/userPage.jsp").forward(req, resp);
    }
}
