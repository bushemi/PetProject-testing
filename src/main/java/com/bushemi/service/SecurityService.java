package com.bushemi.service;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;

public interface SecurityService {

    UserForSessionDto login(UserCreatingDto user);
}
