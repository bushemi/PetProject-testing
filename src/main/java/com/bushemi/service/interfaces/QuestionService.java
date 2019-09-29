package com.bushemi.service.interfaces;

import com.bushemi.model.QuestionForSessionDto;

import java.util.List;

public interface QuestionService {

    List<QuestionForSessionDto> findQuestionsByTestId(Long testId);
}
