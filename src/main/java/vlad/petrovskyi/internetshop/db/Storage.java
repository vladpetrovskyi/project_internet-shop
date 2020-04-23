package vlad.petrovskyi.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.ShoppingCart;
import vlad.petrovskyi.internetshop.model.User;

public class Storage {

    public static final List<Product> products = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static Long productId = 0L;
    private static Long userId = 0L;
    private static Long orderId = 0L;
    private static Long shoppingCartId = 0L;

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        Storage.products.add(product);
    }

    public static void addUser(User user) {
        userId++;
        user.setId(userId);
        Storage.users.add(user);
    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        Storage.orders.add(order);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartId++;
        shoppingCart.setId(shoppingCartId);
        Storage.shoppingCarts.add(shoppingCart);
    }
}
