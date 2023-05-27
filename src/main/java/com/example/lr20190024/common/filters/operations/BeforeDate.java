package com.example.lr20190024.common.filters.operations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BeforeDate<T> implements FilterOperation<T> {
    private String field;
    private Date value;
    private boolean strict;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return strict ? cb.lessThan(root.get(field), value) : cb.lessThanOrEqualTo(root.get(field), value);
    }
}
