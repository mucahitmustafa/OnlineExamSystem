package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.ExamNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.repository.ExamRepository;
import com.mumu.Online.Exam.System.service.ExamService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl extends AbstractService implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(final ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> getAll(String apiKey) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return examRepository.findAllByCustomer(customer);
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
}
