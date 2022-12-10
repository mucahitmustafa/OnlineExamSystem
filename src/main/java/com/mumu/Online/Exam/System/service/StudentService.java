package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll(String apiKey);

    Student validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    Student update(String apiKey, Student student);

    Student create(String apiKey, Student student);
}
