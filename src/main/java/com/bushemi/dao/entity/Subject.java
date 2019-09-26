package com.bushemi.dao.entity;

import java.util.Objects;

public class Subject {
    private Long id;
    private String subjectName;

    public Subject() {
    }

    public Subject(Long id, String subjectName) {
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
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getId(), subject.getId()) &&
                Objects.equals(getSubjectName(), subject.getSubjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectName());
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
