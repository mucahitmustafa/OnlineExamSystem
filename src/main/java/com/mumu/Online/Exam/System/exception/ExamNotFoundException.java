package com.mumu.Online.Exam.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException() {
        super("Exam not found!");
    }
}