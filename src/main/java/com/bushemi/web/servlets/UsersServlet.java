package com.bushemi.web.servlets;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.interfaces.UserService;
import com.bushemi.service.implementations.UserServiceImpl;
import com.bushemi.service.implementations.UserParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.bushemi.web.servlets.AuthenticationServlet.addUserInformationToSessionAndRedirectToNavigationPage;
import static java.util.Objects.nonNull;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("UsersServlet");
    private final UserParserService userParser = new UserParserService();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        LOG.info("doPost from user with session id = {}", session.getId());
        Object fromRequest = session.getAttribute("requestBody");
        session.setAttribute("requestBody", null);
        if (nonNull(fromRequest)) {
            LOG.info("retrieved string from session = {}", fromRequest);
            UserCreatingDto userCreatingDto = userParser.fromString(fromRequest.toString());
            if (userService.isUserExist(userCreatingDto.getLogin())) {
                session.setAttribute("loginError", "Указанный логин уже занят");
                resp.sendRedirect("login.jsp");
                LOG.info("login is already taken");
            } else {
                UserForSessionDto savedUser = userService.save(userCreatingDto);
                addUserInformationToSessionAndRedirectToNavigationPage(resp, session, savedUser);
                resp.setStatus(SC_OK);
                LOG.info("user is  registered!");
            }
        }
    }

}
