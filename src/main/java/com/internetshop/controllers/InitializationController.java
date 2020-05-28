package com.internetshop.controllers;

import com.internetshop.lib.Injector;
import com.internetshop.model.Product;
import com.internetshop.model.Role;
import com.internetshop.model.User;
import com.internetshop.service.ProductService;
import com.internetshop.service.ShoppingCartService;
import com.internetshop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitializationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internetshop");
    final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        productService.create(
                new Product("iPhone 11", new BigDecimal("999")));
        productService.create(
                new Product("iPhone 11 Pro Max", new BigDecimal("1299")));
        productService.create(
                new Product("iPhone Xr", new BigDecimal("699")));

        User admin = new User("Administrator", "admin", "admin");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        User anton = new User("User 1", "user1", "user1");
        anton.setRoles(Set.of(Role.of("USER")));
        anton = userService.create(anton);
        shoppingCartService.create(anton.getId());
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
