package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController extends AbstractController {

    private final StudentConverter studentConverter;
    private final StudentService studentService;

    public StudentController(final StudentConverter studentConverter, final StudentService studentService) {
        this.studentConverter = studentConverter;
        this.studentService = studentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<StudentDTO> getAll(@RequestHeader("api-key") final String apiKey) {
        return studentService.getAll(apiKey).stream().map(studentConverter::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return studentConverter.toDto(studentService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        studentService.delete(apiKey, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO update(@RequestHeader("api-key") final String apiKey, @RequestBody StudentDTO changedDto) {
        Student student = studentConverter.toModel(changedDto);
        return studentConverter.toDto(studentService.update(apiKey, student));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody StudentDTO createdDto) {
        Student student = studentConverter.toModel(createdDto);
        return studentConverter.toDto(studentService.create(apiKey, student));
    }

}
