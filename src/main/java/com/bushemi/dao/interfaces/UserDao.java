package com.bushemi.dao.interfaces;

import com.bushemi.dao.entity.User;

public interface UserDao extends CrudOperationsInterface<User>{
    void setIsBlockedByUserId(Long userId, boolean isBlocked);

    void setLocaleByUserId(Long userId, Long localeId);

    boolean isUserExist(String login);
}
