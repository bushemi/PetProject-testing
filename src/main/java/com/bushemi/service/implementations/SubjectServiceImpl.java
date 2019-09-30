package com.bushemi.service.implementations;

import com.bushemi.converters.SubjectConverter;
import com.bushemi.dao.entity.Subject;
import com.bushemi.dao.implementations.SubjectDaoImpl;
import com.bushemi.dao.interfaces.SubjectDao;
import com.bushemi.model.SubjectDto;
import com.bushemi.service.interfaces.DbConnectionService;
import com.bushemi.service.interfaces.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectServiceImpl implements SubjectService {
    private DbConnectionService dbConnectionService = DbConnectionPoolServiceImpl.getInstance();
    private SubjectDao subjectDao = new SubjectDaoImpl(dbConnectionService);
    private SubjectConverter subjectConverter = new SubjectConverter();

    public SubjectServiceImpl() {
    }

    public SubjectServiceImpl(SubjectDao subjectDao, SubjectConverter subjectConverter) {
        this.subjectDao = subjectDao;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public List<SubjectDto> findAll() {
        return subjectDao.findAll()
                .stream()
                .map(subjectConverter::subjectToSubjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto findById(Long id) {
        Subject subject = subjectDao.findById(id);
        return subjectConverter.subjectToSubjectDto(subject);
    }
}
