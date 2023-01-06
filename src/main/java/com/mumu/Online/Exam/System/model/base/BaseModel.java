package com.mumu.Online.Exam.System.model.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {

    protected String customer;
    protected Date created;
    protected Date updated;
    protected boolean deleted = false;

}
