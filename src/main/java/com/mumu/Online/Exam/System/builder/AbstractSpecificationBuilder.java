package com.mumu.Online.Exam.System.builder;

import com.mumu.Online.Exam.System.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AbstractSpecificationBuilder<T> {
    final List<SearchCriteria> params = new ArrayList<>();

    Specification<T> mergeSpecifications(List<Specification<T>> specs) {
        Specification<T> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
