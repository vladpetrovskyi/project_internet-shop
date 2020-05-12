package vlad.petrovskyi.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import vlad.petrovskyi.internetshop.dao.ProductDao;
import vlad.petrovskyi.internetshop.exceptions.DataProcessingException;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.Product;
import vlad.petrovskyi.internetshop.util.ConnectionUtil;

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
            throw new DataProcessingException("Could not add new product into DB");
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String request = "SELECT * FROM products WHERE product_id = " + id;

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find product with ID#" + id + " in DB");
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String request = "SELECT * FROM products";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productList.add(getProductFromResultSet(resultSet));
            }
            return productList;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not create list of products from DB.");
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
        } catch (SQLException ex) {
            throw new DataProcessingException("Could not update a product with ID#"
                    + element.getId() + " in DB.");
        }
    }

    @Override
    public boolean delete(Long id) {
        String request = "DELETE FROM products WHERE product_id = " + id;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(request)) {
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find product with ID#" + id + " in DB");
        }
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getString("name"),
                rs.getBigDecimal("price"));
        product.setId(rs.getLong("product_id"));
        return product;
    }
}
