package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExamLoginConverter;
import com.mumu.Online.Exam.System.model.dto.ExamLoginDTO;
import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import com.mumu.Online.Exam.System.service.ExamLoginService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/examLogins")
public class ExamLoginController extends AbstractController {

    private final ExamLoginConverter examLoginConverter;
    private final ExamLoginService examLoginService;

    public ExamLoginController(final ExamLoginConverter examLoginConverter, final ExamLoginService examLoginService) {
        this.examLoginConverter = examLoginConverter;
        this.examLoginService = examLoginService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<ExamLoginDTO> getAll(@RequestHeader("api-key") final String apiKey,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(value = "filters[]", required = false) String[] filters,
                                     @RequestParam(value = "sort", required = false) String sort) {
        if (pageNumber == null || pageNumber < 0) pageNumber = 1;
        if (filters == null) filters = new String[0];
        return examLoginService.getAll(apiKey, pageNumber, pageSize, filters, sort).map(examLoginConverter::toDto);
    }

    @RequestMapping(value = "/byStudent/{studentId}", method = RequestMethod.GET)
    public List<ExamLoginDTO> getAllByStudent(@PathVariable("studentId") final Long studentId) {
        return examLoginService.getAllByStudent(studentId).stream().map(examLoginConverter::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExamLoginDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return examLoginConverter.toDto(examLoginService.validate(apiKey, id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ExamLoginDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody ExamLoginDTO createdDto) {
        ExamLogin examLogin = examLoginConverter.toModel(createdDto);
        return examLoginConverter.toDto(examLoginService.create(apiKey, examLogin));
    }
}
