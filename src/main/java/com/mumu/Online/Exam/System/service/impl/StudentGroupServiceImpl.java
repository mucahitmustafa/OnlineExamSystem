package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.StudentGroupSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.StudentGroupNotFoundException;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import com.mumu.Online.Exam.System.repository.StudentGroupRepository;
import com.mumu.Online.Exam.System.service.StudentGroupService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import com.mumu.Online.Exam.System.utils.RegexUtil;
import com.mumu.Online.Exam.System.utils.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

@Service
public class StudentGroupServiceImpl extends AbstractService implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(final StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public Page<StudentGroup> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort) {
        final String customer = ApiKeyUtil.decode(apiKey);

        Sort sortModel = SortUtil.createSortModel(sort, DEFAULT_SORT_DIRECTION);
        Specification<StudentGroup> spec = getSpecification(customer, filters);
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(Math.max(pageNumber - 1, 0), pageSize, sortModel);
        return studentGroupRepository.findAll(spec, pageRequest);
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

    @Override
    public StudentGroup getById(Long groupId) {
        return studentGroupRepository.findById(groupId).orElseThrow(StudentGroupNotFoundException::new);
    }

    private Specification<StudentGroup> getSpecification(String customer, String[] filters) {
        StudentGroupSpecificationBuilder builder = new StudentGroupSpecificationBuilder();

        for (String filter : filters) {
            if (filter.equals("")) continue;
            Matcher matcher = RegexUtil.getFilterMatcher(filter);
            boolean result = matcher.find();
            if (result) {
                String key = matcher.group(1);
                String operator = matcher.group(2);
                Object value = matcher.group(3);
                builder.with(key, operator, value);
            }
        }
        builder.with("customer", "Equal", customer);
        return builder.build();
    }
}
