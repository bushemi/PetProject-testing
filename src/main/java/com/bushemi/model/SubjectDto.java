package com.bushemi.model;

import java.util.Objects;

public class SubjectDto {
    private Long id;
    private String subjectName;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectDto)) return false;
        SubjectDto that = (SubjectDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSubjectName(), that.getSubjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectName());
    }

    @Override
    public String toString() {
        return "SubjectDto{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
