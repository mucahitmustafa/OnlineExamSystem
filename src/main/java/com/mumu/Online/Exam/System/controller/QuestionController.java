package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController extends AbstractController {

    private final QuestionConverter questionConverter;
    private final QuestionService questionService;

    public QuestionController(final QuestionConverter questionConverter, final QuestionService questionService) {
        this.questionConverter = questionConverter;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<QuestionDTO> getAll(@RequestHeader("api-key") final String apiKey,
                                    @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(value = "filters[]", required = false) String[] filters,
                                    @RequestParam(value = "sort", required = false) String sort) {
        if (pageNumber == null || pageNumber < 0) pageNumber = 1;
        if (filters == null) filters = new String[0];
        return questionService.getAll(apiKey, pageNumber, pageSize, filters, sort).map(questionConverter::toDto);
    }

    @RequestMapping(value = "/byExam/{examId}", method = RequestMethod.GET)
    public List<QuestionDTO> getAllByStudent(@PathVariable("examId") final Long examId) {
        return questionService.getByExam(examId).stream().map(questionConverter::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QuestionDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return questionConverter.toDto(questionService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        questionService.delete(apiKey, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public QuestionDTO update(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id,
                              @RequestBody QuestionDTO changedDto) {
        Question question = questionConverter.toModel(changedDto);
        question.setId(id);
        return questionConverter.toDto(questionService.update(apiKey, question));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public QuestionDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody QuestionDTO createdDto) {
        Question question = questionConverter.toModel(createdDto);
        return questionConverter.toDto(questionService.create(apiKey, question));
    }

}
