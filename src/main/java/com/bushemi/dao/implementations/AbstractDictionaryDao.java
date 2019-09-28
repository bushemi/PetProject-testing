package com.bushemi.dao.implementations;

import com.bushemi.dao.interfaces.DictionariesInterface;
import com.bushemi.exceptions.DbException;
import com.bushemi.service.DbConnectionService;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDictionaryDao<T> implements DictionariesInterface<T> {
    private Logger logger;
    private DbConnectionService dbConnector;

    AbstractDictionaryDao(DbConnectionService dbConnector, Logger logger) {
        this.dbConnector = dbConnector;
        this.logger = logger;
    }

    @Override
    public T findById(Long id) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getQueryForFindingEntityById())) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                return getEntityFromResultSet(resultSet);
            }
            throw new DbException("There is no entity with given id");
        } catch (SQLException e) {
            logger.error("Can't find a entity by id = {}. {}", id, e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getQueryForFindingAllEntities())) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.error("Can't find all entities. {}", e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }

        return entities;
    }

    abstract String getQueryForFindingEntityById();

    abstract String getQueryForFindingAllEntities();

    abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
