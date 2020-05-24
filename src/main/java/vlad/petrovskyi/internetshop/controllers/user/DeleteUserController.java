package vlad.petrovskyi.internetshop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vlad.petrovskyi.internetshop.lib.Injector;
import vlad.petrovskyi.internetshop.service.UserService;

public class DeleteUserController extends HttpServlet {

    private static final Injector INJECTOR = Injector.getInstance("vlad.petrovskyi.internetshop");
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String userId = req.getParameter("user_id");
        userService.delete(Long.valueOf(userId));

        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
