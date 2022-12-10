package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExaminerConverter;
import com.mumu.Online.Exam.System.model.dto.ExaminerDTO;
import com.mumu.Online.Exam.System.model.entity.Examiner;
import com.mumu.Online.Exam.System.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExaminerController extends AbstractController {

    private final ExaminerConverter examinerConverter;
    private final ExaminerService examinerService;

    public ExaminerController(final ExaminerConverter examinerConverter,
                              final ExaminerService examinerService) {
        this.examinerConverter = examinerConverter;
        this.examinerService = examinerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ExaminerDTO> getAll(@RequestHeader("api-key") final String apiKey) {
        return examinerService.getAll(apiKey).stream().map(examinerConverter::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExaminerDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return examinerConverter.toDto(examinerService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        examinerService.delete(apiKey, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExaminerDTO update(@RequestHeader("api-key") final String apiKey, @RequestBody ExaminerDTO changedDto) {
        Examiner examiner = examinerConverter.toModel(changedDto);
        return examinerConverter.toDto(examinerService.update(apiKey, examiner));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExaminerDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody ExaminerDTO createdDto) {
        Examiner examiner = examinerConverter.toModel(createdDto);
        return examinerConverter.toDto(examinerService.create(apiKey, examiner));
    }

}
