package com.bushemi.service.interfaces;

import com.bushemi.dao.entity.Test;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.model.TestForTestsPage;

import java.util.List;

public interface TestService {

    List<TestForTestsPage> findAllTests();

    TestForSessionDto findTestById(Long id);

    Long save(Test test);
}
