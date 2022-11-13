package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController extends AbstractController {

    private final ExamConverter examConverter;
    private final ExamService examService;

    public ExamController(final ExamConverter examConverter, final ExamService examService) {
        this.examConverter = examConverter;
        this.examService = examService;
    }
}
