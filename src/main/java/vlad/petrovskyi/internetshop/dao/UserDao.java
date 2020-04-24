package vlad.petrovskyi.internetshop.dao;

import java.util.List;
import java.util.Optional;
import vlad.petrovskyi.internetshop.model.User;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
