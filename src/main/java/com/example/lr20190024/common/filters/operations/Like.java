package com.example.lr20190024.common.filters.operations;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Like<T> implements FilterOperation<T> {
    private String[] fields;
    private String value;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Expression<String> column = cb.lower(root.get(fields[0]));
        for (int i = 1; i < fields.length; i++) {
            column = cb.concat(cb.concat(column, cb.literal(" ")), cb.lower(root.get(fields[i])));
        }
        return cb.like(column, "%" + value.toLowerCase() + "%");
    }
}
