package com.bushemi.dao.interfaces;

import com.bushemi.dao.entity.Question;

import java.util.List;

public interface QuestionDao extends CrudOperationsInterface<Question> {

    List<Question> findAllByTestId(Long testId);
}
