package vlad.petrovskyi.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.OrderService;
import vlad.petrovskyi.internetshop.service.ProductService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;
import vlad.petrovskyi.internetshop.service.UserService;

public class InitializationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");
    final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        productService.create(
                new Product("iPhone 11", new BigDecimal("999.00")));
        productService.create(
                new Product("iPhone 11 Pro Max", new BigDecimal("1299.00")));
        productService.create(
                new Product("iPhone Xr", new BigDecimal("699.00")));

        User michael = userService.create(new User("Michael", "mich234", "13243546"));
        User anton = userService.create(new User("Anton", "antoine2233", "1111"));
        User eugene = userService.create(new User("Eugene", "yogibear", "123454321"));
        User john = userService.create(new User("John", "j_mm3", "44113468"));

        shoppingCartService.create(michael.getId());
        shoppingCartService.create(anton.getId());
        shoppingCartService.create(eugene.getId());
        shoppingCartService.create(john.getId());
        resp.sendRedirect(req.getContextPath() + "/");

        orderService.completeOrder(
                shoppingCartService.getByUserId(michael.getId()).getProducts(), michael);
    }
}
