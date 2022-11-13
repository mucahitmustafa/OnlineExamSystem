package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.service.ExaminerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExaminerController extends AbstractController {

    private final ExaminerConverter examinerConverter;
    private final ExaminerService examinerService;

    public ExaminerController(final ExaminerConverter examinerConverter,
                              final ExaminerService examinerService) {
        this.examinerConverter = examinerConverter;
        this.examinerService = examinerService;
    }

}
