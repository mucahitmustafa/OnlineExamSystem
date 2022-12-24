package com.mumu.Online.Exam.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExaminerNotFoundException extends RuntimeException {
    public ExaminerNotFoundException() {
        super("Examiner not found!");
    }
}
