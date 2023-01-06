package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentConverterImpl implements StudentConverter {

    @Override
    public StudentDTO toDto(Student from) {
        StudentDTO to = new StudentDTO();
        to.setId(from.getId());
        to.setNumber(from.getNumber());
        to.setName(from.getName());
        to.setMail(from.getMail());
        to.setPassword(from.getPassword());
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
        return to;
    }
}
