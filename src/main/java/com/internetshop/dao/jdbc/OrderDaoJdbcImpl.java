package com.internetshop.dao.jdbc;

import com.internetshop.dao.OrderDao;
import com.internetshop.exceptions.DataProcessingException;
import com.internetshop.lib.Dao;
import com.internetshop.model.Order;
import com.internetshop.model.Product;
import com.internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public Order create(Order element) {
        String requestForOrderInsertion = "INSERT INTO orders (user_id) VALUES (?)";
        String requestForProductsAddition = "INSERT INTO orders_products VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(requestForOrderInsertion,
                                Statement.RETURN_GENERATED_KEYS);
                PreparedStatement statement2 =
                        connection.prepareStatement(requestForProductsAddition)) {
            statement.setLong(1, element.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                element.setId(rs.getLong(1));
            }
            for (Product product : element.getProducts()) {
                statement2.setLong(1, element.getId());
                statement2.setLong(2, product.getId());
                statement2.executeUpdate();
            }
            return element;
        } catch (SQLException ex) {
            throw new DataProcessingException("Cannot create order in DB.", ex);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String request = "SELECT * FROM orders JOIN orders_products op USING (order_id) "
                + "JOIN products p USING (product_id) WHERE orders.order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            return getOrders(statement.executeQuery()).stream().findFirst();
        } catch (SQLException ex) {
            throw new DataProcessingException("Cannot get order from DB.", ex);
        }
    }

    @Override
    public List<Order> getAll() {
        String request = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            return getOrders(statement.executeQuery());
        } catch (SQLException ex) {
            throw new DataProcessingException("Cannot get order from DB.", ex);
        }
    }

    @Override
    public Order update(Order element) {
        String requestForProductsDeletion =
                "DELETE FROM orders_products WHERE order_id = ?";
        String requestForOrderUpdate = "INSERT INTO orders_products VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletionStatement =
                        connection.prepareStatement(requestForProductsDeletion);
                PreparedStatement updateStatement =
                        connection.prepareStatement(requestForOrderUpdate)) {
            deletionStatement.setLong(1, element.getId());
            deletionStatement.executeUpdate();
            for (Product product : element.getProducts()) {
                updateStatement.setLong(1, element.getId());
                updateStatement.setLong(2, product.getId());
                updateStatement.executeUpdate();
            }
            return element;
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not update order in DB.", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        String requestForOrderProductsDeletion = "DELETE FROM orders_products WHERE order_id = ?";
        String requestForOrderDeletion = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statementOrderProducts =
                        connection.prepareStatement(requestForOrderProductsDeletion);
                PreparedStatement statementOrder =
                        connection.prepareStatement(requestForOrderDeletion)) {
            statementOrderProducts.setLong(1, id);
            statementOrder.setLong(1, id);
            int statement1 = statementOrderProducts.executeUpdate();
            int statement2 = statementOrder.executeUpdate();
            return (statement1 > 0 && statement2 > 0);
        } catch (SQLException ex) {
            throw new DataProcessingException("Cannot delete order from DB.", ex);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        String request = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, userId);
            return getOrders(statement.executeQuery());
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get orders of user with ID#" + userId, e);
        }
    }

    private List<Order> getOrders(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            Long orderId = resultSet.getLong("order_id");
            Order order = new Order(getProductsFromOrder(orderId),
                    resultSet.getLong("user_id"));
            order.setId(orderId);
            orderList.add(order);
        }
        return orderList;
    }

    private List<Product> getProductsFromOrder(Long id) throws SQLException {
        String request = "SELECT * FROM orders_products op JOIN products p "
                + "USING (product_id) WHERE op.order_id = ?";
        List<Product> productList = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"));
                product.setId(resultSet.getLong("product_id"));
                productList.add(product);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return productList;
    }
}
