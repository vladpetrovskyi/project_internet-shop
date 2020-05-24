package vlad.petrovskyi.internetshop.controllers.products;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.ProductService;

public class AddProductController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/addNewProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        productService.create(new Product(name, new BigDecimal(price)));
        resp.sendRedirect(req.getContextPath() + "/products/allFromDb");
    }
}
