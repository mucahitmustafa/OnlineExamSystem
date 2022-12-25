package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.model.entity.Student;
import com.mumu.Online.Exam.System.specification.SearchCriteria;
import com.mumu.Online.Exam.System.specification.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecificationBuilder extends AbstractSpecificationBuilder<Student> {

    public StudentSpecificationBuilder with(String key, String operation, Object value) {
        if (value == null) {
            return this;
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Student> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Student>> specs = new ArrayList<Specification<Student>>();
        for (SearchCriteria param : params) {
            specs.add(new StudentSpecification(param));
        }

        return mergeSpecifications(specs);
    }

}
