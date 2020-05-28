package com.internetshop.dao.jdbc;

import com.internetshop.dao.ProductDao;
import com.internetshop.exceptions.DataProcessingException;
import com.internetshop.lib.Dao;
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
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product element) {
        String request = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(request, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                element.setId(rs.getLong(1));
            }
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not add new product into DB.", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String request = "SELECT * FROM products WHERE product_id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getProductFromResultSet(resultSet).stream().findFirst();
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find product in DB with ID#" + id, e);
        }
    }

    @Override
    public List<Product> getAll() {
        String request = "SELECT * FROM products";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            ResultSet resultSet = statement.executeQuery();
            return getProductFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not create list of products from DB.", e);
        }
    }

    @Override
    public Product update(Product element) {
        String request = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not update a product in DB with ID#"
                    + element.getId(), e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String request = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find product in DB with ID#" + id, e);
        }
    }

    private List<Product> getProductFromResultSet(ResultSet rs) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product(rs.getString("name"),
                    rs.getBigDecimal("price"));
            product.setId(rs.getLong("product_id"));
            productList.add(product);
        }
        return productList;
    }
}
