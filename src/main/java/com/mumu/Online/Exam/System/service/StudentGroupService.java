package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;

import java.util.List;


public interface StudentGroupService {
    List<StudentGroup> getAll(String apiKey);

    StudentGroup validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    StudentGroup update(String apiKey, StudentGroup studentGroup);

    StudentGroup create(String apiKey, StudentGroup studentGroup);
}
