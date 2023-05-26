package com.example.lr20190024.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Paginate {
    public static Pageable from(Integer page, Integer perPage) {
        return PageRequest.of(
                page != null ? page : 0,
                perPage != null ? perPage : 10
        );
    }
}
