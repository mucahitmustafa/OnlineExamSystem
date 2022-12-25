package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.StudentSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.StudentNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.repository.StudentRepository;
import com.mumu.Online.Exam.System.service.StudentService;
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
public class StudentServiceImpl extends AbstractService implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort) {
        final String customer = ApiKeyUtil.decode(apiKey);

        Sort sortModel = SortUtil.createSortModel(sort, DEFAULT_SORT_DIRECTION);
        Specification<Student> spec = getSpecification(customer, filters);
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(Math.max(pageNumber - 1, 0), pageSize, sortModel);
        return studentRepository.findAll(spec, pageRequest);
    }

    @Override
    public Student validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return studentRepository.findByCustomerAndId(customer, id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Student student = studentRepository.findByCustomerAndId(customer, id)
                .orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
    }

    @Override
    public Student update(String apiKey, Student student) {
        final String customer = ApiKeyUtil.decode(apiKey);
        studentRepository.findByCustomerAndId(customer, student.getId()).orElseThrow(StudentNotFoundException::new);
        student.setCustomer(customer);
        return studentRepository.save(student);
    }

    @Override
    public Student create(String apiKey, Student student) {
        final String customer = ApiKeyUtil.decode(apiKey);
        student.setCustomer(customer);
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    private Specification<Student> getSpecification(String customer, String[] filters) {
        StudentSpecificationBuilder builder = new StudentSpecificationBuilder();

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
