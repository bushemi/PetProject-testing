package com.bushemi.service.implementations;

import com.bushemi.converters.PassedTestConverter;
import com.bushemi.dao.entity.PassedTest;
import com.bushemi.dao.implementations.PassedTestDaoImpl;
import com.bushemi.dao.interfaces.PassedTestDao;
import com.bushemi.model.PassedTestForSessionDto;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.PassedTestsService;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class PassedTestsServiceImpl implements PassedTestsService {
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private PassedTestDao passedTestDao = new PassedTestDaoImpl(dbConnectionService);
    private PassedTestConverter passedTestConverter = new PassedTestConverter();

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

    @Override
    public Map<Long, PassedTestForSessionDto> findAllByUserId(Long userId) {
        Map<Long, PassedTestForSessionDto> passedTests = new HashMap<>();
        passedTestDao.findAllByUserId(userId)
                .stream()
                .map(passedTestConverter::passedTestToPassedTestForSessionDto)
                .forEach(passedTest -> passedTests.put(passedTest.getTestId(), passedTest));

        return passedTests;
    }
}
