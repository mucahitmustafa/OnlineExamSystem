package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.FoundationConverter;
import com.mumu.Online.Exam.System.service.FoundationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoundationController extends AbstractController {

    private final FoundationConverter foundationConverter;
    private final FoundationService foundationService;

    public FoundationController(final FoundationConverter foundationConverter,
                                final FoundationService foundationService) {
        this.foundationConverter = foundationConverter;
        this.foundationService = foundationService;
    }

}
