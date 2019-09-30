package com.bushemi.service.interfaces;

import com.bushemi.model.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> findAll();

    SubjectDto findById(Long id);
}
