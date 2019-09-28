package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.dao.interfaces.PassedTestDao;
import com.bushemi.exceptions.DbException;
import com.bushemi.service.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassedTestDaoImpl extends AbstractDao<PassedTest> implements PassedTestDao {
    private static final Logger LOG = LoggerFactory.getLogger("PassedTestDaoImpl");
    private DbConnectionService dbConnector;

    public PassedTestDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
        this.dbConnector = dbConnector;
    }

    @Override
    public List<PassedTest> findAllByUserId(Long userId) {
        List<PassedTest> entities = new ArrayList<>();
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT id, test_id, user_id, correct_answers, spent_time FROM passed_tests WHERE user_id = ?;")) {

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PassedTest entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOG.error("Can't find questions by test id = {}. {}", userId, e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }

        return entities;
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM passed_tests WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO passed_tests (id, test_id, user_id, correct_answers, spent_time) VALUES (null, ?, ?, ?, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE passed_tests SET test_id=?, user_id=?, correct_answers=?, spent_time=? WHERE id = ?;";
    }

    @Override
    void putParametersForSavingNewEntityIntoPreparedStatement(PassedTest entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getTestId());
        statement.setLong(2, entity.getUserId());
        statement.setInt(3, entity.getCorrectAnswers());
        statement.setInt(4, entity.getSpentTime());
    }

    @Override
    void putParametersForUpdatingEntityIntoPreparedStatement(PassedTest entity, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(entity, statement);

        statement.setLong(5, entity.getId());
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, test_id, user_id, correct_answers, spent_time FROM passed_tests WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, test_id, user_id, correct_answers, spent_time FROM passed_tests";
    }

    @Override
    PassedTest getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PassedTest entity = new PassedTest();
        entity.setId(resultSet.getLong(1));
        entity.setTestId(resultSet.getLong(2));
        entity.setUserId(resultSet.getLong(3));
        entity.setCorrectAnswers(resultSet.getInt(4));
        entity.setSpentTime(resultSet.getInt(5));

        return entity;
    }
}
