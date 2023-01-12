package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.FoundationConverter;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.FoundationDTO;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FoundationConverterImpl implements FoundationConverter {

    private final StudentConverter studentConverter;
    private final ExamConverter examConverter;

    public FoundationConverterImpl(final StudentConverter studentConverter,
                                   final ExamConverter examConverter) {
        this.studentConverter = studentConverter;
        this.examConverter = examConverter;
    }

    @Override
    public FoundationDTO toDto(Foundation from) {
        FoundationDTO to = new FoundationDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        return to;
    }

    @Override
    public Foundation toModel(FoundationDTO from) {
        Foundation to = new Foundation();
        to.setId(from.getId());
        to.setName(from.getName());
        return to;
    }
}
