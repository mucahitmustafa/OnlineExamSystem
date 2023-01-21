package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.FoundationConverter;
import com.mumu.Online.Exam.System.model.dto.FoundationDTO;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import org.springframework.stereotype.Component;

@Component
public class FoundationConverterImpl implements FoundationConverter {


    @Override
    public FoundationDTO toDto(Foundation from) {
        FoundationDTO to = new FoundationDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setPublicCode(from.getPublicCode());
        return to;
    }

}
