package com.bushemi.service.interfaces;

import java.sql.Connection;

public interface DbConnectionService {
    Connection getConnection();
    void releaseConnection(Connection connection);
}
