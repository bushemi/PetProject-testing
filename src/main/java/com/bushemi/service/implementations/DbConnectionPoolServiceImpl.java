package com.bushemi.service.implementations;

import com.bushemi.exceptions.DbConnectionException;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

public class DbConnectionPoolServiceImpl implements DbConnectionService {
    private static final Logger LOG = LoggerFactory.getLogger("DbConnectionService");
    private static final int DEFAULT_CONNECTIONS_QUANTITY = 10;
    private static final int TIMEOUT_TO_OBTAIN_CONNECTION = 10;
    private String url;
    private String username;
    private String password;
    private LinkedBlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Connection> busyConnections = new LinkedBlockingQueue<>();
    private static DbConnectionPoolServiceImpl instance = new DbConnectionPoolServiceImpl();

    private DbConnectionPoolServiceImpl() {
        initParametersForConnection();
        initializeConnectionPool();
    }

    public static DbConnectionPoolServiceImpl getInstance() {
        return instance;
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

    private Connection obtainConnection() {
        Connection connection;
        try {
            connection = freeConnections.poll(TIMEOUT_TO_OBTAIN_CONNECTION, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOG.error("{}", e);
            throw new DbConnectionException(e);
        }
        if (isNull(connection)) {
            LOG.error("{} seconds is not enough to obtain connection", TIMEOUT_TO_OBTAIN_CONNECTION);
            throw new DbConnectionException();
        }
        busyConnections.add(connection);
        LOG.info("Obtained connection by thread = {}", Thread.currentThread().getName());
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        if (busyConnections.remove(connection)) {
            freeConnections.add(connection);
        }
        LOG.info("released connection by thread = {}", Thread.currentThread().getName());
    }

}
