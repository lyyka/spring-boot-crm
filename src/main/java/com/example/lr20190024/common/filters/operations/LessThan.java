package com.example.lr20190024.common.filters.operations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessThan<T> implements FilterOperation<T> {
    private String field;
    private Number value;
    private boolean strict;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return strict ? cb.lt(root.get(field), value) : cb.le(root.get(field), value);
    }
}
