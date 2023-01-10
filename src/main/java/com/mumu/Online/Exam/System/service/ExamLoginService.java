package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamLoginService {
    Page<ExamLogin> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    ExamLogin validate(String apiKey, Long id);

    ExamLogin create(ExamLogin exam);

    ExamLogin getById(Long examId);

    List<ExamLogin> getAllByStudent(Long studentId);

    ExamLogin getByStudentAndExam(Long studentId, Long examId);
}
