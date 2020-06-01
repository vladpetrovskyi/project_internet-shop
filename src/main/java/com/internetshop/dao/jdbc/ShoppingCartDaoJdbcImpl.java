package com.internetshop.dao.jdbc;

import com.internetshop.dao.ProductDao;
import com.internetshop.dao.ShoppingCartDao;
import com.internetshop.exceptions.DataProcessingException;
import com.internetshop.lib.Dao;
import com.internetshop.lib.Inject;
import com.internetshop.model.Product;
import com.internetshop.model.ShoppingCart;
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
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {

    @Inject
    private ProductDao productDao;

    @Override
    public ShoppingCart create(ShoppingCart element) {
        String createCartRequest = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(createCartRequest, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, element.getUserId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                element.setId(resultSet.getLong(1));
            }
            return element;
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not add new shopping cart into DB.", ex);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String getShoppingCartRequest = "SELECT * FROM shopping_carts WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(getShoppingCartRequest)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<ShoppingCart> shoppingCart = getCarts(resultSet).stream().findFirst();
            if (shoppingCart.isPresent()) {
                while (resultSet.next()) {
                    Optional<Product> product = productDao.get(resultSet.getLong("product_id"));
                    product.ifPresent(value -> shoppingCart.get().getProducts().add(value));
                }
                return shoppingCart;
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not get shopping cart from DB.", ex);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        String getShoppingCartRequest = "SELECT * FROM shopping_carts";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(getShoppingCartRequest)) {
            return getCarts(statement.executeQuery());
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not get list of shopping carts from DB.", ex);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        String requestForProductsDeletion =
                "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        String requestForCartUpdate = "INSERT INTO shopping_carts_products VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletionStatement =
                        connection.prepareStatement(requestForProductsDeletion);
                PreparedStatement updateStatement =
                        connection.prepareStatement(requestForCartUpdate)) {
            deletionStatement.setLong(1, element.getId());
            deletionStatement.executeUpdate();
            for (Product product : element.getProducts()) {
                updateStatement.setLong(1, element.getId());
                updateStatement.setLong(2, product.getId());
                updateStatement.executeUpdate();
            }
            return element;
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not update shopping cart in DB.", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteFromCartsRequest = "DELETE FROM shopping_carts WHERE cart_id = ?";
        String deleteFromCartsProductsRequest =
                "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement cartsProductsStatement =
                        connection.prepareStatement(deleteFromCartsProductsRequest);
                PreparedStatement cartsStatement =
                        connection.prepareStatement(deleteFromCartsRequest)) {
            cartsProductsStatement.setLong(1, id);
            cartsStatement.setLong(1, id);
            int statement1 = cartsProductsStatement.executeUpdate();
            int statement2 = cartsStatement.executeUpdate();
            return (statement1 > 0 && statement2 > 0);
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not delete cart from DB.", ex);
        }
    }

    private List<ShoppingCart> getCarts(ResultSet rs) throws SQLException {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        while (rs.next()) {
            ShoppingCart sc = new ShoppingCart(rs.getLong("user_id"));
            long cartId = rs.getLong("cart_id");
            sc.setId(cartId);
            sc.setProducts(getProductsFromCart(cartId));
            shoppingCarts.add(sc);
        }
        return shoppingCarts;
    }

    private List<Product> getProductsFromCart(Long id) throws SQLException {
        List<Product> productList = new ArrayList<>();
        String request = "SELECT * FROM shopping_carts_products WHERE cart_id = ?";
        ResultSet resultSet = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productDao.get(resultSet.getLong("product_id")).ifPresent(productList::add);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return productList;
    }
}
