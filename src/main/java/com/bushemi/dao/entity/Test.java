package com.bushemi.dao.entity;

import java.util.Objects;

public class Test {
    private Long id;
    private Long subjectId;
    private String testName;
    private Integer difficulty;
    private Integer secondsToComplete;

    public Test() {
    }

    public Test(Long id, Long subjectId, String testName, Integer difficulty, Integer secondsToComplete) {
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
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return Objects.equals(getId(), test.getId()) &&
                Objects.equals(getSubjectId(), test.getSubjectId()) &&
                Objects.equals(getTestName(), test.getTestName()) &&
                Objects.equals(getDifficulty(), test.getDifficulty()) &&
                Objects.equals(getSecondsToComplete(), test.getSecondsToComplete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectId(), getTestName(), getDifficulty(), getSecondsToComplete());
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", secondsToComplete=" + secondsToComplete +
                '}';
    }
}
