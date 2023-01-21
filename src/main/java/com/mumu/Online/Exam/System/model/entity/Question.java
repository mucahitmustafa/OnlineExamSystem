package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mumu.Online.Exam.System.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private Long index;

    @ManyToOne
    private Exam exam;

    private String text;

    private String answers;

    private int correctAnswerIndex;

    private int score;

}
