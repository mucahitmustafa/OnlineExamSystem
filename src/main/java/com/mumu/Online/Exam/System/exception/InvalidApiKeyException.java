package com.mumu.Online.Exam.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException() {
        super("API key invalid!");
    }
}
