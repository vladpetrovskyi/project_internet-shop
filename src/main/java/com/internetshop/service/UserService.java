package com.internetshop.service;

import com.internetshop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> getByLogin(String login);
}
