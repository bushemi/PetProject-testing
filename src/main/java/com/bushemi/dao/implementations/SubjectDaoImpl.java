package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.interfaces.SubjectDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDaoImpl extends AbstractDao<Subject> implements SubjectDao {
    private static final Logger LOG = LoggerFactory.getLogger("SubjectDaoImpl");

    public SubjectDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM subjects WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO subjects (id, subject_name) VALUES (null, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE subjects SET subject_name=? WHERE id = ?;";
    }

    @Override
    void putParametersForSavingNewEntityIntoPreparedStatement(Subject subject, PreparedStatement statement) throws SQLException {
        statement.setString(1, subject.getSubjectName());
    }

    @Override
    void putParametersForUpdatingEntityIntoPreparedStatement(Subject subject, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(subject, statement);
        statement.setLong(1, subject.getId());
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, subject_name FROM users WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, subject_name FROM users";
    }

    @Override
    Subject getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong(1));
        subject.setSubjectName(resultSet.getString(2));

        return subject;
    }
}
