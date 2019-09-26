package com.bushemi.dao.entity;

import java.util.Objects;

public class Option {
    private Long id;
    private String mainText;
    private Long questionId;
    private boolean isCorrect;

    public Option() {
    }

    public Option(Long id, String mainText, Long questionId, boolean isCorrect) {
        this.id = id;
        this.mainText = mainText;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;
        Option option = (Option) o;
        return isCorrect() == option.isCorrect() &&
                Objects.equals(getId(), option.getId()) &&
                Objects.equals(getMainText(), option.getMainText()) &&
                Objects.equals(getQuestionId(), option.getQuestionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainText(), getQuestionId(), isCorrect());
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", mainText='" + mainText + '\'' +
                ", questionId=" + questionId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
