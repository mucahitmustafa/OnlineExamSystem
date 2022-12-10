package com.mumu.Online.Exam.System.controller;

import com.mumu.Online.Exam.System.controller.base.AbstractController;
import com.mumu.Online.Exam.System.converter.FoundationConverter;
import com.mumu.Online.Exam.System.model.dto.FoundationDTO;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import com.mumu.Online.Exam.System.service.FoundationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/foundations")
public class FoundationController extends AbstractController {

    private final FoundationConverter foundationConverter;
    private final FoundationService foundationService;

    public FoundationController(final FoundationConverter foundationConverter,
                                final FoundationService foundationService) {
        this.foundationConverter = foundationConverter;
        this.foundationService = foundationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public FoundationDTO get(@RequestHeader("api-key") final String apiKey) {
        return foundationConverter.toDto(foundationService.validate(apiKey));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public FoundationDTO update(@RequestHeader("api-key") final String apiKey, @RequestBody FoundationDTO changedDto) {
        Foundation foundation = foundationConverter.toModel(changedDto);
        return foundationConverter.toDto(foundationService.update(apiKey, foundation));
    }

}
