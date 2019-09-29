package com.bushemi.model;

import java.util.List;
import java.util.Objects;

public class QuestionForSessionDto {
    private Long id;
    private String mainText;
    private List<OptionForSessionDto> options;

    public QuestionForSessionDto() {
    }

    public QuestionForSessionDto(Long id, String mainText, List<OptionForSessionDto> options) {
        this.id = id;
        this.mainText = mainText;
        this.options = options;
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

    public List<OptionForSessionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionForSessionDto> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionForSessionDto)) return false;
        QuestionForSessionDto that = (QuestionForSessionDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getMainText(), that.getMainText()) &&
                Objects.equals(getOptions(), that.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainText(), getOptions());
    }

    @Override
    public String toString() {
        return "QuestionForSessionDto{" +
                "id=" + id +
                ", mainText='" + mainText + '\'' +
                ", options=" + options +
                '}';
    }
}
