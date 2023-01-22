package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.ExamSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.ExamNotFoundException;
import com.mumu.Online.Exam.System.model.dto.ExamStatisticDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.ExamLogin;
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

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

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
    public List<Exam> getUncompletedExamsByStudent(Long studentId) {
        List<Exam> exams = examRepository.findExamsTheStudentHasNotTaken(studentId);
        return exams.stream().filter(exam -> exam.getEndDate().after(new Date()) &&
                exam.getStartDate().before(new Date()) && questionService.countByExam(exam.getId()) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public ExamStatisticDTO getStatistics(Long id) {
        Exam exam = validate(id);
        List<ExamLogin> examLogins = examLoginService.getByExamId(id);
        ExamStatisticDTO examStatisticDTO = new ExamStatisticDTO();
        examStatisticDTO.setId(id);
        examStatisticDTO.setName(exam.getName());
        examStatisticDTO.setTotalParticipants(examLogins.size());
        examStatisticDTO.setAverageScore(examLogins.stream().mapToDouble(ExamLogin::getScore).average().orElse(0));
        examStatisticDTO.setHighestScore(examLogins.stream().mapToDouble(ExamLogin::getScore).max().orElse(0));
        examStatisticDTO.setLowestScore(examLogins.stream().mapToDouble(ExamLogin::getScore).min().orElse(0));

        List<String> correctAnswers = new ArrayList<>();
        examLogins.stream().map(ExamLogin::getCorrectAnswers).forEach(correctAnswersStr -> {
            correctAnswers.addAll(Arrays.stream(correctAnswersStr.split(",")).collect(Collectors.toList()));
        });

        List<String> wrongAnswers = new ArrayList<>();
        examLogins.stream().map(ExamLogin::getWrongAnswers).forEach(wrongAnswersStr -> {
            wrongAnswers.addAll(Arrays.stream(wrongAnswersStr.split(",")).collect(Collectors.toList()));
        });

        List<String> blankAnswers = new ArrayList<>();
        examLogins.stream().map(ExamLogin::getBlankAnswers).forEach(blankAnswersStr -> {
            blankAnswers.addAll(Arrays.stream(blankAnswersStr.split(",")).collect(Collectors.toList()));
        });

        Set<Map.Entry<String, Long>> correctAnswerCounts = correctAnswers.stream().collect(Collectors
                .groupingBy(Function.identity(), counting())).entrySet();

        Set<Map.Entry<String, Long>> wrongAnswerCounts = wrongAnswers.stream().collect(Collectors
                .groupingBy(Function.identity(), counting())).entrySet();

        Set<Map.Entry<String, Long>> blankAnswerCounts = blankAnswers.stream().collect(Collectors
                .groupingBy(Function.identity(), counting())).entrySet();

        correctAnswerCounts.stream().max((first, second) -> (int) (first.getValue() - second.getValue()))
                .ifPresent(stringLongEntry -> {
                    if (!stringLongEntry.getKey().equals("")) {
                        examStatisticDTO.setMostCorrectQuestionId(Long.parseLong(stringLongEntry.getKey()));
                    }
                });

        wrongAnswerCounts.stream().max((first, second) -> (int) (first.getValue() - second.getValue()))
                .ifPresent(stringLongEntry -> {
                    if (!stringLongEntry.getKey().equals("")) {
                        examStatisticDTO.setMostWrongQuestionId(Long.parseLong(stringLongEntry.getKey()));
                    }
                });

        blankAnswerCounts.stream().max((first, second) -> (int) (first.getValue() - second.getValue()))
                .ifPresent(stringLongEntry -> {
                    if (!stringLongEntry.getKey().equals("")) {
                        examStatisticDTO.setMostLeftBlankQuestionId(Long.parseLong(stringLongEntry.getKey()));
                    }
                });

        if (examStatisticDTO.getMostCorrectQuestionId() == null) {
            examStatisticDTO.setMostCorrectQuestionId(-1L);
            examStatisticDTO.setMostCorrectQuestion("No question answered correctly");
        } else {
            Question question = questionService.getByExamIdAndIndex(id, examStatisticDTO.getMostCorrectQuestionId());
            examStatisticDTO.setMostCorrectQuestion(question.getIndex() + ". ) " + question.getText());
        }

        if (examStatisticDTO.getMostWrongQuestionId() == null) {
            examStatisticDTO.setMostWrongQuestionId(-1L);
            examStatisticDTO.setMostWrongQuestion("No question answered wrong");
        } else {
            Question question = questionService.getByExamIdAndIndex(id, examStatisticDTO.getMostWrongQuestionId());
            examStatisticDTO.setMostWrongQuestion(question.getIndex() + ". ) " + question.getText());
        }

        if (examStatisticDTO.getMostLeftBlankQuestionId() == null) {
            examStatisticDTO.setMostLeftBlankQuestionId(-1L);
            examStatisticDTO.setMostLeftBlankQuestion("No question left blank");
        } else {
            Question question = questionService.getByExamIdAndIndex(id, examStatisticDTO.getMostLeftBlankQuestionId());
            examStatisticDTO.setMostLeftBlankQuestion(question.getIndex() + ". ) " + question.getText());
        }

        HashMap<Long, String> questionStatistic = new HashMap<>();

        List<Question> questions = questionService.getByExam(id);
        questions.forEach(question -> {
            long correctCount = correctAnswerCounts.stream().filter(entry -> entry.getKey().equals(question.getIndex()
                    .toString())).mapToLong(Map.Entry::getValue).sum();
            long wrongCount = wrongAnswerCounts.stream().filter(entry -> entry.getKey().equals(question.getIndex()
                    .toString())).mapToLong(Map.Entry::getValue).sum();
            long blankCount = blankAnswerCounts.stream().filter(entry -> entry.getKey().equals(question.getIndex()
                    .toString())).mapToLong(Map.Entry::getValue).sum();
            questionStatistic.put(question.getIndex(), correctCount + " / " + wrongCount + " / " + blankCount);
        });

        examStatisticDTO.setQuestionStatistic(questionStatistic);
        return examStatisticDTO;
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
                if (value.equals("false")) value = false;
                if (value.equals("true")) value = true;
                builder.with(key, operator, value);
            }
        }
        builder.with("customer", "#Equal#", customer);
        return builder.build();
    }
}
