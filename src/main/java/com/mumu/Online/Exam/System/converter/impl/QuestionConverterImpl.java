package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverterImpl implements QuestionConverter {

    private final ExamService examService;

    public QuestionConverterImpl(final ExamService examService) {
        this.examService = examService;
    }

    @Override
    public QuestionDTO toDto(Question from) {
        QuestionDTO to = new QuestionDTO();
        to.setId(from.getId());
        to.setExamId(from.getExam().getId());
        to.setText(from.getText());
        to.setAnswers(from.getAnswers());
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }

    @Override
    public Question toModel(QuestionDTO from) {
        Question to = new Question();
        to.setId(from.getId());
        to.setExam(examService.getById(from.getExamId()));
        to.setText(from.getText());
        to.setAnswers(from.getAnswers());
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }
}
