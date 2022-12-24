package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamLoginConverter;
import com.mumu.Online.Exam.System.model.dto.ExamLoginDTO;
import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import com.mumu.Online.Exam.System.service.ExamService;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.stereotype.Component;

@Component
public class ExamLoginConverterImpl implements ExamLoginConverter {

    private final ExamService examService;
    private final StudentService studentService;

    public ExamLoginConverterImpl(final ExamService examService,
                                   final StudentService studentService) {
        this.examService = examService;
        this.studentService = studentService;
    }

    @Override
    public ExamLoginDTO toDto(ExamLogin from) {
        ExamLoginDTO to = new ExamLoginDTO();
        to.setId(from.getId());
        to.setExamId(from.getExam().getId());
        to.setStudentId(from.getStudent().getId());
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }

    @Override
    public ExamLogin toModel(ExamLoginDTO from) {
        ExamLogin to = new ExamLogin();
        to.setId(from.getId());
        to.setExam(examService.getById(from.getExamId()));
        to.setStudent(studentService.getById(from.getStudentId()));
        to.setAnswers(from.getAnswers());
        to.setScore(from.getScore());
        to.setLoginDate(from.getLoginDate());
        return to;
    }
}
