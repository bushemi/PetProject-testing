package com.bushemi.model;

import java.util.Objects;

public class TestForSessionDto {
    private Long id;
    private Long subjectId;
    private String testName;
    private Integer difficulty;
    private Integer secondsToComplete;

    public TestForSessionDto() {
    }

    public TestForSessionDto(Long id, Long subjectId, String testName, Integer difficulty, Integer secondsToComplete) {
        this.id = id;
        this.subjectId = subjectId;
        this.testName = testName;
        this.difficulty = difficulty;
        this.secondsToComplete = secondsToComplete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSecondsToComplete() {
        return secondsToComplete;
    }

    public void setSecondsToComplete(Integer secondsToComplete) {
        this.secondsToComplete = secondsToComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestForSessionDto)) return false;
        TestForSessionDto that = (TestForSessionDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSubjectId(), that.getSubjectId()) &&
                Objects.equals(getTestName(), that.getTestName()) &&
                Objects.equals(getDifficulty(), that.getDifficulty()) &&
                Objects.equals(getSecondsToComplete(), that.getSecondsToComplete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectId(), getTestName(), getDifficulty(), getSecondsToComplete());
    }

    @Override
    public String toString() {
        return "TestForSessionDto{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", secondsToComplete=" + secondsToComplete +
                '}';
    }
}
