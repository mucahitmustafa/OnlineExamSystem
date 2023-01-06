package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExamConverterImpl implements ExamConverter {

    private final StudentService studentService;

    public ExamConverterImpl(final StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ExamDTO toDto(Exam from) {
        ExamDTO to = new ExamDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setDuration(from.getDuration());
        to.setStudents(from.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Exam toModel(ExamDTO from) {
        Exam to = new Exam();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setDuration(from.getDuration());
        to.setStudents(from.getStudents().stream().map(studentService::getById).collect(Collectors.toList()));
        return to;
    }
}
