package com.example.lr20190024.common.filters.operations;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Between multiple groups, AND clause should be applied
 * Inside the group itself, OR is applied
 */
@Data
public class FilterOperationGroup<T> {
    private List<FilterOperation<T>> operations = new ArrayList<>();

    public boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public void add(FilterOperation<T> filterOperation) {
        this.operations.add(filterOperation);
    }
}
