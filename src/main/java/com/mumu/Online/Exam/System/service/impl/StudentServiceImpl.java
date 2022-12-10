package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.StudentNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.repository.StudentRepository;
import com.mumu.Online.Exam.System.service.StudentService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends AbstractService implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll(String apiKey) {
        final String customer = ApiKeyUtil.decode(apiKey);
        return studentRepository.findAllByCustomer(customer);
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
}
