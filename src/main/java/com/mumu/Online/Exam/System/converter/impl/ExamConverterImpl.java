package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExamConverterImpl implements ExamConverter {

    private final ExaminerConverter examinerConverter;
    private final StudentConverter studentConverter;
    private final QuestionConverter questionConverter;

    public ExamConverterImpl(final ExaminerConverter examinerConverter,
                             final StudentConverter studentConverter,
                             final QuestionConverter questionConverter) {
        this.examinerConverter = examinerConverter;
        this.studentConverter = studentConverter;
        this.questionConverter = questionConverter;
    }

    @Override
    public ExamDTO toDto(Exam from) {
        ExamDTO to = new ExamDTO();
        to.setId(from.getId());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setExaminer(examinerConverter.toDto(from.getExaminer()));
        to.setStudents(from.getStudents().stream().map(studentConverter::toDto).collect(Collectors.toList()));
        to.setQuestions(from.getQuestions().stream().map(questionConverter::toDto).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Exam toModel(ExamDTO from) {
        Exam to = new Exam();
        to.setId(from.getId());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setExaminer(examinerConverter.toModel(from.getExaminer()));
        to.setStudents(from.getStudents().stream().map(studentConverter::toModel).collect(Collectors.toList()));
        to.setQuestions(from.getQuestions().stream().map(questionConverter::toModel).collect(Collectors.toList()));
        return to;
    }
}
