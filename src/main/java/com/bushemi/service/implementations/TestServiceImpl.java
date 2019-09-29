package com.bushemi.service.implementations;

import com.bushemi.converters.TestConverter;
import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.entity.Test;
import com.bushemi.dao.implementations.SubjectDaoImpl;
import com.bushemi.dao.implementations.TestDaoImpl;
import com.bushemi.dao.interfaces.SubjectDao;
import com.bushemi.dao.interfaces.TestDao;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.model.TestForTestsPage;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestServiceImpl implements TestService {
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private final TestDao testDao = new TestDaoImpl(dbConnectionService);
    private final SubjectDao subjectDao = new SubjectDaoImpl(dbConnectionService);
    private final TestConverter testConverter = new TestConverter();

    public TestServiceImpl(DbConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    public TestServiceImpl() {
    }

    @Override
    public List<TestForTestsPage> findAllTests() {
        Map<Long, Subject> subjects = getSubjectMap();
        return testDao.findAll().stream()
                .map(test -> testConverter.fromTestToTestForTestsPage(test, subjects))
                .collect(Collectors.toList());
    }

    private Map<Long, Subject> getSubjectMap() {
        Map<Long, Subject> subjects = new HashMap<>();
        subjectDao.findAll().forEach(subject -> subjects.put(subject.getId(), subject));
        return subjects;
    }

    @Override
    public TestForSessionDto findTestById(Long id) {

        return null;
    }
}
