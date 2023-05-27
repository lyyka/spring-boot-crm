package com.example.lr20190024.common.filters;

public abstract class FilterRequest<T> {
    public abstract FilterBuilder<T> getFilterBuilder();

    public SearchSpecification<T> getSearchSpecification() {
        return new SearchSpecification<>(this);
    }
}
