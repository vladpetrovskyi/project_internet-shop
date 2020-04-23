package vlad.petrovskyi.internetshop.dao;

import vlad.petrovskyi.internetshop.model.User;

import java.util.List;

public interface UserDao {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
