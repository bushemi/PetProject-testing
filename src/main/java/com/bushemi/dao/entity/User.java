package com.bushemi.dao.entity;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private boolean isBlocked;
    private Long roleId;
    private Long localeId;

    public User() {
    }

    public User(Long id, String login, String password, boolean isBlocked, Long roleId, Long localeId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isBlocked = isBlocked;
        this.roleId = roleId;
        this.localeId = localeId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getLocaleId() {
        return localeId;
    }

    public void setLocaleId(Long localeId) {
        this.localeId = localeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isBlocked() == user.isBlocked() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRoleId(), user.getRoleId()) &&
                Objects.equals(getLocaleId(), user.getLocaleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), isBlocked(), getRoleId(), getLocaleId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isBlocked=" + isBlocked +
                ", roleId=" + roleId +
                ", localeId=" + localeId +
                '}';
    }
}
