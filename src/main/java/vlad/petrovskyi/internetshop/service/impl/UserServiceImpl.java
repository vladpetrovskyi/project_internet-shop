package vlad.petrovskyi.internetshop.service.impl;

import vlad.petrovskyi.internetshop.dao.UserDao;
import vlad.petrovskyi.internetshop.db.Storage;
import vlad.petrovskyi.internetshop.lib.Dao;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Dao
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No user found with ID " + id + " found!"));
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
}
