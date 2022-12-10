package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getAll(String apiKey);

    Exam validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    Exam update(String apiKey, Exam exam);

    Exam create(String apiKey, Exam exam);
}
