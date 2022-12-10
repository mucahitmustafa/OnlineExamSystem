package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.QuestionNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.repository.QuestionRepository;
import com.mumu.Online.Exam.System.service.QuestionService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAll(String apiKey) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return questionRepository.findAllByCustomer(customer);
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
        questionRepository.findByCustomerAndId(customer, question.getId()).orElseThrow(QuestionNotFoundException::new);
        question.setCustomer(customer);
        return questionRepository.save(question);
    }

    @Override
    public Question create(String apiKey, Question question) {
        final String customer = ApiKeyUtil.decode(apiKey);
        question.setCustomer(customer);
        return questionRepository.save(question);
    }
}
