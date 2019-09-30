package com.bushemi.model;

import java.util.Objects;

public class TestForTestsPage {
    private Long id;
    private String subject;
    private String testName;
    private Integer difficulty;
    private Double minutesToComplete;
    private Integer correctAnswers;
    private Integer spentTime;

    public TestForTestsPage() {
    }

    public TestForTestsPage(Long id, String subject, String testName,
                            Integer difficulty, Double minutesToComplete,
                            Integer correctAnswers, Integer spentTime) {
        this.id = id;
        this.subject = subject;
        this.testName = testName;
        this.difficulty = difficulty;
        this.minutesToComplete = minutesToComplete;
        this.correctAnswers = correctAnswers;
        this.spentTime = spentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Double getMinutesToComplete() {
        return minutesToComplete;
    }

    public void setMinutesToComplete(Double minutesToComplete) {
        this.minutesToComplete = minutesToComplete;
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
        if (!(o instanceof TestForTestsPage)) return false;
        TestForTestsPage that = (TestForTestsPage) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSubject(), that.getSubject()) &&
                Objects.equals(getTestName(), that.getTestName()) &&
                Objects.equals(getDifficulty(), that.getDifficulty()) &&
                Objects.equals(getMinutesToComplete(), that.getMinutesToComplete()) &&
                Objects.equals(getCorrectAnswers(), that.getCorrectAnswers()) &&
                Objects.equals(getSpentTime(), that.getSpentTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubject(), getTestName(), getDifficulty(), getMinutesToComplete(), getCorrectAnswers(), getSpentTime());
    }

    @Override
    public String toString() {
        return "TestForTestsPage{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", testName='" + testName + '\'' +
                ", difficulty=" + difficulty +
                ", minutesToComplete=" + minutesToComplete +
                ", correctAnswers=" + correctAnswers +
                ", spentTime=" + spentTime +
                '}';
    }
}
