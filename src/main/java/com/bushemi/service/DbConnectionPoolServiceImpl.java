package com.bushemi.service;

import com.bushemi.exceptions.DbConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class DbConnectionPoolServiceImpl implements DbConnectionService {
    private static final Logger LOG = LoggerFactory.getLogger("DbConnectionService");
    private static final int DEFAULT_CONNECTIONS_QUANTITY = 10;
    private String url;
    private String username;
    private String password;
    private LinkedList<Connection> freeConnections = new LinkedList<>();
    private LinkedList<Connection> busyConnections = new LinkedList<>();

    public DbConnectionPoolServiceImpl() {
        initParametersForConnection();
        initializeConnectionPool();
    }

    private void initParametersForConnection() {
        PropertiesLoaderService propertiesLoaderService = new PropertiesLoaderService();
        Properties props = propertiesLoaderService.getProps();
        String dbDriver = props.getProperty("db.driver");
        if (dbDriver != null) {
            System.setProperty("db.driver", dbDriver);
        }
        url = props.getProperty("db.url");
        username = props.getProperty("db.user");
        password = props.getProperty("db.password");
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            LOG.error("error with loading class {}. {}", dbDriver, e.getMessage());
            LOG.error(e.toString());
        }
    }

    private void initializeConnectionPool() {
        for (int i = 0; i < DEFAULT_CONNECTIONS_QUANTITY; i++) {
            freeConnections.add(createNewConnection());
        }
    }

    private Connection createNewConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DbConnectionException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return obtainConnection();
    }

    private synchronized Connection obtainConnection() {
        while (freeConnections.isEmpty()) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                LOG.error("current thread was interrupted. {}", e.getCause());
                throw new DbConnectionException(e);
            }
        }
        Connection connection = freeConnections.pop();
        busyConnections.addFirst(connection);
        LOG.info("Obtained connection by thread = {}", Thread.currentThread().getName());
        return connection;
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        if (busyConnections.remove(connection)) {
            freeConnections.addFirst(connection);
        }
        LOG.info("released connection by thread = {}", Thread.currentThread().getName());
    }

}
