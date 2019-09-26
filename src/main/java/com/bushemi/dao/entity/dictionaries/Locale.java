package com.bushemi.dao.entity.dictionaries;

import java.util.Objects;

public class Locale {
    private Long id;
    private String fullName;
    private String shortName;

    public Locale() {
    }

    public Locale(Long id, String fullName, String shortName) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locale)) return false;
        Locale locale = (Locale) o;
        return Objects.equals(getId(), locale.getId()) &&
                Objects.equals(getFullName(), locale.getFullName()) &&
                Objects.equals(getShortName(), locale.getShortName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getShortName());
    }

    @Override
    public String toString() {
        return "Locale{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
