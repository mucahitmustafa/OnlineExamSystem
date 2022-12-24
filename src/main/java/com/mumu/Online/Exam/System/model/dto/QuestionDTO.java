package com.mumu.Online.Exam.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class QuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long examId;
    private String text;
    private String answers;
    private int correctAnswerIndex;
}
