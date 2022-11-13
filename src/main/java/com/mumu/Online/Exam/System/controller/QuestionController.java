package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.service.QuestionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController extends AbstractController {

    private final QuestionConverter questionConverter;
    private final QuestionService questionService;

    public QuestionController(final QuestionConverter questionConverter, final QuestionService questionService) {
        this.questionConverter = questionConverter;
        this.questionService = questionService;
    }

}
