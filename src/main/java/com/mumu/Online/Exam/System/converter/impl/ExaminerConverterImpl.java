package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.model.dto.ExaminerDTO;
import com.mumu.Online.Exam.System.model.entity.Examiner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExaminerConverterImpl implements ExaminerConverter {

    private final ExamConverter examConverter;

    public ExaminerConverterImpl(final ExamConverter examConverter) {
        this.examConverter = examConverter;
    }


    @Override
    public ExaminerDTO toDto(Examiner from) {
        ExaminerDTO to = new ExaminerDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
        to.setBlocked(from.isBlocked());
        to.setLastLoginDate(from.getLastLoginDate());
        to.setExams(from.getExams().stream().map(examConverter::toDto).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Examiner toModel(ExaminerDTO from) {
        Examiner to = new Examiner();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
        to.setBlocked(from.isBlocked());
        to.setLastLoginDate(from.getLastLoginDate());
        to.setExams(from.getExams().stream().map(examConverter::toModel).collect(Collectors.toList()));
        return to;
    }
}
