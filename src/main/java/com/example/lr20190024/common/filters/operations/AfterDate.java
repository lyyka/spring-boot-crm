package com.example.lr20190024.common.filters.operations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@AllArgsConstructor
public class AfterDate<T> implements FilterOperation<T> {
    private String field;
    private Date value;
    private boolean strict;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return strict ? cb.greaterThan(root.get(field), value) : cb.greaterThanOrEqualTo(root.get(field), value);
    }
}
