package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.ExamLoginDTO;
import com.mumu.Online.Exam.System.model.entity.ExamLogin;

public interface ExamLoginConverter {

    ExamLoginDTO toDto(ExamLogin from);

    ExamLogin toModel(ExamLoginDTO from);
}
