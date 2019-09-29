package com.bushemi.model;

import java.util.Objects;

public class OptionForSessionDto {
    private Long id;
    private String mainText;
    private boolean isCorrect;

    public OptionForSessionDto() {
    }

    public OptionForSessionDto(Long id, String mainText, boolean isCorrect) {
        this.id = id;
        this.mainText = mainText;
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionForSessionDto)) return false;
        OptionForSessionDto that = (OptionForSessionDto) o;
        return isCorrect() == that.isCorrect() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getMainText(), that.getMainText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainText(), isCorrect());
    }

    @Override
    public String toString() {
        return "OptionForSessionDto{" +
                "id=" + id +
                ", mainText='" + mainText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
