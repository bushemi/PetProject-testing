package com.bushemi.converters;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.model.PassedTestForSessionDto;

public class PassedTestConverter {

    public PassedTestForSessionDto passedTestToPassedTestForSessionDto(PassedTest passedTest) {
        PassedTestForSessionDto passedTestForSessionDto = new PassedTestForSessionDto();

        passedTestForSessionDto.setTestId(passedTest.getTestId());
        passedTestForSessionDto.setCorrectAnswers(passedTest.getCorrectAnswers());
        passedTestForSessionDto.setSpentTime(passedTest.getSpentTime());

        return passedTestForSessionDto;
    }
}
