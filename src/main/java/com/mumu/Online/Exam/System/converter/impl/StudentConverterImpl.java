package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamResultConverter;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.converter.StudentGroupConverter;
import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentConverterImpl implements StudentConverter {

    private final StudentGroupConverter studentGroupConverter;
    private final ExamResultConverter examResultConverter;

    public StudentConverterImpl(final StudentGroupConverter studentGroupConverter,
                                final ExamResultConverter examResultConverter) {
        this.studentGroupConverter = studentGroupConverter;
        this.examResultConverter = examResultConverter;
    }

    @Override
    public StudentDTO toDto(Student from) {
        StudentDTO to = new StudentDTO();
        to.setId(from.getId());
        to.setNumber(from.getNumber());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
        to.setBlocked(from.isBlocked());
        to.setLastLoginDate(from.getLastLoginDate());
        to.setGroup(studentGroupConverter.toDto(from.getGroup()));
        to.setResults(from.getResults().stream().map(examResultConverter::toDto).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Student toModel(StudentDTO from) {
        Student to = new Student();
        to.setId(from.getId());
        to.setNumber(from.getNumber());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
        to.setBlocked(from.isBlocked());
        to.setLastLoginDate(from.getLastLoginDate());
        to.setGroup(studentGroupConverter.toModel(from.getGroup()));
        to.setResults(from.getResults().stream().map(examResultConverter::toModel).collect(Collectors.toList()));
        return to;
    }
}
