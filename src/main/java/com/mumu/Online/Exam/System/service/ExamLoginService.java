package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamLoginService {
    Page<ExamLogin> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    ExamLogin validate(String apiKey, Long id);

    ExamLogin create(ExamLogin exam);

    List<ExamLogin> getAllByStudent(Long studentId);

    void deleteByStudentId(Long studentId);

    void deleteByExamId(Long examId);

}
