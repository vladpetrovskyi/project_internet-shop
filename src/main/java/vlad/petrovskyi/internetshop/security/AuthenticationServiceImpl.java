package vlad.petrovskyi.internetshop.security;

import vlad.petrovskyi.internetshop.exceptions.AuthenticationException;
import vlad.petrovskyi.internetshop.lib.Inject;
import vlad.petrovskyi.internetshop.lib.Service;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.UserService;
import vlad.petrovskyi.internetshop.util.HashUtil;

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
