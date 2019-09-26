package com.bushemi.dao.entity;

import java.util.Objects;

public class Question {
    private Long id;
    private String mainText;
    private Long testId;

    public Question() {
    }

    public Question(Long id, String mainText, Long testId) {
        this.id = id;
        this.mainText = mainText;
        this.testId = testId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId()) &&
                Objects.equals(getMainText(), question.getMainText()) &&
                Objects.equals(getTestId(), question.getTestId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainText(), getTestId());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", mainText='" + mainText + '\'' +
                ", testId=" + testId +
                '}';
    }
}
