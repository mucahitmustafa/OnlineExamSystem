package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.entity.Student;

public interface StudentConverter {

    StudentDTO toDto(Student from);

    Student toModel(StudentDTO from);
}
