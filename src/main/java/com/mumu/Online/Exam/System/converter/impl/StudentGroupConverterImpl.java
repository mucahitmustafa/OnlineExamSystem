package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.converter.StudentGroupConverter;
import com.mumu.Online.Exam.System.model.dto.StudentGroupDTO;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentGroupConverterImpl implements StudentGroupConverter {

    private final StudentConverter studentConverter;

    public StudentGroupConverterImpl(final StudentConverter studentConverter) {
        this.studentConverter = studentConverter;
    }

    @Override
    public StudentGroupDTO toDto(StudentGroup from) {
        StudentGroupDTO to = new StudentGroupDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStudents(from.getStudents().stream().map(studentConverter::toDto).collect(Collectors.toList()));
        return to;
    }

    @Override
    public StudentGroup toModel(StudentGroupDTO from) {
        StudentGroup to = new StudentGroup();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setStudents(from.getStudents().stream().map(studentConverter::toModel).collect(Collectors.toList()));
        return to;
    }
}
