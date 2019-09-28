package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.Question;
import com.bushemi.dao.interfaces.QuestionDao;
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

public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {
    private static final Logger LOG = LoggerFactory.getLogger("QuestionDaoImpl");
    private DbConnectionService dbConnector;

    public QuestionDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
        this.dbConnector = dbConnector;
    }

    @Override
    public List<Question> findAllByTestId(Long testId) {
        List<Question> entities = new ArrayList<>();
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT id, main_text, test_id FROM questions WHERE test_id = ?;")) {

            preparedStatement.setLong(1, testId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOG.error("Can't find questions by test id = {}. {}", testId, e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }

        return entities;
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM questions WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO questions (id, main_text, test_id) VALUES (null, ?, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE questions SET main_text=?, test_id=? WHERE id = ?;";
    }

    @Override
    void putParametersForSavingNewEntityIntoPreparedStatement(Question entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getMainText());
        statement.setLong(2, entity.getTestId());
    }

    @Override
    void putParametersForUpdatingEntityIntoPreparedStatement(Question entity, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(entity, statement);
        statement.setLong(3, entity.getId());
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, main_text, test_id FROM questions WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, main_text, test_id FROM questions";
    }

    @Override
    Question getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Question entity = new Question();
        entity.setId(resultSet.getLong(1));
        entity.setMainText(resultSet.getString(2));
        entity.setTestId(resultSet.getLong(3));

        return entity;
    }
}
