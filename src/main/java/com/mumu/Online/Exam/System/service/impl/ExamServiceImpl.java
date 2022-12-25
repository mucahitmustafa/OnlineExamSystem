package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.ExamSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.ExamNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.repository.ExamRepository;
import com.mumu.Online.Exam.System.service.ExamService;
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
public class ExamServiceImpl extends AbstractService implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(final ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Page<Exam> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort) {
        final String customer = ApiKeyUtil.decode(apiKey);

        Sort sortModel = SortUtil.createSortModel(sort, DEFAULT_SORT_DIRECTION);
        Specification<Exam> spec = getSpecification(customer, filters);
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(Math.max(pageNumber - 1, 0), pageSize, sortModel);
        return examRepository.findAll(spec, pageRequest);
    }

    @Override
    public Exam validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return examRepository.findByCustomerAndId(customer, id).orElseThrow(ExamNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Exam exam = examRepository.findByCustomerAndId(customer, id).orElseThrow(ExamNotFoundException::new);
        examRepository.delete(exam);
    }

    @Override
    public Exam update(String apiKey, Exam exam) {
        final String customer = ApiKeyUtil.decode(apiKey);
        examRepository.findByCustomerAndId(customer, exam.getId()).orElseThrow(ExamNotFoundException::new);
        exam.setCustomer(customer);
        return examRepository.save(exam);
    }

    @Override
    public Exam create(String apiKey, Exam exam) {
        final String customer = ApiKeyUtil.decode(apiKey);
        exam.setCustomer(customer);
        return examRepository.save(exam);
    }

    @Override
    public Exam getById(Long examId) {
        return examRepository.findById(examId).orElseThrow(ExamNotFoundException::new);
    }

    private Specification<Exam> getSpecification(String customer, String[] filters) {
        ExamSpecificationBuilder builder = new ExamSpecificationBuilder();

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
