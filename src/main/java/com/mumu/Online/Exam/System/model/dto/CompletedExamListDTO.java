package com.mumu.Online.Exam.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CompletedExamListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Date loginDate;
    private int score = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private int emptyAnswers = 0;

}
