package com.bushemi.converters;

import com.bushemi.dao.entity.Option;
import com.bushemi.model.OptionForSessionDto;

public class OptionsConverter {

    public OptionForSessionDto optionToOptionForSessionDto(Option option) {
        OptionForSessionDto optionForSessionDto = new OptionForSessionDto();

        optionForSessionDto.setId(option.getId());
        optionForSessionDto.setCorrect(option.isCorrect());
        optionForSessionDto.setMainText(option.getMainText());

        return optionForSessionDto;
    }
}
