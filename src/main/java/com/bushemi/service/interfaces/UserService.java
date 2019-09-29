package com.bushemi.service.interfaces;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;

public interface UserService {
    UserForSessionDto save(UserCreatingDto user);

    boolean isUserExist(String login);

}
