package vlad.petrovskyi.internetshop.controllers.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

public class ShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products",
                shoppingCartService.getAllProducts(shoppingCartService.getByUserId(USER_ID)));
        request.setAttribute("sum", shoppingCartService.getAllProducts(
                shoppingCartService.getByUserId(USER_ID))
                .stream()
                .map(p -> p.getPrice().longValue())
                .reduce(Long::sum).orElse(0L));
        request.getRequestDispatcher("/WEB-INF/views/shoppingcart/viewShoppingCart.jsp")
                .forward(request, response);
    }
}
