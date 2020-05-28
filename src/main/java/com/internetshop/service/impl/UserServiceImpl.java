package com.internetshop.service.impl;

import com.internetshop.dao.ShoppingCartDao;
import com.internetshop.dao.UserDao;
import com.internetshop.lib.Inject;
import com.internetshop.lib.Service;
import com.internetshop.model.ShoppingCart;
import com.internetshop.model.User;
import com.internetshop.service.UserService;
import com.internetshop.util.HashUtil;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        user.setSalt(salt);
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public boolean delete(Long id) {
        shoppingCartDao.delete(
                shoppingCartDao.getAll()
                        .stream()
                        .filter(c -> c.getUserId().equals(id))
                        .map(ShoppingCart::getId)
                        .findFirst()
                        .orElseThrow());
        return userDao.delete(id);
    }
}
