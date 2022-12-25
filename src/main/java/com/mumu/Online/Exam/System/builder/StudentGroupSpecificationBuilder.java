package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import com.mumu.Online.Exam.System.specification.SearchCriteria;
import com.mumu.Online.Exam.System.specification.StudentGroupSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentGroupSpecificationBuilder extends AbstractSpecificationBuilder<StudentGroup> {

    public StudentGroupSpecificationBuilder with(String key, String operation, Object value) {
        if (value == null) {
            return this;
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<StudentGroup> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<StudentGroup>> specs = new ArrayList<Specification<StudentGroup>>();
        for (SearchCriteria param : params) {
            specs.add(new StudentGroupSpecification(param));
        }

        return mergeSpecifications(specs);
    }

}
