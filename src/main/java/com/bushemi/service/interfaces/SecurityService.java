package com.bushemi.service.interfaces;

import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;

public interface SecurityService {

    UserForSessionDto login(UserCreatingDto user);
}
