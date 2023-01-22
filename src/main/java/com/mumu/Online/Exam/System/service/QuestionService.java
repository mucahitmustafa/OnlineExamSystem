package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {
    Page<Question> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    Question validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    Question update(String apiKey, Question question);

    Question create(String apiKey, Question question);

    List<Question> getByExam(Long examId);

    void deleteByExamId(Long examId);

    Question getById(Long questionId);

    Question getByExamIdAndIndex(Long examId, Long index);

    int countByExam(Long examId);
}
