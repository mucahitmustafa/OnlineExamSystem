package com.mumu.Online.Exam.System.converter.impl;

import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        to.setScore(from.getScore());
        to.setIndex(from.getIndex());
        to.setExamId(from.getExam().getId());
        to.setText(from.getText());
        to.setAnswers(Arrays.stream(from.getAnswers().split("###")).collect(Collectors.toList()));
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }

    @Override
    public Question toModel(QuestionDTO from) {
        Question to = new Question();
        to.setId(from.getId());
        to.setScore(from.getScore());
        to.setIndex(from.getIndex());
        to.setExam(examService.getById(from.getExamId()));
        to.setText(from.getText());
        to.setAnswers(String.join("###", from.getAnswers()));
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }

    @Override
    public Question toModelForCreate(QuestionDTO from) {
        Question to = new Question();
        to.setText(from.getText());
        to.setScore(from.getScore());
        to.setIndex(from.getIndex());
        to.setAnswers(String.join("###", from.getAnswers()));
        to.setCorrectAnswerIndex(from.getCorrectAnswerIndex());
        return to;
    }
}
