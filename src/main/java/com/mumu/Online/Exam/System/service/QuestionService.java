package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAll(String apiKey);

    Question validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    Question update(String apiKey, Question question);

    Question create(String apiKey, Question question);
}
