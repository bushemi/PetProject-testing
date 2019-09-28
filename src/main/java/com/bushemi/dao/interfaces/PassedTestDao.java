package com.bushemi.dao.interfaces;

import com.bushemi.dao.entity.PassedTest;

import java.util.List;

public interface PassedTestDao extends CrudOperationsInterface<PassedTest> {

    List<PassedTest> findAllByUserId(Long userId);

}
