package com.bushemi.model;

import com.bushemi.dao.entity.dictionaries.Locale;
import com.bushemi.dao.entity.dictionaries.Role;

import java.util.Objects;

public class UserForSessionDto {
    private Long id;
    private String login;
    private boolean isBlocked;
    private Role role;
    private Locale locale;

    public UserForSessionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserForSessionDto)) return false;
        UserForSessionDto that = (UserForSessionDto) o;
        return isBlocked() == that.isBlocked() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getRole(), that.getRole()) &&
                Objects.equals(getLocale(), that.getLocale());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), isBlocked(), getRole(), getLocale());
    }

    @Override
    public String toString() {
        return "UserForSessionDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", isBlocked=" + isBlocked +
                ", role=" + role +
                ", locale=" + locale +
                '}';
    }
}
