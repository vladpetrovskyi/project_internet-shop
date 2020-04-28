package vlad.petrovskyi.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("userId", req.getParameter("user_id"));
        req.getRequestDispatcher("WEB-INF/views/users/userPage.jsp").forward(req, resp);
    }
}
