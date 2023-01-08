package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.ExamConverter;
import com.mumu.Online.Exam.System.converter.QuestionConverter;
import com.mumu.Online.Exam.System.model.dto.ExamDTO;
import com.mumu.Online.Exam.System.model.dto.ExamUpdateDTO;
import com.mumu.Online.Exam.System.model.dto.QuestionDTO;
import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.service.ExamService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/exams")
public class ExamController extends AbstractController {

    private final ExamService examService;
    private final ExamConverter examConverter;
    private final QuestionConverter questionConverter;

    public ExamController(final ExamService examService, final ExamConverter examConverter,
                          final QuestionConverter questionConverter) {
        this.examService = examService;
        this.examConverter = examConverter;
        this.questionConverter = questionConverter;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<ExamDTO> getAll(@RequestHeader("api-key") final String apiKey,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "filters[]", required = false) String[] filters,
                                @RequestParam(value = "sort", required = false) String sort) {
        if (pageNumber == null || pageNumber < 0) pageNumber = 1;
        if (filters == null) filters = new String[0];
        return examService.getAll(apiKey, pageNumber, pageSize, filters, sort).map(examConverter::toDto);
    }

    @RequestMapping(value = "/byStudent/{studentId}", method = RequestMethod.GET)
    public List<ExamDTO> getAllByStudent(@PathVariable("studentId") final Long studentId) {
        return examService.getUncompletedExamsAllByStudent(studentId).stream().map(examConverter::toDto)
                .collect(Collectors.toList());
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
    public ExamDTO update(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id,
                          @RequestBody ExamUpdateDTO changedDto) {
        Exam exam = examConverter.toModelForUpdate(changedDto);
        List<Question> questions = changedDto.getQuestions().stream().map(questionConverter::toModelForCreate)
                .collect(Collectors.toList());
        exam.setId(id);
        return examConverter.toDto(examService.update(apiKey, exam, questions));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ExamDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody ExamDTO createdDto) {
        Exam exam = examConverter.toModel(createdDto);
        return examConverter.toDto(examService.create(apiKey, exam));
    }
}
