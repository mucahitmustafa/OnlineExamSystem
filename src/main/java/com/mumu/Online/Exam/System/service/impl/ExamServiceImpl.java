package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.ExamSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.ExamNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.repository.ExamRepository;
import com.mumu.Online.Exam.System.service.ExamLoginService;
import com.mumu.Online.Exam.System.service.ExamService;
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
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl extends AbstractService implements ExamService {

    private final ExamRepository examRepository;
    private final QuestionService questionService;
    private final ExamLoginService examLoginService;

    public ExamServiceImpl(final ExamRepository examRepository, final QuestionService questionService,
                           final ExamLoginService examLoginService) {
        this.examRepository = examRepository;
        this.questionService = questionService;
        this.examLoginService = examLoginService;
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
    public Exam validate(Long id) {
        return examRepository.findById(id).orElseThrow(ExamNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Exam exam = examRepository.findByCustomerAndId(customer, id).orElseThrow(ExamNotFoundException::new);
        questionService.deleteByExamId(id);
        examLoginService.deleteByExamId(id);
        examRepository.delete(exam);
    }

    @Override
    public Exam update(String apiKey, Exam exam, List<Question> questions) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Exam originalExam = examRepository.findByCustomerAndId(customer, exam.getId())
                .orElseThrow(ExamNotFoundException::new);
        exam.setCustomer(customer);
        exam.setCreated(originalExam.getCreated());
        exam.setUpdated(new Date());
        Exam savedExam = examRepository.save(exam);
        questionService.deleteByExamId(savedExam.getId());
        questions.forEach(question -> {
            question.setExam(savedExam);
            questionService.create(apiKey, question);
        });
        return savedExam;
    }

    @Override
    public Exam create(String apiKey, Exam exam) {
        final String customer = ApiKeyUtil.decode(apiKey);
        exam.setCustomer(customer);
        exam.setCreated(new Date());
        exam.setUpdated(new Date());
        return examRepository.save(exam);
    }

    @Override
    public Exam getById(Long examId) {
        return examRepository.findById(examId).orElseThrow(ExamNotFoundException::new);
    }

    @Override
    public List<Exam> getUncompletedExamsAllByStudent(Long studentId) {
        List<Exam> exams = examRepository.findExamsTheStudentHasNotTaken(studentId);
        return exams.stream().filter(exam -> exam.getEndDate().after(new Date()) &&
                exam.getStartDate().before(new Date())).collect(Collectors.toList());
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
        builder.with("customer", "#Equal#", customer);
        return builder.build();
    }
}
