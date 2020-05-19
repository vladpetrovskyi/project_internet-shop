package vlad.petrovskyi.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import vlad.petrovskyi.internetshop.dao.UserDao;
import vlad.petrovskyi.internetshop.exceptions.DataProcessingException;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.model.Role;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> getByLogin(String login) {
        String request = "SELECT * FROM users JOIN users_roles ON users.user_id = "
                + "users_roles.user_id JOIN roles ON users_roles.role_id = roles.role_id "
                + "WHERE users.login = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, login);
            return getUserExecution(statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user with login '"
                    + login + "' in DB.", e);
        }
    }

    @Override
    public User create(User element) {
        String createUserRequest = "INSERT INTO users (name, login, password, salt) "
                + "VALUES (?, ?, ?, ?)";
        String findRoleRequest = "SELECT role_id FROM roles WHERE role_name = ?";
        String connectUserAndRoleRequest = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, ?)";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement insertUser = connection
                        .prepareStatement(createUserRequest, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement findRole = connection.prepareStatement(findRoleRequest);
                PreparedStatement boundUserAndRole = connection
                        .prepareStatement(connectUserAndRoleRequest)) {
            insertUser.setString(1, element.getName());
            insertUser.setString(2, element.getLogin());
            insertUser.setString(3, element.getPassword());
            insertUser.setBytes(4, element.getSalt());
            insertUser.executeUpdate();

            ResultSet rs = insertUser.getGeneratedKeys();
            if (rs.next()) {
                long userId = rs.getLong(1);
                element.setId(userId);

                for (Role role : element.getRoles()) {
                    findRole.setString(1, role.getRoleName().name());
                    ResultSet resultSet = findRole.executeQuery();
                    while (resultSet.next()) {
                        boundUserAndRole.setLong(1, userId);
                        boundUserAndRole.setLong(2, resultSet.getLong("role_id"));
                        boundUserAndRole.executeUpdate();
                    }
                }
            }
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not add new user into DB.", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String request = "SELECT * FROM users JOIN users_roles ON users.user_id = "
                + "users_roles.user_id JOIN roles ON users_roles.role_id = roles.role_id "
                + "WHERE users.user_id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            return getUserExecution(statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user with ID#" + id + " in DB.", e);
        }
    }

    @Override
    public List<User> getAll() {
        String request = "SELECT * FROM users JOIN users_roles ON users.user_id = "
                + "users_roles.user_id JOIN roles ON users_roles.role_id = roles.role_id";

        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(request)) {
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
            throw new DataProcessingException("Could not update a user with ID#"
                    + element.getId() + " in DB.", e);
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
            return statement.executeUpdate() > 0 & statement2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not find user with ID#" + id + " in DB.", e);
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
}
