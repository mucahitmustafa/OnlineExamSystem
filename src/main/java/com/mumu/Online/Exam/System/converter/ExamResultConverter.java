package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.ExamResultDTO;
import com.mumu.Online.Exam.System.model.entity.ExamResult;

public interface ExamResultConverter {

    ExamResultDTO toDto(ExamResult from);

    ExamResult toModel(ExamResultDTO from);
}
