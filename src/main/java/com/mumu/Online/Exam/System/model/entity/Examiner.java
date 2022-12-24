package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
@Data
@EqualsAndHashCode(callSuper = true)
public class Examiner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
