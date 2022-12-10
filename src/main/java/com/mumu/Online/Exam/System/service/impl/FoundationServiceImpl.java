package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.BadRequestException;
import com.mumu.Online.Exam.System.exception.FoundationNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import com.mumu.Online.Exam.System.repository.FoundationRepository;
import com.mumu.Online.Exam.System.service.FoundationService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoundationServiceImpl extends AbstractService implements FoundationService {

    private final FoundationRepository foundationRepository;

    public FoundationServiceImpl(final FoundationRepository foundationRepository) {
        this.foundationRepository = foundationRepository;
    }

    @Override
    public Foundation validate(final String apiKey) {
        String customerName = ApiKeyUtil.decode(apiKey);
        return foundationRepository.findByCustomer(customerName).orElseThrow(FoundationNotFoundException::new);
    }

    @Override
    public Foundation update(final String apiKey, Foundation foundation) {
        Foundation original = validate(apiKey);
        if (!Objects.equals(original.getId(), foundation.getId())) {
            throw new BadRequestException("The foundation ID is not matched!");
        }
        return foundationRepository.save(foundation);
    }

}
