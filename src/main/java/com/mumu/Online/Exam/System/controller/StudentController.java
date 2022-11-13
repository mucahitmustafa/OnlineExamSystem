package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController extends AbstractController {

    private final StudentConverter studentConverter;
    private final StudentService studentService;

    public StudentController(final StudentConverter studentConverter, final StudentService studentService) {
        this.studentConverter = studentConverter;
        this.studentService = studentService;
    }

}
