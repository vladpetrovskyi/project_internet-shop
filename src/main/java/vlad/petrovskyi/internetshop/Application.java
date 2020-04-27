package vlad.petrovskyi.internetshop;

import java.math.BigDecimal;
import java.util.List;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.OrderService;
import vlad.petrovskyi.internetshop.service.ProductService;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;
import vlad.petrovskyi.internetshop.service.UserService;

public class Application {
    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");

    public static void main(String[] args) {
        final ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);
        final UserService userService = (UserService) injector.getInstance(UserService.class);
        final ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        final OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        final Product iPhoneXI = productService.create(
                new Product("iPhone 11", new BigDecimal("999.00")));
        final Product iPhoneXiProMax = productService.create(
                new Product("iPhone 11 Pro Max", new BigDecimal("1299.00")));
        final Product iPhoneXr = productService.create(
                new Product("iPhone Xr", new BigDecimal("699.00")));

        productService.getAll();

        Product productTest = productService.get(2L);

        productTest.setPrice(new BigDecimal("2000.00"));
        productTest.setName("iPhone 5S");
        productService.update(productTest);

        productService.delete(2L);

        User michael = userService.create(new User("Michael", "mich234", "13243546"));
        User anton = userService.create(new User("Anton", "antoine2233", "1111"));
        User eugene = userService.create(new User("Eugene", "yogibear", "123454321"));
        User john = userService.create(new User("John", "j_mm3", "44113468"));

        ShoppingCart shoppingCartMichael = shoppingCartService.getByUserId(michael.getId());
        ShoppingCart shoppingCartAnton = shoppingCartService.getByUserId(anton.getId());
        ShoppingCart shoppingCartEugene = shoppingCartService.getByUserId(eugene.getId());
        ShoppingCart shoppingCartJohn = shoppingCartService.getByUserId(john.getId());

        shoppingCartService.addProduct(shoppingCartMichael, iPhoneXI);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(userService.get(1L).getId()), iPhoneXiProMax);
        shoppingCartService.addProduct(shoppingCartAnton, iPhoneXr);
        shoppingCartService.addProduct(shoppingCartEugene, iPhoneXiProMax);
        shoppingCartService.addProduct(shoppingCartJohn, iPhoneXr);

        shoppingCartService.deleteProduct(shoppingCartService
                .getByUserId(eugene.getId()), iPhoneXiProMax);

        shoppingCartService.clear(shoppingCartService.getByUserId(michael.getId()));

        shoppingCartService.addProduct(shoppingCartMichael, iPhoneXr);
        Order order = orderService.completeOrder(shoppingCartMichael.getProducts(), michael);
        shoppingCartService.clear(shoppingCartService.getByUserId(michael.getId()));

        orderService.get(order.getId());

        List<Order> orderList = orderService.getUserOrders(michael);

        orderService.getAll();

        orderService.delete(order.getId());
    }
}
