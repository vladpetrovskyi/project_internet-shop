package vlad.petrovskyi.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vlad.petrovskyi.internetshop.dao.ProductDao;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER =
            LogManager.getLogger(vlad.petrovskyi.internetshop.dao.jdbc.ProductDaoJdbcImpl.class);

    @Override
    public Product create(Product element) {
        String request = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.executeUpdate();
            element.setId(statement.getGeneratedKeys().getLong("product_id"));
            LOGGER.info("New product has been added");
            return element;
        } catch (SQLException e) {
            LOGGER.warn("Could not add new product into DB", e);
            throw new RuntimeException("Could not add new product into DB", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String request = "SELECT * FROM products WHERE product_id = " + id;

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                BigDecimal productPrice = resultSet.getBigDecimal("price");
                product = new Product(productName, productPrice);
                product.setId(id);
            }
            LOGGER.info("Product with ID#" + id + " has been found.");
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            LOGGER.warn("Could not find product with ID#" + id + " in DB", e);
            throw new RuntimeException("Could not find product with ID#" + id + " in DB", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String request = "SELECT * FROM products";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Product product;
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String productName = resultSet.getString("name");
                BigDecimal productPrice = resultSet.getBigDecimal("price");
                product = new Product(productName, productPrice);
                product.setId(productId);
                productList.add(product);
            }
            LOGGER.info("List of all products from DB is created.");
            return productList;
        } catch (SQLException e) {
            LOGGER.warn("Could not create list of products from DB.", e);
            throw new RuntimeException("Could not create list of products from DB.", e);
        }
    }

    @Override
    public Product update(Product element) {
        String request = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
            LOGGER.info("An element with ID#" + element.getId() + " is updated.");
            return element;
        } catch (SQLException ex) {
            LOGGER.warn("Could not update a product with ID#"
                    + element.getId() + " in DB.", ex);
            throw new RuntimeException("Could not update a product with ID#"
                    + element.getId() + " in DB.", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        String request = "DELETE FROM products WHERE product_id = " + id;

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.executeUpdate();
            LOGGER.info("Product with ID#" + id + " has been deleted.");
            return true;
        } catch (SQLException e) {
            LOGGER.warn("Could not find product with ID#" + id + " in DB", e);
            throw new RuntimeException("Could not find product with ID#" + id + " in DB", e);
        }
    }
}
