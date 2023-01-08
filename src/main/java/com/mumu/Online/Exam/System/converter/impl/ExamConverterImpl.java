package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.dto.ExamUpdateDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import org.springframework.stereotype.Component;

@Component
public class ExamConverterImpl implements ExamConverter {

    @Override
    public ExamDTO toDto(Exam from) {
        ExamDTO to = new ExamDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        return to;
    }

    @Override
    public Exam toModel(ExamDTO from) {
        Exam to = new Exam();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        return to;
    }

    @Override
    public Exam toModelForUpdate(ExamUpdateDTO from) {
        Exam to = new Exam();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        return to;
    }
}
