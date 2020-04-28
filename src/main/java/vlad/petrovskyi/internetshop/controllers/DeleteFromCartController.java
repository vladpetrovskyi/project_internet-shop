package vlad.petrovskyi.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.ProductService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;

public class DeleteFromCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        shoppingCartService.deleteProduct(
                shoppingCartService.getByUserId(USER_ID),
                productService.get(Long.valueOf(req.getParameter("product_id"))));
        resp.sendRedirect(req.getContextPath() + "/viewCart");
    }
}
