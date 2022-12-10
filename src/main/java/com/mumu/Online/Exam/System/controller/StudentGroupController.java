package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.StudentGroupConverter;
import com.mumu.Online.Exam.System.model.dto.StudentGroupDTO;
import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import com.mumu.Online.Exam.System.service.StudentGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentGroupController extends AbstractController {

    private final StudentGroupConverter studentGroupConverter;
    private final StudentGroupService studentGroupService;

    public StudentGroupController(final StudentGroupConverter studentGroupConverter,
                                  final StudentGroupService studentGroupService) {
        this.studentGroupConverter = studentGroupConverter;
        this.studentGroupService = studentGroupService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<StudentGroupDTO> getAll(@RequestHeader("api-key") final String apiKey) {
        return studentGroupService.getAll(apiKey).stream().map(studentGroupConverter::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentGroupDTO get(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        return studentGroupConverter.toDto(studentGroupService.validate(apiKey, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestHeader("api-key") final String apiKey, @PathVariable("id") final Long id) {
        studentGroupService.delete(apiKey, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentGroupDTO update(@RequestHeader("api-key") final String apiKey, @RequestBody StudentGroupDTO changedDto) {
        StudentGroup studentGroup = studentGroupConverter.toModel(changedDto);
        return studentGroupConverter.toDto(studentGroupService.update(apiKey, studentGroup));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentGroupDTO create(@RequestHeader("api-key") final String apiKey, @RequestBody StudentGroupDTO createdDto) {
        StudentGroup studentGroup = studentGroupConverter.toModel(createdDto);
        return studentGroupConverter.toDto(studentGroupService.create(apiKey, studentGroup));
    }

}
