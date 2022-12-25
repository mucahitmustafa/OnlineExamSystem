package com.mumu.Online.Exam.System.specification;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentGroupSpecification extends BaseSpecification implements Specification<StudentGroup> {

    private final SearchCriteria criteria;

    public StudentGroupSpecification(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<StudentGroup> root, final CriteriaQuery<?> query,
                                 final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ("#Equal#"):
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case ("#Like#"):
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    final char escapeChar = '_';
                    String value = criteria.getValue().toString();
                    value = value.replaceAll("^\\s+|\\s+$", "");
                    value = value.replaceAll("_", "__");
                    return builder.like(
                            root.<String>get(criteria.getKey()), "%" + value + "%",
                            escapeChar);
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            default:
                return null;
        }
    }
}
