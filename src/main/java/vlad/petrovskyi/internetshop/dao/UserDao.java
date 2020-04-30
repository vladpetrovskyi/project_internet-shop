package vlad.petrovskyi.internetshop.dao;

import java.util.Optional;
import vlad.petrovskyi.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> getByLogin(String login);
}
