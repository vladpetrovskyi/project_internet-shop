package vlad.petrovskyi.internetshop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import vlad.petrovskyi.internetshop.dao.OrderDao;
import vlad.petrovskyi.internetshop.dao.ShoppingCartDao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = new Order(List.copyOf(products), user);
        shoppingCartDao.getAll().stream()
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .forEach(c -> c.getProducts().clear());
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll().stream()
                .filter(o -> o.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal sum(List<Product> products) {
        return BigDecimal.valueOf(products.stream()
                .map(p -> p.getPrice().longValue())
                .reduce(Long::sum)
                .orElse(0L));
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
