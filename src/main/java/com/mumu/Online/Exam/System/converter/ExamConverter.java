package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;

public interface ExamConverter {

    ExamDTO toDto(Exam from);

    Exam toModel(ExamDTO from);
}
