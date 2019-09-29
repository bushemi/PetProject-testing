package com.bushemi.service.implementations;

import com.bushemi.model.UserCreatingDto;

import static java.util.Objects.isNull;

public class UserParserService {

    private static final String DELIMITER_FOR_PARAMETERS = "&";
    private static final String DELIMITER_FOR_PAIRS = "=";

    public UserCreatingDto fromString(String text) {
        UserCreatingDto user = new UserCreatingDto();
        String[] pairs = text.split(DELIMITER_FOR_PARAMETERS);
        for (String pair : pairs) {
            recognizeParameterAndIncludeItToUser(user, pair);
        }
        return user;
    }

    private void recognizeParameterAndIncludeItToUser(UserCreatingDto user, String pair) {
        String[] strings = pair.split(DELIMITER_FOR_PAIRS);
        String value = strings.length > 1 ? strings[1].trim() : null;
        if (pair.contains("login")) {
            user.setLogin(valueIfPresentOrNull(value));
            return;
        }
        if (pair.contains("password")) {
            user.setPassword(valueIfPresentOrNull(value));
            return;
        }

    }

    private String valueIfPresentOrNull(String value) {
        return isValueNull(value) ? null : value;
    }


    private boolean isValueNull(String value) {
        return isNull(value) || value.equalsIgnoreCase("null") || value.isEmpty();
    }
}
