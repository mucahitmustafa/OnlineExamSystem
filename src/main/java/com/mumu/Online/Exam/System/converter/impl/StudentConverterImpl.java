package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.service.StudentGroupService;
import org.springframework.stereotype.Component;


@Component
public class StudentConverterImpl implements StudentConverter {

    private final StudentGroupService studentGroupService;

    public StudentConverterImpl(final StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
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
        to.setGroupId(from.getGroup().getId());
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
        to.setGroup(studentGroupService.getById(from.getGroupId()));
        return to;
    }
}
