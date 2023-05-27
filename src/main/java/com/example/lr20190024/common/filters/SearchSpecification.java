package com.example.lr20190024.common.filters;

import com.example.lr20190024.common.filters.operations.FilterOperationGroup;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    private final FilterRequest<T> request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        FilterBuilder<T> filterBuilder = request.getFilterBuilder().and();
        Predicate predicate = cb.and();

        for (FilterOperationGroup<T> filterOperationGroup : filterBuilder.getOperationGroups()) {
            predicate = cb.and(
                    cb.or(
                            filterOperationGroup
                                    .getOperations()
                                    .stream()
                                    .map(operation -> operation.toPredicate(root, query, cb))
                                    .toList()
                                    .toArray(new Predicate[0])
                    ), predicate
            );
        }

        return predicate;
    }
}
