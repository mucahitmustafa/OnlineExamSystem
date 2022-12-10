package com.mumu.Online.Exam.System.model.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {

    protected String customer;
    protected Date created;
    protected Date updated;
    protected boolean deleted;

}
