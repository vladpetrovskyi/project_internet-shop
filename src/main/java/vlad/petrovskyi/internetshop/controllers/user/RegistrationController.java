package vlad.petrovskyi.internetshop.controllers.user;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.Role;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.ShoppingCartService;
import vlad.petrovskyi.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("pass_Repeat");
        String nameSurname = req.getParameter("name_Surname");

        if (pass.equals(passRepeat)) {
            User user = new User(nameSurname, login, pass);
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            Long id = user.getId();
            shoppingCartService.create(id);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("message", "Passwords do not match!");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
