package com.bushemi.dao.interfaces;

import com.bushemi.dao.entity.Option;

import java.util.List;

public interface OptionDao extends CrudOperationsInterface<Option> {
    List<Option> finAllByQuestionId(Long questionId);
}
