package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamResultConverter;
import com.mumu.Online.Exam.System.model.dto.ExamResultDTO;
import com.mumu.Online.Exam.System.model.entity.ExamResult;
import com.mumu.Online.Exam.System.service.ExamService;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.stereotype.Component;

@Component
public class ExamResultConverterImpl implements ExamResultConverter {

    private final ExamService examService;
    private final StudentService studentService;

    public ExamResultConverterImpl(final ExamService examService,
                                   final StudentService studentService) {
        this.examService = examService;
        this.studentService = studentService;
    }

    @Override
    public ExamResultDTO toDto(ExamResult from) {
        ExamResultDTO to = new ExamResultDTO();
        to.setId(from.getId());
        to.setExamId(from.getExam().getId());
        to.setStudentId(from.getStudent().getId());
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }

    @Override
    public ExamResult toModel(ExamResultDTO from) {
        ExamResult to = new ExamResult();
        to.setId(from.getId());
        to.setExam(examService.getById(from.getExamId()));
        to.setStudent(studentService.getById(from.getStudentId()));
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }
}
