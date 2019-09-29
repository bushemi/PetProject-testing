package com.bushemi.dao.implementations;

import com.bushemi.dao.interfaces.CrudOperationsInterface;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> extends AbstractDictionaryDao<T> implements CrudOperationsInterface<T> {
    private Logger logger;
    private DbConnectionService dbConnector;

    AbstractDao(DbConnectionService dbConnector, Logger logger) {
        super(dbConnector, logger);
        this.dbConnector = dbConnector;
        this.logger = logger;
    }

    @Override
    public void delete(Long id) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getQueryForDeletingById())) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete an entity with id = {}. {}", id, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
    }

    @Override
    public long save(T entity) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getQueryForSavingNewEntity()
                             , PreparedStatement.RETURN_GENERATED_KEYS)) {

            putParametersForSavingNewEntityIntoPreparedStatement(entity, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            boolean next = resultSet.next();
            if (next) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("Can't save a new entity = {}. {}", entity, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
        return 0L;
    }

    @Override
    public void update(T entity) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getQueryForUpdatingEntity())) {

            putParametersForUpdatingEntityIntoPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update an entity = {}. {}", entity, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
    }

    abstract String getQueryForDeletingById();

    abstract String getQueryForSavingNewEntity();

    abstract String getQueryForUpdatingEntity();

    abstract void putParametersForSavingNewEntityIntoPreparedStatement(T entity, PreparedStatement statement) throws SQLException;

    abstract void putParametersForUpdatingEntityIntoPreparedStatement(T entity, PreparedStatement statement) throws SQLException;
}
