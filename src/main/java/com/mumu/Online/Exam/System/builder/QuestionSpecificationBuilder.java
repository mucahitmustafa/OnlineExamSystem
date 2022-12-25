package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.model.entity.Question;
import com.mumu.Online.Exam.System.specification.QuestionSpecification;
import com.mumu.Online.Exam.System.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuestionSpecificationBuilder extends AbstractSpecificationBuilder<Question> {

    public QuestionSpecificationBuilder with(String key, String operation, Object value) {
        if (value == null) {
            return this;
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Question> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Question>> specs = new ArrayList<Specification<Question>>();
        for (SearchCriteria param : params) {
            specs.add(new QuestionSpecification(param));
        }

        return mergeSpecifications(specs);
    }

}
