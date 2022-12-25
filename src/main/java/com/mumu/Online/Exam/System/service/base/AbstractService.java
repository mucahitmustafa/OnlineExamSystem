package com.mumu.Online.Exam.System.service.base;

import org.springframework.data.domain.Sort;

public abstract class AbstractService {
    protected static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
    protected static final Integer DEFAULT_PAGE_SIZE = 20;

}
