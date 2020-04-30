package vlad.petrovskyi.internetshop.service;

import java.util.Optional;
import vlad.petrovskyi.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> getByLogin(String login);
}
