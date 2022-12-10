package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverterImpl implements QuestionConverter {

    private final ExamConverter examConverter;

    public QuestionConverterImpl(final ExamConverter examConverter) {
        this.examConverter = examConverter;
    }

    @Override
    public QuestionDTO toDto(Question from) {
        QuestionDTO to = new QuestionDTO();
        to.setId(from.getId());
        to.setExam(examConverter.toDto(from.getExam()));
        to.setText(from.getText());
        to.setAnswers(from.getAnswers());
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }

    @Override
    public Question toModel(QuestionDTO from) {
        Question to = new Question();
        to.setId(from.getId());
        to.setExam(examConverter.toModel(from.getExam()));
        to.setText(from.getText());
        to.setAnswers(from.getAnswers());
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }
}
