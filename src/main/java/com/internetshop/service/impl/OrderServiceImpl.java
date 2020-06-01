package com.internetshop.service.impl;

import com.internetshop.dao.OrderDao;
import com.internetshop.dao.ShoppingCartDao;
import com.internetshop.lib.Inject;
import com.internetshop.lib.Service;
import com.internetshop.model.Order;
import com.internetshop.model.Product;
import com.internetshop.model.ShoppingCart;
import com.internetshop.model.User;
import com.internetshop.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = new Order(List.copyOf(products), user.getId());
        Optional<ShoppingCart> shoppingCart = shoppingCartDao.getAll().stream()
                .filter(c -> c.getUserId().equals(user.getId()))
                .findFirst();
        if (shoppingCart.isPresent()) {
            shoppingCart.get().getProducts().clear();
            shoppingCartDao.update(shoppingCart.get());
        }
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user.getId());
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
        return orderDao.get(id).orElseThrow();
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
