package vlad.petrovskyi.internetshop.service;

import java.math.BigDecimal;
import java.util.List;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    BigDecimal sum(List<Product> products);
}
