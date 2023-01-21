package com.mumu.Online.Exam.System.service.impl;

import com.mumu.Online.Exam.System.exception.FoundationNotFoundException;
import com.mumu.Online.Exam.System.model.entity.Foundation;
import com.mumu.Online.Exam.System.repository.FoundationRepository;
import com.mumu.Online.Exam.System.service.FoundationService;
import com.mumu.Online.Exam.System.service.base.AbstractService;
import com.mumu.Online.Exam.System.utils.ApiKeyUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FoundationServiceImpl extends AbstractService implements FoundationService {

    private final FoundationRepository foundationRepository;

    public FoundationServiceImpl(final FoundationRepository foundationRepository) {
        this.foundationRepository = foundationRepository;
    }

    @Override
    public Foundation validate(final String apiKey) {
        String customerName = ApiKeyUtil.decode(apiKey);
        Optional<Foundation> foundationOpt = foundationRepository.findByCustomer(customerName);
        if (foundationOpt.isPresent()) return foundationOpt.get();
        Foundation foundation = new Foundation();
        foundation.setName(customerName);
        foundation.setCustomer(customerName);
        foundation.setPublicCode(RandomStringUtils.randomAlphabetic(6).toUpperCase());
        foundation.setUpdated(new Date());
        foundation.setCreated(new Date());
        return foundationRepository.save(foundation);
    }

    @Override
    public Foundation getByPublicCode(String publicCode) {
        return foundationRepository.findByPublicCode(publicCode).orElseThrow(FoundationNotFoundException::new);
    }

}
