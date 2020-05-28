package com.internetshop.dao.impl;

import com.internetshop.dao.UserDao;
import com.internetshop.db.Storage;
import com.internetshop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(x -> user.getId().equals(Storage.users.get(x).getId()))
                .forEach(u -> Storage.users.set(u, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }
}
