package com.mumu.Online.Exam.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExamStatisticDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int totalParticipants;
    private Double averageScore;
    private Double highestScore;
    private Double lowestScore;
    private String mostCorrectQuestion;
    private Long mostCorrectQuestionId;
    private String mostWrongQuestion;
    private Long mostWrongQuestionId;
    private String mostLeftBlankQuestion;
    private Long mostLeftBlankQuestionId;
    private HashMap<Long, String> questionStatistic;

}
