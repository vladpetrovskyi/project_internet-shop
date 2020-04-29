package vlad.petrovskyi.internetshop.controllers.db.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.service.OrderService;

public class ViewOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("order_id"));
        List<Product> products = (orderService.get(id)).getProducts();
        req.setAttribute("order_id", id);
        req.setAttribute("products", products);
        req.setAttribute("sum", products.stream()
                .map(p -> p.getPrice().longValue())
                .reduce(Long::sum)
                .orElse(0L));
        req.getRequestDispatcher("/WEB-INF/views/products/productsFromOrder.jsp")
                .forward(req, resp);
    }
}
