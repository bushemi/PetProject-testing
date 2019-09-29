package com.bushemi.web.servlets;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.SecurityService;
import com.bushemi.service.SecurityServiceImpl;
import com.bushemi.service.UserParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthenticationServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("AuthenticationServlet");
    private static final String LOGIN_PAGE = "/login";
    private final UserParserService userParserService = new UserParserService();
    private final SecurityService securityService = new SecurityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        LOG.info("try to log in from session with id = {}", session.getId());
        Object textFromRequest = session.getAttribute("textFromRequest");
        session.setAttribute("textFromRequest", null);
        if (nonNull(textFromRequest)) {
            UserCreatingDto userCreatingDto = userParserService.fromString(textFromRequest.toString());

            UserForSessionDto userForSessionDto;
            try {
                userForSessionDto = securityService.login(userCreatingDto);
            } catch (Exception e) {
                session.setAttribute("loginError", "Проверьте логин");
                session.setAttribute("passwordError", "Проверьте пароль");
                resp.sendRedirect(LOGIN_PAGE);
                LOG.error("An error occurs = {}", e);
                return;
            }

            LOG.info("logged in with user = {}", userForSessionDto);
            session.setAttribute("login", userForSessionDto.getLogin());
            session.setAttribute("locale", userForSessionDto.getLocale().getShortName());
            session.setAttribute("role", userForSessionDto.getRole().getRoleName());
            session.setAttribute("userId", userForSessionDto.getId());

            resp.sendRedirect("navigation");
            return;
        }
        LOG.error("empty request body");
        resp.sendRedirect(LOGIN_PAGE);
    }
}
