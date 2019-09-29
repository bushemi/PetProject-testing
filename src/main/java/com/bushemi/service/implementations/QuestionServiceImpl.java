package com.bushemi.service.implementations;

import com.bushemi.converters.OptionsConverter;
import com.bushemi.converters.QuestionConverter;
import com.bushemi.dao.implementations.OptionDaoImpl;
import com.bushemi.dao.implementations.QuestionDaoImpl;
import com.bushemi.dao.interfaces.OptionDao;
import com.bushemi.dao.interfaces.QuestionDao;
import com.bushemi.model.OptionForSessionDto;
import com.bushemi.model.QuestionForSessionDto;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.QuestionService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class QuestionServiceImpl implements QuestionService {
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private QuestionDao questionDao = new QuestionDaoImpl(dbConnectionService);
    private OptionDao optionDao = new OptionDaoImpl(dbConnectionService);
    private QuestionConverter questionConverter = new QuestionConverter();
    private OptionsConverter optionsConverter = new OptionsConverter();

    @Override
    public List<QuestionForSessionDto> findQuestionsByTestId(Long testId) {
        return questionDao.findAllByTestId(testId)
                .stream()
                .map(questionConverter::questionToQuestionForSessionDto)
                .peek(question -> question.setOptions(getOptions(question)))
                .collect(toList());
    }

    private List<OptionForSessionDto> getOptions(QuestionForSessionDto question) {
        return optionDao.finAllByQuestionId(question.getId()).stream().map(optionsConverter::optionToOptionForSessionDto).collect(toList());
    }
}
