package com.mumu.Online.Exam.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date startDate;
    private Date endDate;
    private Long duration;
    private Long examinerId;
    private List<Long> students;
    private List<QuestionDTO> questions;

}
