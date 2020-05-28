package com.internetshop.controllers.products;

import com.internetshop.lib.Injector;
import com.internetshop.model.Product;
import com.internetshop.service.ProductService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllProductsInDbController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = productService.getAll();

        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/views/products/allProductsEdit.jsp").forward(req, resp);
    }
}
