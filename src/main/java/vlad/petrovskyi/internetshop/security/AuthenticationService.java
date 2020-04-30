package vlad.petrovskyi.internetshop.security;

import vlad.petrovskyi.internetshop.exceptions.AuthenticationException;
import vlad.petrovskyi.internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String pass) throws AuthenticationException;
}
