package vlad.petrovskyi.internetshop.dao.impl;

import vlad.petrovskyi.internetshop.dao.OrderDao;
import vlad.petrovskyi.internetshop.model.Order;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.model.User;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order completeOrder(List<Product> products, User user) {
        return null;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return null;
    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
