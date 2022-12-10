package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.ExamResultConverter;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.ExamResultDTO;
import com.mumu.Online.Exam.System.model.entity.ExamResult;
import org.springframework.stereotype.Component;

@Component
public class ExamResultConverterImpl implements ExamResultConverter {

    private final ExamConverter examConverter;
    private final StudentConverter studentConverter;

    public ExamResultConverterImpl(final ExamConverter examConverter,
                                   final StudentConverter studentConverter) {
        this.examConverter = examConverter;
        this.studentConverter = studentConverter;
    }

    @Override
    public ExamResultDTO toDto(ExamResult from) {
        ExamResultDTO to = new ExamResultDTO();
        to.setId(from.getId());
        to.setExam(examConverter.toDto(from.getExam()));
        to.setStudent(studentConverter.toDto(from.getStudent()));
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }

    @Override
    public ExamResult toModel(ExamResultDTO from) {
        ExamResult to = new ExamResult();
        to.setId(from.getId());
        to.setExam(examConverter.toModel(from.getExam()));
        to.setStudent(studentConverter.toModel(from.getStudent()));
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }
}
