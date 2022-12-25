package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import org.springframework.data.domain.Page;

public interface ExamLoginService {
    Page<ExamLogin> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    ExamLogin validate(String apiKey, Long id);

    ExamLogin create(String apiKey, ExamLogin exam);

    ExamLogin getById(Long examId);
}
