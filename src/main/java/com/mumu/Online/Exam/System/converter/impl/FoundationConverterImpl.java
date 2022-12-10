package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.converter.FoundationConverter;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.FoundationDTO;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FoundationConverterImpl implements FoundationConverter {

    private final ExaminerConverter examinerConverter;
    private final StudentConverter studentConverter;
    private final ExamConverter examConverter;

    public FoundationConverterImpl(final ExaminerConverter examinerConverter,
                                   final StudentConverter studentConverter,
                                   final ExamConverter examConverter) {
        this.examinerConverter = examinerConverter;
        this.studentConverter = studentConverter;
        this.examConverter = examConverter;
    }

    @Override
    public FoundationDTO toDto(Foundation from) {
        FoundationDTO to = new FoundationDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setManagers(from.getManagers().stream().map(examinerConverter::toDto).collect(Collectors.toList()));
        to.setExaminers(from.getExaminers().stream().map(examinerConverter::toDto).collect(Collectors.toList()));
        to.setStudents(from.getStudents().stream().map(studentConverter::toDto).collect(Collectors.toList()));
        to.setExams(from.getExams().stream().map(examConverter::toDto).collect(Collectors.toList()));
        return to;
    }

    @Override
    public Foundation toModel(FoundationDTO from) {
        Foundation to = new Foundation();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setManagers(from.getManagers().stream().map(examinerConverter::toModel).collect(Collectors.toList()));
        to.setExaminers(from.getExaminers().stream().map(examinerConverter::toModel).collect(Collectors.toList()));
        to.setStudents(from.getStudents().stream().map(studentConverter::toModel).collect(Collectors.toList()));
        to.setExams(from.getExams().stream().map(examConverter::toModel).collect(Collectors.toList()));
        return to;
    }
}
