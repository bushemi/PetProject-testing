package com.bushemi.service;

import java.sql.Connection;

public interface DbConnectionService {
    Connection getConnection();
    void releaseConnection(Connection connection);
}
