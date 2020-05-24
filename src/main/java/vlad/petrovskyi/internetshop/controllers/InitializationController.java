package vlad.petrovskyi.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.Role;
import vlad.petrovskyi.internetshop.model.User;
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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        productService.create(
                new Product("iPhone 11", new BigDecimal("999")));
        productService.create(
                new Product("iPhone 11 Pro Max", new BigDecimal("1299")));
        productService.create(
                new Product("iPhone Xr", new BigDecimal("699")));

        User admin = new User("Vlad Petrovskyi", "admin", "admin");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        User michael = new User("Michael Weber", "mich234", "13243546");
        michael.setRoles(Set.of(Role.of("USER")));
        michael = userService.create(michael);
        User anton = new User("Anton Kurochkin", "antoine2233", "1111");
        anton.setRoles(Set.of(Role.of("USER")));
        anton = userService.create(anton);
        User eugene = new User("Rob Whilson", "robbi", "123454321");
        eugene.setRoles(Set.of(Role.of("USER")));
        eugene = userService.create(eugene);
        User john = new User("John Michael", "j_mm3", "44113468");
        john.setRoles(Set.of(Role.of("USER")));
        john = userService.create(john);

        shoppingCartService.create(michael.getId());
        shoppingCartService.create(anton.getId());
        shoppingCartService.create(eugene.getId());
        shoppingCartService.create(john.getId());
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
