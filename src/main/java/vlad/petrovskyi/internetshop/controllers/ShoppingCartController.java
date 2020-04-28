package vlad.petrovskyi.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

public class ShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products",
                shoppingCartService.getAllProducts(shoppingCartService.getByUserId(USER_ID)));
        request.getRequestDispatcher("/WEB-INF/views/shoppingcart/viewShoppingCart.jsp")
                .forward(request, response);
    }
}
