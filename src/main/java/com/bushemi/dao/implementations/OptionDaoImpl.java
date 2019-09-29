package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.Option;
import com.bushemi.dao.interfaces.OptionDao;
import com.bushemi.exceptions.DbException;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionDaoImpl extends AbstractDao<Option> implements OptionDao {
    private static final Logger LOG = LoggerFactory.getLogger("OptionDaoImpl");
    private DbConnectionService dbConnector;

    public OptionDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
        this.dbConnector = dbConnector;
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM options WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO options (id, main_text, question_id, is_correct) VALUES (null, ?, ?, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE options SET main_text=?, question_id=?, is_correct=? WHERE id = ?;";
    }

    @Override
    void putParametersForSavingNewEntityIntoPreparedStatement(Option entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getMainText());
        statement.setLong(2, entity.getQuestionId());
        statement.setBoolean(3, entity.isCorrect());
    }

    @Override
    void putParametersForUpdatingEntityIntoPreparedStatement(Option entity, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(entity, statement);
        statement.setLong(4, entity.getId());
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, main_text, question_id, is_correct FROM options WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, main_text, question_id, is_correct FROM options";
    }

    @Override
    Option getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Option entity = new Option();
        entity.setId(resultSet.getLong(1));
        entity.setMainText(resultSet.getString(2));
        entity.setQuestionId(resultSet.getLong(3));
        entity.setCorrect(resultSet.getBoolean(4));

        return entity;
    }

    @Override
    public List<Option> finAllByQuestionId(Long questionId) {
        List<Option> entities = new ArrayList<>();
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT id, main_text, question_id, is_correct FROM options WHERE question_id = ?;")) {

            preparedStatement.setLong(1, questionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Option entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOG.error("Can't find options by question id = {}. {}", questionId, e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }

        return entities;
    }
}
