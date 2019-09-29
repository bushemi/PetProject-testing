package com.bushemi.service;

import com.bushemi.converters.UserConverter;
import com.bushemi.dao.entity.User;
import com.bushemi.dao.implementations.UserDaoImpl;
import com.bushemi.dao.interfaces.UserDao;
import com.bushemi.exceptions.WrongPasswordException;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.nonNull;

public class SecurityServiceImpl implements SecurityService {
    private static final Logger LOG = LoggerFactory.getLogger("SecurityServiceImpl");
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private UserDao userDao = new UserDaoImpl(dbConnectionService);
    private UserConverter userConverter = new UserConverter();

    @Override
    public UserForSessionDto login(UserCreatingDto user) {
        LOG.info("Try to login with user = {}", user);
        User byLogin = userDao.findByLogin(user.getLogin());
        if (nonNull(byLogin) && byLogin.getPassword().equals(user.getPassword())) {
            LOG.info("users successfully logged in");
            return userConverter.userToUserForSessionDto(byLogin);
        }
        LOG.error("Wrong login or password.");
        throw new WrongPasswordException();
    }
}
