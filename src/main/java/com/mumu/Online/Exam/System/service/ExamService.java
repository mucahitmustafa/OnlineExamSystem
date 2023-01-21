package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.dto.ExamStatisticDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamService {
    Page<Exam> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    Exam validate(Long id);

    void delete(String apiKey, Long id);

    Exam update(String apiKey, Exam exam, List<Question> questions);

    Exam create(String apiKey, Exam exam);

    Exam getById(Long examId);

    List<Exam> getUncompletedExamsByStudent(Long studentId);

    ExamStatisticDTO getStatistics(Long id);
}
