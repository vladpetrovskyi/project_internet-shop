package com.internetshop.controllers.shoppingcart;

import com.internetshop.lib.Injector;
import com.internetshop.model.ShoppingCart;
import com.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartController extends HttpServlet {
    private static final String USER_ID = "user_id";

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShoppingCart cart = shoppingCartService.getByUserId(
                (Long) request.getSession().getAttribute(USER_ID));
        request.setAttribute("products", shoppingCartService.getAllProducts(cart));
        request.setAttribute("sum", shoppingCartService.getAllProducts(cart)
                .stream()
                .map(p -> p.getPrice().longValue())
                .reduce(Long::sum).orElse(0L));
        request.getRequestDispatcher("/WEB-INF/views/shoppingcart/viewShoppingCart.jsp")
                .forward(request, response);
    }
}
