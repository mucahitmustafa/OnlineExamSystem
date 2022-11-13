package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.StudentGroupConverter;
import com.mumu.Online.Exam.System.service.StudentGroupService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentGroupController extends AbstractController {

    private final StudentGroupConverter studentGroupConverter;
    private final StudentGroupService studentGroupService;

    public StudentGroupController(final StudentGroupConverter studentGroupConverter,
                                  final StudentGroupService studentGroupService) {
        this.studentGroupConverter = studentGroupConverter;
        this.studentGroupService = studentGroupService;
    }

}
