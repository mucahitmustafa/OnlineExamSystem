package com.mumu.Online.Exam.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExamLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long examId;
    private Long studentId;
    private String answers;
    private float score;
    private Date loginDate;
    private Long duration;

}
