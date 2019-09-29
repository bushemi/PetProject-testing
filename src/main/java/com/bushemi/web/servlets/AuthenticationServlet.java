package com.bushemi.web.servlets;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.implementations.SecurityServiceImpl;
import com.bushemi.service.implementations.UserParserService;
import com.bushemi.service.interfaces.SecurityService;
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
    private final UserParserService userParserService = new UserParserService();
    private final SecurityService securityService = new SecurityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        LOG.info("try to log in from session with id = {}", session.getId());
        Object textFromRequest = session.getAttribute("requestBody");
        session.setAttribute("requestBody", null);
        if (nonNull(textFromRequest)) {
            UserCreatingDto userCreatingDto = userParserService.fromString(textFromRequest.toString());
            UserForSessionDto userForSessionDto;
            try {
                userForSessionDto = securityService.login(userCreatingDto);
            } catch (Exception e) {
                addGeneralErrorsAndRedirectToLoginPage(resp, session);
                LOG.error("An error occurs = {}", e);
                return;
            }

            LOG.info("logged in with user = {}", userForSessionDto);
            addUserInformationToSessionAndRedirectToNavigationPage(resp, session, userForSessionDto);
            return;
        }
        LOG.error("empty request body");
        addGeneralErrorsAndRedirectToLoginPage(resp, session);
    }

    private void addGeneralErrorsAndRedirectToLoginPage(HttpServletResponse resp, HttpSession session) throws IOException {
        session.setAttribute("loginError", "Проверьте логин");
        session.setAttribute("passwordError", "Проверьте пароль");
        resp.sendRedirect("login.jsp");
    }

    static void addUserInformationToSessionAndRedirectToNavigationPage(HttpServletResponse resp, HttpSession session, UserForSessionDto userForSessionDto) throws IOException {
        session.setAttribute("login", userForSessionDto.getLogin());
        session.setAttribute("locale", userForSessionDto.getLocale().getShortName());
        session.setAttribute("role", userForSessionDto.getRole().getRoleName());
        session.setAttribute("userId", userForSessionDto.getId());

        resp.sendRedirect("navigation");
    }
}
