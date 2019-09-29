package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.Option;
import com.bushemi.dao.interfaces.OptionDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionDaoImpl extends AbstractDao<Option> implements OptionDao {
    private static final Logger LOG = LoggerFactory.getLogger("OptionDaoImpl");

    public OptionDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
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
}
