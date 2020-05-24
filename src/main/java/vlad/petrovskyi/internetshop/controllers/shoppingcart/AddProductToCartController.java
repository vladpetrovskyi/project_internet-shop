package vlad.petrovskyi.internetshop.controllers.shoppingcart;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.ProductService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

public class AddProductToCartController extends HttpServlet {
    private static final String USER_ID = "user_id";

    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        shoppingCartService.addProduct(
                shoppingCartService.getByUserId((Long) req.getSession().getAttribute(USER_ID)),
                productService.get(Long.valueOf(req.getParameter("product_id"))));
        resp.sendRedirect(req.getContextPath() + "/products/allAvailable");
    }
}
