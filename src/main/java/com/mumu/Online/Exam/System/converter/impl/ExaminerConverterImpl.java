package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.model.dto.ExaminerDTO;
import com.mumu.Online.Exam.System.model.entity.Examiner;
import org.springframework.stereotype.Component;

@Component
public class ExaminerConverterImpl implements ExaminerConverter {

    @Override
    public ExaminerDTO toDto(Examiner from) {
        ExaminerDTO to = new ExaminerDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
        to.setBlocked(from.isBlocked());
        to.setLastLoginDate(from.getLastLoginDate());
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
        return to;
    }
}
