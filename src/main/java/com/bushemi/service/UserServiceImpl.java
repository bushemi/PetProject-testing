package com.bushemi.service;

import com.bushemi.converters.UserConverter;
import com.bushemi.dao.entity.User;
import com.bushemi.dao.implementations.UserDaoImpl;
import com.bushemi.dao.interfaces.UserDao;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.implementations.DbConnectionPoolServiceImpl;
import com.bushemi.service.interfaces.DbConnectionService;

public class UserServiceImpl implements UserService {

    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private UserDao userDao = new UserDaoImpl(dbConnectionService);
    private UserConverter userConverter = new UserConverter();

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserForSessionDto save(UserCreatingDto user) {
        User userToSave = userConverter.userCreatingToUser(user);

        long id = userDao.save(userToSave);

        return userConverter.userToUserForSessionDto(userDao.findById(id));
    }

    @Override
    public boolean isUserExist(String login) {
        return userDao.isUserExist(login);
    }
}
