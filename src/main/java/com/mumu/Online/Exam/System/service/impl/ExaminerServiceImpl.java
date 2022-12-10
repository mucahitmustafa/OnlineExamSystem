package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.ExaminerNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Examiner;
import com.mumu.Online.Exam.System.repository.ExaminerRepository;
import com.mumu.Online.Exam.System.service.ExaminerService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminerServiceImpl extends AbstractService implements ExaminerService {

    private final ExaminerRepository examinerRepository;

    public ExaminerServiceImpl(final ExaminerRepository examinerRepository) {
        this.examinerRepository = examinerRepository;
    }

    @Override
    public List<Examiner> getAll(String apiKey) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return examinerRepository.findAllByCustomer(customer);
    }

    @Override
    public Examiner validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return examinerRepository.findByCustomerAndId(customer, id).orElseThrow(ExaminerNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Examiner examiner = examinerRepository.findByCustomerAndId(customer, id)
                .orElseThrow(ExaminerNotFoundException::new);
        examinerRepository.delete(examiner);
    }

    @Override
    public Examiner update(String apiKey, Examiner examiner) {
        final String customer = ApiKeyUtil.decode(apiKey);
        examinerRepository.findByCustomerAndId(customer, examiner.getId()).orElseThrow(ExaminerNotFoundException::new);
        examiner.setCustomer(customer);
        return examinerRepository.save(examiner);
    }

    @Override
    public Examiner create(String apiKey, Examiner examiner) {
        final String customer = ApiKeyUtil.decode(apiKey);
        examiner.setCustomer(customer);
        return examinerRepository.save(examiner);
    }
}
