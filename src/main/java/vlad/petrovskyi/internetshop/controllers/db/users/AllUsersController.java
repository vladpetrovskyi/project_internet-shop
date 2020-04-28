package vlad.petrovskyi.internetshop.controllers.db.users;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.model.User;
import vlad.petrovskyi.internetshop.service.UserService;

public class AllUsersController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAll();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users/allUsers.jsp").forward(req, resp);
    }
}
