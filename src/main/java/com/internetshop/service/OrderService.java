package com.internetshop.service;

import com.internetshop.model.Order;
import com.internetshop.model.Product;
import com.internetshop.model.User;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    BigDecimal sum(List<Product> products);
}
