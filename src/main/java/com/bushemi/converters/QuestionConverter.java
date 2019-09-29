package com.bushemi.converters;

import com.bushemi.dao.entity.Question;
import com.bushemi.model.QuestionForSessionDto;

public class QuestionConverter {
    public QuestionForSessionDto questionToQuestionForSessionDto(Question question) {
        QuestionForSessionDto questionForSessionDto = new QuestionForSessionDto();

        questionForSessionDto.setId(question.getId());
        questionForSessionDto.setMainText(question.getMainText());

        return questionForSessionDto;
    }
}
