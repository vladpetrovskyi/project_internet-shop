package com.internetshop.dao.jdbc;

import com.internetshop.dao.UserDao;
import com.internetshop.exceptions.DataProcessingException;
import com.internetshop.lib.Dao;
import com.internetshop.lib.Inject;
import com.internetshop.model.Role;
import com.internetshop.model.User;
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
public class UserDaoJdbcImpl implements UserDao {

    @Inject

    private static final String SELECT_USER_BY_PARAMETER = "SELECT * FROM users JOIN users_roles "
            + "USING (user_id) JOIN roles USING (role_id) ";

    @Override
    public Optional<User> getByLogin(String login) {
        String request = SELECT_USER_BY_PARAMETER + "WHERE users.login = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, login);
            return getUserExecution(statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user in DB with login '"
                    + login + "'.", e);
        }
    }

    @Override
    public User create(User element) {
        String createUserRequest = "INSERT INTO users (name, login, password, salt) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement insertUser = connection
                        .prepareStatement(createUserRequest, Statement.RETURN_GENERATED_KEYS)) {
            insertUser.setString(1, element.getName());
            insertUser.setString(2, element.getLogin());
            insertUser.setString(3, element.getPassword());
            insertUser.setBytes(4, element.getSalt());
            insertUser.executeUpdate();

            ResultSet rs = insertUser.getGeneratedKeys();
            if (rs.next()) {
                long userId = rs.getLong(1);
                element.setId(userId);
                roleSetter(connection, element);
            }
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not add new user into DB.", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String request = SELECT_USER_BY_PARAMETER + "WHERE users.user_id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            return getUserExecution(statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user in DB with ID#" + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(SELECT_USER_BY_PARAMETER)) {
            return getUsersFromResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DataProcessingException("Could not create list of users from DB.", e);
        }
    }

    @Override
    public User update(User element) {
        String request = "UPDATE users SET name = ?, login = ?, password = ?, salt = ? "
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(3, element.getSalt());
            statement.setLong(5, element.getId());
            statement.executeUpdate();
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not update a user in DB with ID#"
                    + element.getId(), e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String requestToDeleteFromUsers = "DELETE FROM users WHERE user_id = ?";
        String requestToDeleteFromUsersRoles = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(requestToDeleteFromUsersRoles);
                PreparedStatement statement2 =
                        connection.prepareStatement(requestToDeleteFromUsers)) {
            statement.setLong(1, id);
            statement2.setLong(1, id);
            int statement1Result = statement.executeUpdate();
            int statement2Result = statement2.executeUpdate();
            return (statement1Result > 0 && statement2Result > 0);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user in DB with ID#" + id, e);
        }
    }

    private Optional<User> getUserExecution(PreparedStatement statement) throws SQLException {
        Optional<User> user = getUsersFromResultSet(statement.executeQuery()).stream().findFirst();
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return user;
    }

    private List<User> getUsersFromResultSet(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User(rs.getString("name"),
                    rs.getString("login"), rs.getString("password"));
            Long userId = rs.getLong("user_id");
            user.setId(userId);
            user.getRoles().add(Role.of(rs.getString("role_name")));
            user.setSalt(rs.getBytes("salt"));
            if (userList.contains(user)) {
                userList.get(userList.indexOf(user))
                        .getRoles()
                        .add(Role.of(rs.getString("role_name")));
            } else {
                userList.add(user);
            }
        }
        return userList;
    }

    private void roleSetter(Connection connection, User element) throws SQLException {
        String findRoleRequest = "SELECT role_id FROM roles WHERE role_name = ?";
        String connectUserAndRoleRequest = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement findRole = connection.prepareStatement(findRoleRequest);
                    PreparedStatement boundUserAndRole =
                            connection.prepareStatement(connectUserAndRoleRequest)) {
            for (Role role : element.getRoles()) {
                findRole.setString(1, role.getRoleName().name());
                resultSet = findRole.executeQuery();
                while (resultSet.next()) {
                    boundUserAndRole.setLong(1, element.getId());
                    boundUserAndRole.setLong(2, resultSet.getLong("role_id"));
                    boundUserAndRole.executeUpdate();
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
}
