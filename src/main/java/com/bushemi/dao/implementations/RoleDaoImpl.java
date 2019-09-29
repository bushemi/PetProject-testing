package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.dictionaries.Role;
import com.bushemi.dao.interfaces.RoleDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl extends AbstractDictionaryDao<Role> implements RoleDao {
    private static final Logger LOG = LoggerFactory.getLogger("RoleDaoImpl");

    public RoleDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, role_name FROM roles WHERE id=?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, role_name FROM roles;";
    }

    @Override
    Role getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(1));
        role.setRoleName(resultSet.getString(2));
        return role;
    }
}
