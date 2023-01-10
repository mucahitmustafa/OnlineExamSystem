package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.ExamLoginSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.ExamLoginNotFoundException;
import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.repository.ExamLoginRepository;
import com.mumu.Online.Exam.System.service.ExamLoginService;
import com.mumu.Online.Exam.System.service.QuestionService;
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

import java.util.*;
import java.util.regex.Matcher;

@Service
public class ExamLoginServiceImpl extends AbstractService implements ExamLoginService {

    private final ExamLoginRepository examLoginRepository;
    private final QuestionService questionService;
    private final StudentService studentService;

    public ExamLoginServiceImpl(final ExamLoginRepository examLoginRepository, final QuestionService questionService,
                                final StudentService studentService) {
        this.examLoginRepository = examLoginRepository;
        this.questionService = questionService;
        this.studentService = studentService;
    }

    @Override
    public Page<ExamLogin> getAll(String apiKey, Integer pageNumber, Integer pageSize, String[] filters, String sort) {
        final String customer = ApiKeyUtil.decode(apiKey);

        Sort sortModel = SortUtil.createSortModel(sort, DEFAULT_SORT_DIRECTION);
        Specification<ExamLogin> spec = getSpecification(customer, filters);
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(Math.max(pageNumber - 1, 0), pageSize, sortModel);
        return examLoginRepository.findAll(spec, pageRequest);
    }

    @Override
    public ExamLogin validate(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return examLoginRepository.findByCustomerAndId(customer, id).orElseThrow(ExamLoginNotFoundException::new);
    }

    @Override
    public ExamLogin create(ExamLogin examLogin) {
        examLogin.setCustomer(examLogin.getStudent().getCustomer());
        examLogin.setCreated(new Date());
        examLogin.setUpdated(new Date());

        String[] answers = examLogin.getAnswers().split(",");
        Long examId = examLogin.getExam().getId();
        List<Question> questions = questionService.getByExam(examId);
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String answer = answers[i];
            if (Objects.equals(answer, Arrays.asList("A", "B", "C", "D").get(q.getCorrectAnswerIndex()))) {
                score += 100 / questions.size();
            }
        }
        examLogin.setScore(Math.min(score, 100));
        return examLoginRepository.save(examLogin);
    }

    @Override
    public ExamLogin getById(Long examLoginId) {
        return examLoginRepository.findById(examLoginId).orElseThrow(ExamLoginNotFoundException::new);
    }

    @Override
    public List<ExamLogin> getAllByStudent(Long studentId) {
        return examLoginRepository.findByStudent_Id(studentId);
    }

    @Override
    public ExamLogin getByStudentAndExam(Long studentId, Long examId) {
        return examLoginRepository.findByStudent_IdAndExam_Id(studentId, examId);
    }

    private Specification<ExamLogin> getSpecification(String customer, String[] filters) {
        ExamLoginSpecificationBuilder builder = new ExamLoginSpecificationBuilder();

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
