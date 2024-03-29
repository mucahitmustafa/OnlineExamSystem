package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<Student> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    Student validate(Long id);

    void delete(String apiKey, Long id);

    Student update(String apiKey, Student student);

    Student create(String apiKey, Student student);

    Student getById(Long studentId);

    Student login(String username, String password);

    void register(String foundationCode, Student student);

    void approve(Long id);
}
