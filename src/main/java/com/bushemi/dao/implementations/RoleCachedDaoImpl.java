package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.dictionaries.Role;
import com.bushemi.dao.interfaces.RoleDao;
import com.bushemi.service.interfaces.DbConnectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCachedDaoImpl implements RoleDao {
    private Map<Long, Role> rolesById = new HashMap<>();
    private List<Role> allRoles;

    public RoleCachedDaoImpl(DbConnectionService dbConnector) {
        RoleDao roleDao = new RoleDaoImpl(dbConnector);
        allRoles = roleDao.findAll();
        allRoles.forEach(role -> rolesById.put(role.getId(), role));
    }

    @Override
    public Role findById(Long id) {
        return rolesById.get(id);
    }

    @Override
    public List<Role> findAll() {
        return allRoles;
    }
}
