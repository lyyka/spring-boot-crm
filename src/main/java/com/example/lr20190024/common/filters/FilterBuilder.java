package com.example.lr20190024.common.filters;

import com.example.lr20190024.common.filters.operations.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class FilterBuilder<T> {
    private List<FilterOperationGroup<T>> operationGroups = new ArrayList<>();
    private FilterOperationGroup<T> currentGroup = new FilterOperationGroup<>();

    public FilterBuilder<T> and() {
        if (!this.currentGroup.isEmpty()) {
            this.operationGroups.add(this.currentGroup);
        }
        this.currentGroup = new FilterOperationGroup<>();
        return this;
    }

    public FilterBuilder<T> like(String field, String expr) {
        this.currentGroup.add(new Like<>(new String[]{field}, expr));
        return this;
    }

    public FilterBuilder<T> like(String expr, String... fields) {
        this.currentGroup.add(new Like<>(fields, expr));
        return this;
    }

    public FilterBuilder<T> lt(String field, Number expr) {
        this.currentGroup.add(new LessThan<>(field, expr, true));
        return this;
    }

    public FilterBuilder<T> lt(String field, Date expr) {
        this.currentGroup.add(new BeforeDate<>(field, expr, true));
        return this;
    }

    public FilterBuilder<T> gt(String field, Number expr) {
        this.currentGroup.add(new GreaterThan<>(field, expr, true));
        return this;
    }

    public FilterBuilder<T> gt(String field, Date expr) {
        this.currentGroup.add(new AfterDate<>(field, expr, true));
        return this;
    }

    public FilterBuilder<T> lte(String field, Number expr) {
        this.currentGroup.add(new LessThan<>(field, expr, false));
        return this;
    }

    public FilterBuilder<T> lte(String field, Date expr) {
        this.currentGroup.add(new BeforeDate<>(field, expr, false));
        return this;
    }

    public FilterBuilder<T> gte(String field, Number expr) {
        this.currentGroup.add(new GreaterThan<>(field, expr, false));
        return this;
    }

    public FilterBuilder<T> gte(String field, Date expr) {
        this.currentGroup.add(new AfterDate<>(field, expr, false));
        return this;
    }
}
