package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.StudentGroupConverter;
import com.mumu.Online.Exam.System.model.dto.StudentGroupDTO;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupConverterImpl implements StudentGroupConverter {

    @Override
    public StudentGroupDTO toDto(StudentGroup from) {
        StudentGroupDTO to = new StudentGroupDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        return to;
    }

    @Override
    public StudentGroup toModel(StudentGroupDTO from) {
        StudentGroup to = new StudentGroup();
        to.setId(from.getId());
        to.setName(from.getName());
        return to;
    }
}
