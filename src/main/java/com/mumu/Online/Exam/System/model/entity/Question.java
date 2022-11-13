package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mumu.Online.Exam.System.model.base.BaseModel;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class Question extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exam exam;

    private String text;

    private String answers;

    private int correctAnswerIndex;

}
