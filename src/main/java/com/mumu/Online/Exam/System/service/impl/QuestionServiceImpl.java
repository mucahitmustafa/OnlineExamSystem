package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.QuestionSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.QuestionNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.repository.QuestionRepository;
import com.mumu.Online.Exam.System.service.QuestionService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import com.mumu.Online.Exam.System.utils.RegexUtil;
import com.mumu.Online.Exam.System.utils.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort) {
        final String customer = ApiKeyUtil.decode(apiKey);

        Sort sortModel = SortUtil.createSortModel(sort, DEFAULT_SORT_DIRECTION);
        Specification<Question> spec = getSpecification(customer, filters);
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(Math.max(pageNumber - 1, 0), pageSize, sortModel);
        return questionRepository.findAll(spec, pageRequest);
    }

    @Override
    public Question validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return questionRepository.findByCustomerAndId(customer, id).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Question question = questionRepository.findByCustomerAndId(customer, id).orElseThrow(QuestionNotFoundException::new);
        questionRepository.delete(question);
    }

    @Override
    public Question update(String apiKey, Question question) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Question originalQuestion = questionRepository.findByCustomerAndId(customer, question.getId())
                .orElseThrow(QuestionNotFoundException::new);
        question.setCustomer(customer);
        question.setCreated(originalQuestion.getCreated());
        question.setUpdated(new Date());
        return questionRepository.save(question);
    }

    @Override
    public Question create(String apiKey, Question question) {
        final String customer = ApiKeyUtil.decode(apiKey);
        question.setCustomer(customer);
        question.setCreated(new Date());
        question.setUpdated(new Date());
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getByExam(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    @Override
    public void deleteByExamId(Long examId) {
        questionRepository.deleteByExamId(examId);
    }

    private Specification<Question> getSpecification(String customer, String[] filters) {
        QuestionSpecificationBuilder builder = new QuestionSpecificationBuilder();

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
        builder.with("customer", "#Equal#", customer);
        return builder.build();
    }
}
