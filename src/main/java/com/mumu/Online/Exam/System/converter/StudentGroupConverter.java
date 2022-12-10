package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.StudentGroupDTO;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;

public interface StudentGroupConverter {

    StudentGroupDTO toDto(StudentGroup from);

    StudentGroup toModel(StudentGroupDTO from);
}
