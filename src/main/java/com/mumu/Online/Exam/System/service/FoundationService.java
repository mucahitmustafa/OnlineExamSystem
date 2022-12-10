package com.mumu.Online.Exam.System.service;

import com.mumu.Online.Exam.System.model.entity.Foundation;

public interface FoundationService {

    Foundation validate(final String apiKey);

    Foundation update(final String apiKey, Foundation foundation);

}
