package com.internetshop.dao;

import com.internetshop.model.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(Long userId);
}
