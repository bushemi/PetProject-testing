package com.bushemi.converters;

import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.entity.Test;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.model.TestForTestsPage;

import java.util.Map;

public class TestConverter {

    private static final double SECONDS_IN_MINUTE = 60d;

    public TestForTestsPage fromTestToTestForTestsPage(Test test, Map<Long, Subject> subjects) {
        TestForTestsPage testForTestsPage = new TestForTestsPage();

        testForTestsPage.setId(test.getId());
        String subjectName = subjects.get(test.getSubjectId()).getSubjectName();
        testForTestsPage.setSubject(subjectName);
        testForTestsPage.setTestName(test.getTestName());
        testForTestsPage.setDifficulty(test.getDifficulty());
        testForTestsPage.setMinutesToComplete(test.getSecondsToComplete() / SECONDS_IN_MINUTE);

        return testForTestsPage;
    }

    public TestForSessionDto fromTestToTestForSessionDto(Test test) {
        TestForSessionDto testForSessionDto = new TestForSessionDto();

        testForSessionDto.setId(test.getId());
        testForSessionDto.setSubjectId(test.getSubjectId());
        testForSessionDto.setTestName(test.getTestName());
        testForSessionDto.setDifficulty(test.getDifficulty());
        testForSessionDto.setSecondsToComplete(test.getSecondsToComplete());

        return testForSessionDto;
    }
}