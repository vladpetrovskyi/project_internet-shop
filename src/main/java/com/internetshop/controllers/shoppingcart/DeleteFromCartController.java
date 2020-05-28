package com.internetshop.controllers.shoppingcart;

import com.internetshop.lib.Injector;
import com.internetshop.service.ProductService;
import com.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFromCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        shoppingCartService.deleteProduct(
                shoppingCartService.getByUserId((Long) req.getSession().getAttribute(USER_ID)),
                productService.get(Long.valueOf(req.getParameter("product_id"))));
        resp.sendRedirect(req.getContextPath() + "/user/cart");
    }
}
