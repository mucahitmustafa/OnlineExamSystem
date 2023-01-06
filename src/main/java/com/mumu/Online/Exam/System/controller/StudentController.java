package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.StudentConverter;
import com.mumu.Online.Exam.System.model.dto.StudentDTO;
import com.mumu.Online.Exam.System.model.dto.StudentLoginDTO;
import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/students")
public class StudentController extends AbstractController {

    private final StudentConverter studentConverter;
    private final StudentService studentService;

    public StudentController(final StudentConverter studentConverter, final StudentService studentService) {
        this.studentConverter = studentConverter;
        this.studentService = studentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<StudentDTO> getAll(@RequestHeader("api-key") final String apiKey,
                                   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                   @RequestParam(value = "filters[]", required = false) String[] filters,
                                   @RequestParam(value = "sort", required = false) String sort) {
        if (pageNumber == null || pageNumber < 0) pageNumber = 1;
        if (filters == null) filters = new String[0];
        return studentService.getAll(apiKey, pageNumber, pageSize, filters, sort).map(studentConverter::toDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return studentConverter.toDto(studentService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        studentService.delete(apiKey, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public StudentDTO update(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id,
                             @RequestBody StudentDTO changedDto) {
        Student student = studentConverter.toModel(changedDto);
        student.setId(id);
        return studentConverter.toDto(studentService.update(apiKey, student));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public StudentDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody StudentDTO createdDto) {
        Student student = studentConverter.toModel(createdDto);
        return studentConverter.toDto(studentService.create(apiKey, student));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public StudentDTO login(@RequestBody StudentLoginDTO loginDTO) {
        return studentConverter.toDto(studentService.login(loginDTO.getMail(), loginDTO.getPassword()));
    }

}
