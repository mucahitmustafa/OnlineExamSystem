package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.builder.StudentSpecificationBuilder;
import com.mumu.Online.Exam.System.exception.StudentAlreadyExistException;
import com.mumu.Online.Exam.System.exception.StudentNotFoundException;
import com.mumu.Online.Exam.System.exception.WrongMailOrPasswordException;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.repository.StudentRepository;
import com.mumu.Online.Exam.System.service.ExamLoginService;
import com.mumu.Online.Exam.System.service.FoundationService;
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

import java.util.Date;
import java.util.regex.Matcher;

@Service
public class StudentServiceImpl extends AbstractService implements StudentService {

    private final StudentRepository studentRepository;
    private final ExamLoginService examLoginService;
    private final FoundationService foundationService;

    public StudentServiceImpl(final StudentRepository studentRepository, final ExamLoginService examLoginService,
                              final FoundationService foundationService) {
        this.studentRepository = studentRepository;
        this.examLoginService = examLoginService;
        this.foundationService = foundationService;
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
    public Student validate(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void delete(String apiKey, Long id) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Student student = studentRepository.findByCustomerAndId(customer, id)
                .orElseThrow(StudentNotFoundException::new);
        examLoginService.deleteByStudentId(id);
        studentRepository.delete(student);
    }

    @Override
    public Student update(String apiKey, Student student) {
        final String customer = ApiKeyUtil.decode(apiKey);
        Student originalStudent = studentRepository.findByCustomerAndId(customer, student.getId())
                .orElseThrow(StudentNotFoundException::new);
        student.setCustomer(customer);
        student.setCreated(originalStudent.getCreated());
        student.setUpdated(new Date());
        return studentRepository.save(student);
    }

    @Override
    public Student create(String apiKey, Student student) {
        final String customer = ApiKeyUtil.decode(apiKey);
        if (studentRepository.existsByMail(student.getMail())) {
            throw new StudentAlreadyExistException();
        }
        student.setCustomer(customer);
        student.setCreated(new Date());
        student.setUpdated(new Date());
        student.setVerified(true);
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student login(String mail, String password) {
        return studentRepository.findByMailAndPasswordAndVerifiedTrue(mail, password).orElseThrow(WrongMailOrPasswordException::new);
    }

    @Override
    public void register(String foundationCode, Student student) {
        Foundation foundation = foundationService.getByPublicCode(foundationCode);
        if (studentRepository.existsByMail(student.getMail())) {
            throw new StudentAlreadyExistException();
        }
        if (studentRepository.existsByNumberAndCustomer(student.getNumber(), foundation.getCustomer())) {
            throw new StudentAlreadyExistException();
        }

        student.setCustomer(foundation.getCustomer());
        student.setCreated(new Date());
        student.setUpdated(new Date());
        student.setVerified(false);
        studentRepository.save(student);
    }

    @Override
    public void approve(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setVerified(true);
        studentRepository.save(student);
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
                if (value.equals("false")) value = false;
                if (value.equals("true")) value = true;
                builder.with(key, operator, value);
            }
        }
        builder.with("customer", "#Equal#", customer);
        return builder.build();
    }
}
