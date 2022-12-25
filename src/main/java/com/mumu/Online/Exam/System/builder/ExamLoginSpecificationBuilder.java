package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import com.mumu.Online.Exam.System.specification.ExamLoginSpecification;
import com.mumu.Online.Exam.System.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExamLoginSpecificationBuilder extends AbstractSpecificationBuilder<ExamLogin> {

    public ExamLoginSpecificationBuilder with(String key, String operation, Object value) {
        if (value == null) {
            return this;
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<ExamLogin> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<ExamLogin>> specs = new ArrayList<Specification<ExamLogin>>();
        for (SearchCriteria param : params) {
            specs.add(new ExamLoginSpecification(param));
        }

        return mergeSpecifications(specs);
    }

}
