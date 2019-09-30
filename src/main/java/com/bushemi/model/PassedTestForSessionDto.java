package com.bushemi.model;

import java.util.Objects;

public class PassedTestForSessionDto {
    private Long testId;
    private Integer correctAnswers;
    private Integer spentTime;

    public PassedTestForSessionDto() {
    }

    public PassedTestForSessionDto(Long testId, Integer correctAnswers, Integer spentTime) {
        this.testId = testId;
        this.correctAnswers = correctAnswers;
        this.spentTime = spentTime;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedTestForSessionDto)) return false;
        PassedTestForSessionDto that = (PassedTestForSessionDto) o;
        return Objects.equals(getTestId(), that.getTestId()) &&
                Objects.equals(getCorrectAnswers(), that.getCorrectAnswers()) &&
                Objects.equals(getSpentTime(), that.getSpentTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTestId(), getCorrectAnswers(), getSpentTime());
    }

    @Override
    public String toString() {
        return "PassedTestForSessionDto{" +
                "testId=" + testId +
                ", correctAnswers=" + correctAnswers +
                ", spentTime=" + spentTime +
                '}';
    }
}
