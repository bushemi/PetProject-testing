package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.Test;
import com.bushemi.dao.interfaces.TestDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDaoImpl extends AbstractDao<Test> implements TestDao {
    private static final Logger LOG = LoggerFactory.getLogger("TestDaoImpl");

    public TestDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM tests WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO tests (id, subject, test_name, difficulty, seconds_to_complete) VALUES (null, ?, ?, ?, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE tests SET subject=?, test_name=?, difficulty=?, seconds_to_complete=? WHERE id = ?;";
    }

    @Override
    void putParametersForSavingNewEntityIntoPreparedStatement(Test test, PreparedStatement statement) throws SQLException {
        statement.setLong(1, test.getSubjectId());
        statement.setString(2, test.getTestName());
        statement.setInt(3, test.getDifficulty());
        statement.setInt(4, test.getSecondsToComplete());
    }

    @Override
    void putParametersForUpdatingEntityIntoPreparedStatement(Test test, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(test, statement);
        statement.setLong(5, test.getId());
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, subject, test_name, difficulty, seconds_to_complete FROM tests WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, subject, test_name, difficulty, seconds_to_complete FROM tests";
    }

    @Override
    Test getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong(1));
        test.setSubjectId(resultSet.getLong(2));
        test.setTestName(resultSet.getString(3));
        test.setDifficulty(resultSet.getInt(4));
        test.setSecondsToComplete(resultSet.getInt(5));

        return test;
    }
}
