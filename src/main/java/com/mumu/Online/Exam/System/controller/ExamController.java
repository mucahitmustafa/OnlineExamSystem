package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<ExamDTO> getAll(@RequestHeader("api-key") final String apiKey,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "filters[]", required = false) String[] filters,
                                @RequestParam(value = "sort", required = false) String sort) {
        if (pageNumber == null || pageNumber < 0) pageNumber = 1;
        if (filters == null) filters = new String[0];
        return examService.getAll(apiKey, pageNumber, pageSize, filters, sort).map(examConverter::toDto);
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
