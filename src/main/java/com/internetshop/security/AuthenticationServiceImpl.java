package com.internetshop.security;

import com.internetshop.exceptions.AuthenticationException;
import com.internetshop.lib.Inject;
import com.internetshop.lib.Service;
import com.internetshop.model.User;
import com.internetshop.service.UserService;
import com.internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserService userService;

    @Override
    public User login(String login, String pass) throws AuthenticationException {
        User userFromDb = userService.getByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));

        if (HashUtil.isValid(pass, userFromDb.getPassword(), userFromDb.getSalt())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
