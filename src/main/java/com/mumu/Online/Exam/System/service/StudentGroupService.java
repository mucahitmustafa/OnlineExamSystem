package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.data.domain.Page;


public interface StudentGroupService {
    Page<StudentGroup> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort);

    StudentGroup validate(String apiKey, Long id);

    void delete(String apiKey, Long id);

    StudentGroup update(String apiKey, StudentGroup studentGroup);

    StudentGroup create(String apiKey, StudentGroup studentGroup);

    StudentGroup getById(Long groupId);
}
