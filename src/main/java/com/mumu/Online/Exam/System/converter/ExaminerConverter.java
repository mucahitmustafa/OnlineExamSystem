package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.ExaminerDTO;
import com.mumu.Online.Exam.System.model.entity.Examiner;

public interface ExaminerConverter {

    ExaminerDTO toDto(Examiner from);

    Examiner toModel(ExaminerDTO from);
}
