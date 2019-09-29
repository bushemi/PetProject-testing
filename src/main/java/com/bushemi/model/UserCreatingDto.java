package com.bushemi.model;

import java.util.Objects;

public class UserCreatingDto {
    private String login;
    private String password;

    public UserCreatingDto() {
    }

    public UserCreatingDto(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreatingDto)) return false;
        UserCreatingDto that = (UserCreatingDto) o;
        return Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "UserCreatingDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
