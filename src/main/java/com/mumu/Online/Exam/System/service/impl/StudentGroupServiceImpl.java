package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.StudentGroupNotFoundException;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import com.mumu.Online.Exam.System.repository.StudentGroupRepository;
import com.mumu.Online.Exam.System.service.StudentGroupService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupServiceImpl extends AbstractService implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(final StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public List<StudentGroup> getAll(String apiKey) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return studentGroupRepository.findAllByCustomer(customer);
    }

    @Override
    public StudentGroup validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return studentGroupRepository.findByCustomerAndId(customer, id).orElseThrow(StudentGroupNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        StudentGroup studentGroup = studentGroupRepository.findByCustomerAndId(customer, id)
                .orElseThrow(StudentGroupNotFoundException::new);
        studentGroupRepository.delete(studentGroup);
    }

    @Override
    public StudentGroup update(String apiKey, StudentGroup studentGroup) {
        final String customer = ApiKeyUtil.decode(apiKey);
        studentGroupRepository.findByCustomerAndId(customer, studentGroup.getId()).orElseThrow(StudentGroupNotFoundException::new);
        studentGroup.setCustomer(customer);
        return studentGroupRepository.save(studentGroup);
    }

    @Override
    public StudentGroup create(String apiKey, StudentGroup studentGroup) {
        final String customer = ApiKeyUtil.decode(apiKey);
        studentGroup.setCustomer(customer);
        return studentGroupRepository.save(studentGroup);
    }
}
