package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Examiner;

import java.util.List;

public interface ExaminerService {
    List<Examiner> getAll(String apiKey);

    Examiner validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    Examiner update(String apiKey, Examiner examiner);

    Examiner create(String apiKey, Examiner examiner);
}
