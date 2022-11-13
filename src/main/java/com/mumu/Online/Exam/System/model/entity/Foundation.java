package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mumu.Online.Exam.System.model.base.BaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class Foundation extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "foundation_managers",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "foundation_id"))
    private List<Examiner> managers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "foundation_examiners",
            joinColumns = @JoinColumn(name = "examiner_id"),
            inverseJoinColumns = @JoinColumn(name = "foundation_id"))
    private List<Examiner> examiners;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "foundation_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "foundation_id"))
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "foundation_exams",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "foundation_id"))
    private List<Exam> exams;

}
