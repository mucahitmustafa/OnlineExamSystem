package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/exams")
public class ExamController extends AbstractController {

    private final ExamConverter examConverter;
    private final ExamService examService;

    public ExamController(final ExamConverter examConverter, final ExamService examService) {
        this.examConverter = examConverter;
        this.examService = examService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ExamDTO> getAll(@RequestHeader("api-key") final String apiKey) {
        return examService.getAll(apiKey).stream().map(examConverter::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExamDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return examConverter.toDto(examService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        examService.delete(apiKey, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ExamDTO update(@RequestHeader("api-key") final String apiKey, @RequestBody ExamDTO changedDto) {
        Exam exam = examConverter.toModel(changedDto);
        return examConverter.toDto(examService.update(apiKey, exam));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ExamDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody ExamDTO createdDto) {
        Exam exam = examConverter.toModel(createdDto);
        return examConverter.toDto(examService.create(apiKey, exam));
    }
}
