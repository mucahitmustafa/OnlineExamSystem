package com.mumu.Online.Exam.System.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class ExamResult extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    private String answers;

    private float score;

    private Date loginDate;

}
