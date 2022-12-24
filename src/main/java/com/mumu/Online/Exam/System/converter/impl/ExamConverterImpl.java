package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.service.ExaminerService;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExamConverterImpl implements ExamConverter {

    private final ExaminerService examinerService;
    private final StudentService studentService;

    public ExamConverterImpl(final ExaminerService examinerService,
                             final StudentService studentService) {
        this.examinerService = examinerService;
        this.studentService = studentService;
    }

    @Override
    public ExamDTO toDto(Exam from) {
        ExamDTO to = new ExamDTO();
        to.setId(from.getId());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setDuration(from.getDuration());
        to.setExaminerId(from.getExaminer().getId());
        to.setStudents(from.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Exam toModel(ExamDTO from) {
        Exam to = new Exam();
        to.setId(from.getId());
        to.setStartDate(from.getStartDate());
        to.setEndDate(from.getEndDate());
        to.setDuration(from.getDuration());
        to.setExaminer(examinerService.getById(from.getExaminerId()));
        to.setStudents(from.getStudents().stream().map(studentService::getById).collect(Collectors.toList()));
        return to;
    }
}
