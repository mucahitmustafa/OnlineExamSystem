package com.mumu.Online.Exam.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExaminerNotFoundException extends RuntimeException {
    public ExaminerNotFoundException() {
        super("Examiner not found!");
    }
}
