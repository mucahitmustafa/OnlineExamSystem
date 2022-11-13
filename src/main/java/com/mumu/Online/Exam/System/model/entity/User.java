package com.mumu.Online.Exam.System.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class User {

    private String name;

    private String mail;

    private String password;

    private boolean blocked;

    private Date lastLoginDate;
}
