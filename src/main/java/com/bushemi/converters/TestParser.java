package com.bushemi.converters;

import com.bushemi.dao.entity.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TestParser {
    public Test fromString(String text) {
        Test testEntity = new Test();

        String[] strings = text.split("&");
        for (String pair : strings) {
            String[] split = pair.split("=");
            if (split.length > 1) {
                if (pair.contains("subjectId")) {
                    Long subjectId;
                    try {
                        subjectId = Long.parseLong(split[1]);
                    } catch (NumberFormatException e) {
                        subjectId = null;
                    }
                    testEntity.setSubjectId(subjectId);
                    continue;
                }
                if (pair.contains("difficulty")) {
                    Integer difficulty;
                    try {
                        difficulty = Integer.parseInt(split[1]);
                    } catch (NumberFormatException e) {
                        difficulty = null;
                    }
                    testEntity.setDifficulty(difficulty);
                    continue;
                }
                if (pair.contains("secondsToComplete")) {
                    Integer secondsToComplete;
                    try {
                        secondsToComplete = Integer.parseInt(split[1]);
                    } catch (NumberFormatException e) {
                        secondsToComplete = null;
                    }
                    testEntity.setSecondsToComplete(secondsToComplete);
                    continue;
                }
                if (pair.contains("testName")) {
                    String testName ;
                    try {
                        testName = URLDecoder.decode(split[1], "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        testName = null;
                    }
                    testEntity.setTestName(testName);
                    continue;
                }
            }

        }
        return testEntity;
    }
}
