package com.mumu.Online.Exam.System.converter;

import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Question;

public interface QuestionConverter {

    QuestionDTO toDto(Question from);

    Question toModel(QuestionDTO from);
}
