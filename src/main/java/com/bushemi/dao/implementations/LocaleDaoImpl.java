package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.dictionaries.Locale;
import com.bushemi.dao.interfaces.LocaleDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocaleDaoImpl extends AbstractDictionaryDao<Locale> implements LocaleDao {
    private static final Logger LOG = LoggerFactory.getLogger("LocaleDaoImpl");

    public LocaleDaoImpl(DbConnectionService dbConnector) {
        super(dbConnector, LOG);
    }

    @Override
    String getQueryForFindingEntityById() {
        return "SELECT id, short_name, full_name FROM locales WHERE id=?;";
    }

    @Override
    String getQueryForFindingAllEntities() {
        return "SELECT id, short_name, full_name FROM locales;";
    }

    @Override
    Locale getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Locale locale = new Locale();
        locale.setId(resultSet.getLong(1));
        locale.setShortName(resultSet.getString(2));
        locale.setFullName(resultSet.getString(3));
        return locale;
    }

}
