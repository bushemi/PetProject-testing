package com.bushemi.service.implementations;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.dao.implementations.PassedTestDaoImpl;
import com.bushemi.dao.interfaces.PassedTestDao;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.PassedTestsService;

import static java.util.Objects.isNull;

public class PassedTestsServiceImpl implements PassedTestsService {
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private PassedTestDao passedTestDao = new PassedTestDaoImpl(dbConnectionService);

    @Override
    public void save(PassedTest passedTest) {
        PassedTest byUserIdAndTestId =
                passedTestDao.findByUserIdAndTestId(passedTest.getUserId(), passedTest.getTestId());
        if (isNull(byUserIdAndTestId)) {
            passedTestDao.save(passedTest);
        } else {
            passedTest.setId(byUserIdAndTestId.getId());
            passedTestDao.update(passedTest);
        }
    }
}
