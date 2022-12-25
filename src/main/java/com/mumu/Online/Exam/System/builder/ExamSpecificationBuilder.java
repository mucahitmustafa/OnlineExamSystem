package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.model.entity.Exam;
import com.mumu.Online.Exam.System.specification.ExamSpecification;
import com.mumu.Online.Exam.System.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExamSpecificationBuilder extends AbstractSpecificationBuilder<Exam> {

    public ExamSpecificationBuilder with(String key, String operation, Object value) {
        if (value == null) {
            return this;
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Exam> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Exam>> specs = new ArrayList<Specification<Exam>>();
        for (SearchCriteria param : params) {
            specs.add(new ExamSpecification(param));
        }

        return mergeSpecifications(specs);
    }

}
