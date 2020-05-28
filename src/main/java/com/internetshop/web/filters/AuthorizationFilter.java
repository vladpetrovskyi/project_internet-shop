package com.internetshop.web.filters;

import com.internetshop.lib.Injector;
import com.internetshop.model.Role;
import com.internetshop.model.User;
import com.internetshop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorizationFilter implements Filter {

    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.internetshop");
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private final Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/allFromDb", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/addNew", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/deleteFromDb", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin", Set.of(Role.RoleName.ADMIN));

        protectedUrls.put("/user/cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/product/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/user", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/checkout", Set.of(Role.RoleName.USER));
        protectedUrls.put("/products/allAvailable", Set.of(Role.RoleName.USER));
        protectedUrls.put("/user/orders", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Set<Role.RoleName> roleSet = protectedUrls.get(req.getServletPath());

        if (roleSet == null) {
            filterChain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (!isAuthorized(userService.get(userId), roleSet)) {
            LOGGER.warn("Unauthorized try of access by user with ID#" + userId);
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> roles) {
        for (Role.RoleName authorizedRoles : roles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRoles.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
