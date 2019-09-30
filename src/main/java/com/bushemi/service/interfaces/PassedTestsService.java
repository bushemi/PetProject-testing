package com.bushemi.service.interfaces;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.model.PassedTestForSessionDto;

import java.util.Map;

public interface PassedTestsService {
    void save(PassedTest passedTest);

    Map<Long, PassedTestForSessionDto> findAllByUserId(Long userId);
}
