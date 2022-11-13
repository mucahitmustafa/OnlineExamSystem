package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    private StudentGroup group;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_results",
            joinColumns = @JoinColumn(name = "exam_result_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<ExamResult> results;

}
