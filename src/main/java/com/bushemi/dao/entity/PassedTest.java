package com.bushemi.dao.entity;

import java.util.Objects;

public class PassedTest {
    private Long id;
    private Long testId;
    private Long userId;
    private Integer correctAnswers;
    private Integer spentTime;

    public PassedTest() {
    }

    public PassedTest(Long id, Long testId, Long userId, Integer correctAnswers, Integer spentTime) {
        this.id = id;
        this.testId = testId;
        this.userId = userId;
        this.correctAnswers = correctAnswers;
        this.spentTime = spentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        if (!(o instanceof PassedTest)) return false;
        PassedTest that = (PassedTest) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTestId(), that.getTestId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getCorrectAnswers(), that.getCorrectAnswers()) &&
                Objects.equals(getSpentTime(), that.getSpentTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTestId(), getUserId(), getCorrectAnswers(), getSpentTime());
    }

    @Override
    public String toString() {
        return "PassedTest{" +
                "id=" + id +
                ", testId=" + testId +
                ", userId=" + userId +
                ", correctAnswers=" + correctAnswers +
                ", spentTime=" + spentTime +
                '}';
    }
}
