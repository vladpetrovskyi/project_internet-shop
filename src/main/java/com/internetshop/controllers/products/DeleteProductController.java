package com.internetshop.controllers.products;

import com.internetshop.lib.Injector;
import com.internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        productService.delete(Long.valueOf(req.getParameter("product_id")));
        resp.sendRedirect(req.getContextPath() + "/products/allFromDb");
    }
}
