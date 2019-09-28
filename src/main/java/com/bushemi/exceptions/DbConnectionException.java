package com.bushemi.exceptions;

public class DbConnectionException extends DbException {
    public DbConnectionException() {
    }

    public DbConnectionException(String message) {
        super(message);
    }

    public DbConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectionException(Throwable cause) {
        super(cause);
    }

}
