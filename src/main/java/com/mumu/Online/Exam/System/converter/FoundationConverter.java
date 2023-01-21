package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.FoundationDTO;
import com.mumu.Online.Exam.System.model.entity.Foundation;

public interface FoundationConverter {

    FoundationDTO toDto(Foundation from);

}
