package vlad.petrovskyi.internetshop.controllers.user;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.ProductService;

public class AllProductsToBuyController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = productService.getAll();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("WEB-INF/views/products/allProductsBuy.jsp").forward(req, resp);
    }
}
