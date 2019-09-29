package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.dictionaries.Locale;
import com.bushemi.dao.interfaces.LocaleDao;
import com.bushemi.service.DbConnectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocaleCachedDaoImpl implements LocaleDao {
    private Map<Long, Locale> localesById = new HashMap<>();
    private List<Locale> allLocales;

    public LocaleCachedDaoImpl(DbConnectionService dbConnector) {
        LocaleDao localeDao = new LocaleDaoImpl(dbConnector);
        allLocales = localeDao.findAll();
        allLocales.forEach(locale -> localesById.put(locale.getId(), locale));
    }

    @Override
    public Locale findById(Long id) {
        return localesById.get(id);
    }

    @Override
    public List<Locale> findAll() {
        return allLocales;
    }
}
