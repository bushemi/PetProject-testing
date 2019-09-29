package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.User;
import com.bushemi.dao.interfaces.UserDao;
import com.bushemi.exceptions.DbException;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger("UserDaoImpl");
    private DbConnectionService dbConnector;

    public UserDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
        this.dbConnector = dbConnector;
    }

    @Override
    public void setIsBlockedByUserId(Long userId, boolean isBlocked) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update users set is_blocked=? where id = ?;")) {
            preparedStatement.setBoolean(1, isBlocked);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't set blocked status({}) for a user with id = {}. {}", isBlocked, userId, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
    }

    @Override
    public void setLocaleByUserId(Long userId, Long localeId) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update users set locale_id=? where id = ?;")) {
            preparedStatement.setLong(1, localeId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't set blocked status({}) for a user with id = {}. {}", localeId, userId, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
    }

    @Override
    public boolean isUserExist(String login) {
        LOG.info("isUserExist for login = {}", login);
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from users where login = ?;")) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Can't find a user with same login {}. {}", login, e.getMessage());
        } finally {
            dbConnector.releaseConnection(connection);
        }
        return false;
    }

    @Override
    protected void putParametersForSavingNewEntityIntoPreparedStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setBoolean(3, user.isBlocked());
        statement.setLong(4, user.getRoleId());
        statement.setLong(5, user.getLocaleId());
    }

    @Override
    protected void putParametersForUpdatingEntityIntoPreparedStatement(User user, PreparedStatement statement) throws SQLException {
        putParametersForSavingNewEntityIntoPreparedStatement(user, statement);

        statement.setLong(7, user.getId());
    }

    @Override
    String getQueryForDeletingById() {
        return "DELETE FROM users WHERE id = ?;";
    }

    @Override
    String getQueryForSavingNewEntity() {
        return "INSERT INTO users (id, login, password, is_blocked, role_id, locale_id) VALUES (null, ?, ?, ?, ?, ?);";
    }

    @Override
    String getQueryForUpdatingEntity() {
        return "UPDATE users SET login=?, password=?, is_blocked=?, role_id=?, locale_id=? WHERE id = ?;";
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, login, is_blocked, role_id, locale_id, password FROM users WHERE id = ?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, login, is_blocked, role_id, locale_id, password FROM users;";
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setLogin(resultSet.getString(2));
        user.setBlocked(resultSet.getBoolean(3));
        user.setRoleId(resultSet.getLong(4));
        user.setLocaleId(resultSet.getLong(5));
        user.setPassword(resultSet.getString(6));
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Connection connection = dbConnector.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT id, login, is_blocked, role_id, locale_id, password FROM users WHERE login = ?;")) {

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (next) {
                return getEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            LOG.error("Can't find a entity by login = {}. {}", login, e.getMessage());
            throw new DbException(e);
        } finally {
            dbConnector.releaseConnection(connection);
        }

    }
}
